/*
Copyright @ 2021
Project : skyhawks-core
Written: ananthupm
Date : 26/06/21
*/
package com.skyhawks.authentication.resources;

import com.skyhawks.authentication.config.ApplicationConfiguration;
import com.skyhawks.authentication.config.jwt.JWTUtil;
import com.skyhawks.authentication.exception.MailServiceDownException;
import com.skyhawks.authentication.model.LoginUser;
import com.skyhawks.authentication.proxy.MailServerProxy;
import com.skyhawks.authentication.request.*;
import com.skyhawks.authentication.resources.dto.AuthenticationResponse;
import com.skyhawks.authentication.services.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.webjars.NotFoundException;

import javax.validation.Valid;

@Data
@RestController
public class AuthenticationController {

    public static final String MAIL_SEND_CREATE_USER = "/mail/send/create-user";
    private final UserService userService;
    private final MailServerProxy mailServerProxy;
    @Autowired
    private final JWTUtil jwtUtil;
    private final RestTemplate restTemplate;

    private final ApplicationConfiguration applicationConfiguration;

    @GetMapping(path = "/test")
    public String test(@RequestHeader String userName){

        return "Success -> "+userName;
    }

    @GetMapping(path = "/authored/test")
    public String testAfterLogin(){
        return "Success";
    }


    @PostMapping(path = "/sign-up")
    public String createUser(@Valid @RequestBody CreateUserRequest request) throws MailServiceDownException {
        LoginUser loginUser= userService.saveUser(request);
        if(loginUser!=null && loginUser.getUuid()!=null){
            try {
                SendCreateMailRequest mailRequest = new SendCreateMailRequest();
                mailRequest.setSubject("Welcome to skyhawks");
                mailRequest.setTo(request.getEmail());
                mailRequest.setBody(String.format("Hi %s,\n Welcome to skyhawks %s \n Please find you login details.\n " +
                                "Username:\t%s \nPassword:\t%s\n\nThanks and Regards.",
                        request.getName(), request.getSchoolName(), loginUser.getUserName(), loginUser.getPassword()));
                mailServerProxy.sendMail(mailRequest);
            }catch (Exception e){
                userService.deleteUser(loginUser.getUuid().toString());
                throw  new MailServiceDownException(e);
            }
        }
        return loginUser.getUuid().toString();
    }

    @DeleteMapping(path = "/sign-out/{id}")
    public void deleteUser(@PathVariable String id){
         userService.deleteUser(id);
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody AuthenticationRequest request) {
        final LoginUser userDetails = userService.findByUsername(request.getUserName());
        if(userDetails!=null) {
            final String jwt = jwtUtil.generateToken(userDetails);
            return ResponseEntity.ok(AuthenticationResponse
                    .builder()
                    .jwt(jwt)
                    .newUser(userDetails.getNewUser())
                    .active(userDetails.getEnabled())
                    .userId(userDetails.getUuid().toString())
                    .userName(userDetails.getUserName()).build());
        }else{
            throw new NotFoundException("User Not found");
        }
    }

    @GetMapping(path = "/validate")
    public TokenResponse validate(@Valid @RequestParam String token){
        System.out.println("validate");
        return userService.validate(token);
    }

}
