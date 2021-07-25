package com.school.studentmanagementservice.service;

import com.school.studentmanagementservice.dto.Parent;
import com.school.studentmanagementservice.dto.Staff;
import com.school.studentmanagementservice.dto.Student;
import com.school.studentmanagementservice.dto.UserMapping;
import com.school.studentmanagementservice.http.dto.UserResponse;
import com.school.studentmanagementservice.repo.IParentRepo;
import com.school.studentmanagementservice.repo.IStaffRepo;
import com.school.studentmanagementservice.repo.IStudentRepo;
import com.school.studentmanagementservice.repo.IUserMappingRepo;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Data
@Service
public class UserMappingService {

    private final IUserMappingRepo iUserMappingRepo;
    private final IStudentRepo iStudentRepo;
    private final IStaffRepo iStaffRepo;
    private final IParentRepo parentRepo;

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
}
