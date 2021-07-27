package com.school.user.service;

import com.school.user.dto.*;
import com.school.user.exception.AdminCreateException;
import com.school.user.exception.AdminDeleteException;
import com.school.user.http.dto.AdminResponse;
import com.school.user.http.dto.CreateLoginRequest;
import com.school.user.http.dto.UserResponse;
import com.school.user.proxy.AuthProxy;
import com.school.user.repo.*;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Data
@Service
public class UserMappingService {

    private final IUserMappingRepo iUserMappingRepo;
    private final IStudentRepo iStudentRepo;
    private final IStaffRepo iStaffRepo;
    private final IParentRepo parentRepo;
    private final ISchoolRepo iSchoolRepo;
    private final Utils utils;
    private final AuthProxy authProxy;

    public UserResponse findUser(String userId) {
        UserMapping userMapping = iUserMappingRepo.findByLogin(UUID.fromString(userId));
        if (userMapping != null) {
            UserResponse response = new UserResponse();
            response.setUserType(userMapping.getUserType().toString());
            response.setSchool(userMapping.getSchool().toString());
            switch (userMapping.getUserType()) {
                case STUDENT:
                    Student student = iStudentRepo.findByUserId(userMapping.getUserId());
                    response.setFirstName(student.getFirstName());
                    response.setMiddleName(student.getMiddleName());
                    response.setLastName(student.getLastName());
                    response.setEmail(student.getEmail());
                    break;
                case TEACHER:
                case FINANCE_MANGER:
                case ACADEMIC_MANGER:
                    Staff staff = iStaffRepo.findByUserId(userMapping.getUserId());
                    response.setFirstName(staff.getFirstName());
                    response.setMiddleName(staff.getMiddleName());
                    response.setLastName(staff.getLastName());
                    response.setEmail(staff.getEmail());
                    break;
                case PARENT:
                    Parent parent = parentRepo.findByUserId(userMapping.getUserId());
                    response.setFirstName(parent.getFirstName());
                    response.setMiddleName(parent.getMiddleName());
                    response.setLastName(parent.getLastName());
                    response.setEmail(parent.getEmail());
                    break;
                case SCHOOL:
                    School school = iSchoolRepo.findByUserId(userMapping.getUserId());
                    response.setFirstName(school.getName());
                    response.setMiddleName("");
                    response.setLastName("");
                    response.setEmail(school.getEmail());
                    break;
            }
            return response;
        }
        return null;
    }


    public AdminResponse addAdmin() throws AdminCreateException {
        AdminResponse adminResponse = new AdminResponse();
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
            throw new AdminCreateException(e);
        }
        return adminResponse;
    }

    public void deleteAdmin(String token) throws AdminDeleteException {
        try {
            Optional<UserMapping> mapping = iUserMappingRepo.findById(UUID.fromString(token));
            if (mapping.isPresent()) {
                UserMapping userMapping = mapping.get();
                authProxy.deleteLogin(userMapping.getLogin().toString());
                iUserMappingRepo.delete(userMapping);
            }
        } catch (Exception e) {
            throw new AdminDeleteException(e);
        }
    }
}
