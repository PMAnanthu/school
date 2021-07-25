package com.school.studentmanagementservice.service;

import com.school.studentmanagementservice.dto.*;
import com.school.studentmanagementservice.http.dto.CreateLoginRequest;
import com.school.studentmanagementservice.http.dto.UserResponse;
import com.school.studentmanagementservice.proxy.AuthProxy;
import com.school.studentmanagementservice.repo.IParentRepo;
import com.school.studentmanagementservice.repo.IStaffRepo;
import com.school.studentmanagementservice.repo.IStudentRepo;
import com.school.studentmanagementservice.repo.IUserMappingRepo;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public void addAdmin() {
        List<UserMapping> userMappings=iUserMappingRepo.findAllByUserType(UserType.SYS_ADMIN);
        if(userMappings.size()==0){
            UserMapping userMapping = new UserMapping();
            try {
                userMapping.setUserType(UserType.SYS_ADMIN);
                CreateLoginRequest createLoginRequestRequest = new CreateLoginRequest();
                createLoginRequestRequest.setEmail("skyhawks.app@gmail.com");
                createLoginRequestRequest.setName("SYS Admin");
                createLoginRequestRequest.setUserName("sysadmin");
                createLoginRequestRequest.setPassword(utils.randomChar(8));
                String createLoginResponse = authProxy.createLogin(createLoginRequestRequest);
                if (createLoginResponse != null) {
                    userMapping.setLogin(UUID.fromString(createLoginResponse));
                    iUserMappingRepo.save(userMapping);
                }
            } catch (Exception e) {
                throw e;
            }
        }
    }
}
