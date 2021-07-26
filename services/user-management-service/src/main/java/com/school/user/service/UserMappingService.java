package com.school.user.service;

import com.school.user.dto.*;
import com.school.user.http.dto.AdminResponse;
import com.school.user.http.dto.CreateLoginRequest;
import com.school.user.http.dto.UserResponse;
import com.school.user.proxy.AuthProxy;
import com.school.user.repo.IParentRepo;
import com.school.user.repo.IStaffRepo;
import com.school.user.repo.IStudentRepo;
import com.school.user.repo.IUserMappingRepo;
import lombok.Data;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Data
@Service
public class UserMappingService {

    private final IUserMappingRepo iUserMappingRepo;
    private final IStudentRepo iStudentRepo;
    private final IStaffRepo iStaffRepo;
    private final IParentRepo parentRepo;
    private final Utils utils;
    private final AuthProxy authProxy;

    public UserResponse findUser(String userId) {
        UserMapping userMapping = iUserMappingRepo.findByUserId(UUID.fromString(userId));
        if (userMapping != null) {
            UserResponse response = new UserResponse();
            response.setUserType(userMapping.getUserType().toString());
            switch (userMapping.getUserType()) {
                case STUDENT:
                    response.setSchool(userMapping.getSchool().toString());
                    Student student = iStudentRepo.findNameByUserId(UUID.fromString(userId));
                    response.setFirstName(student.getFirstName() );
                    response.setMiddleName(student.getMiddleName());
                    response.setLastName(student.getLastName());
                    break;
                case TEACHER:
                case FINANCE_MANGER:
                case ACADEMIC_MANGER:
                    response.setSchool(userMapping.getSchool().toString());
                    Staff staff=iStaffRepo.findByUserId(UUID.fromString(userId));
                    response.setFirstName(staff.getFirstName() );
                    response.setMiddleName(staff.getMiddleName());
                    response.setLastName(staff.getLastName());
                    break;
                case PARENT:
                    Parent parent=parentRepo.findByUserId(UUID.fromString(userId));
                    response.setFirstName(parent.getFirstName() );
                    response.setMiddleName(parent.getMiddleName());
                    response.setLastName(parent.getLastName());
                    break;
            }
            return response;
        }
        return null;
    }


    public UserMapping findUserLoginId(String userId) {
       return iUserMappingRepo.findByLogin(UUID.fromString(userId));
    }

    public AdminResponse addAdmin() {
        AdminResponse adminResponse=new AdminResponse();
        UserMapping userMapping = new UserMapping();
        try {
            adminResponse.setUserName(utils.randomChar(8));
            adminResponse.setPassword(utils.randomChar(8));
            userMapping.setUserType(UserType.SYS_ADMIN);
            CreateLoginRequest createLoginRequestRequest = new CreateLoginRequest();
            createLoginRequestRequest.setEmail("skyhawks.app@gmail.com");
            createLoginRequestRequest.setName("sysadmin");
            createLoginRequestRequest.setUserName(adminResponse.getUserName());
            createLoginRequestRequest.setPassword(adminResponse.getPassword());
            String createLoginResponse = authProxy.createLogin(createLoginRequestRequest);
            if (createLoginResponse != null) {
                userMapping.setLogin(UUID.fromString(createLoginResponse));
                userMapping.setLogin(UUID.fromString(createLoginResponse));
                adminResponse.setToken(iUserMappingRepo.save(userMapping).getUuid().toString());
            }
        } catch (Exception e) {
            throw e;
        }
        return adminResponse;
    }

    public void deleteAdmin(String token) {
        Optional<UserMapping> mapping = iUserMappingRepo.findById(UUID.fromString(token));
        if(mapping.isPresent()){
            UserMapping userMapping = mapping.get();
            authProxy.deleteLogin(userMapping.getLogin().toString());
            iUserMappingRepo.delete(userMapping);
        }
    }
}
