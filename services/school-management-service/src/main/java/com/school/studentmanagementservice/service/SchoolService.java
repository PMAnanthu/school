package com.school.studentmanagementservice.service;

import com.school.studentmanagementservice.dto.School;
import com.school.studentmanagementservice.exception.UserNotFountException;
import com.school.studentmanagementservice.http.dto.CreateManger;
import com.school.studentmanagementservice.http.dto.CreateSchool;
import com.school.studentmanagementservice.proxy.UserServiceProxy;
import com.school.studentmanagementservice.repo.ISchoolRepo;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Service
public class SchoolService {


    private final ModelMapper modelMapper;
    private final ISchoolRepo iSchoolRepo;
    private final UserServiceProxy serviceProxy;

    public List<School> findAll() {
        List<School> schools = new ArrayList<>();
        iSchoolRepo.findAll().iterator().forEachRemaining(school -> schools.add(school));
        return schools;
    }


    public School findSchool(UUID uuid) throws UserNotFountException {
        School school = iSchoolRepo.findByUserId(uuid);
        if (school == null)
            throw new UserNotFountException("School Not Found with for ID " + uuid.toString());
        return school;
    }

    public String insertSchool(CreateSchool createRequest) {
        try {
            School school = iSchoolRepo.save(modelMapper.map(createRequest, School.class));
            if (school != null) {
                CreateManger createManger=createRequest.getManger();
                createManger.setSchool(school.getUuid().toString());
                String mangerID=serviceProxy.createUser(createManger);
                if(mangerID!=null && !mangerID.isEmpty()) {
                    return school.getUuid().toString();
                }else {
                    iSchoolRepo.delete(school);
                    return "Unable to create manger";
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return null;
    }
}
