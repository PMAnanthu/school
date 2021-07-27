package com.school.user.service;

import com.school.user.dto.School;
import com.school.user.dto.UserMapping;
import com.school.user.dto.UserType;
import com.school.user.exception.UserNotFountException;
import com.school.user.http.dto.CreateLoginRequest;
import com.school.user.http.dto.CreateSchool;
import com.school.user.proxy.AuthProxy;
import com.school.user.repo.ISchoolRepo;
import com.school.user.repo.IUserMappingRepo;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Data
@Service
public class SchoolService {


    private final ModelMapper modelMapper;
    private final ISchoolRepo iSchoolRepo;
    private final AuthProxy authProxy;
    private final Utils utils;
    private final IUserMappingRepo iUserMappingRepo;

    public List<School> findAll() {
        List<School> schools = new ArrayList<>();
        iSchoolRepo.findAll().iterator().forEachRemaining(school -> schools.add(school));
        return schools;
    }


    public School findSchool(UUID uuid) throws UserNotFountException {
        Optional<School> school = iSchoolRepo.findById(uuid);
        return school.orElseThrow(()->new UserNotFountException("School Not Found with for ID " + uuid.toString()));
    }

    public String insertSchool(CreateSchool createRequest) {
        try {
            School school = iSchoolRepo.save(modelMapper.map(createRequest, School.class));
            if (school != null) {
                try {
                    CreateLoginRequest createLoginRequestRequest = new CreateLoginRequest();
                    createLoginRequestRequest.setEmail(createRequest.getEmail());
                    createLoginRequestRequest.setName(createRequest.getName());
                    createLoginRequestRequest.setUserName(createRequest.getUserName());
                    createLoginRequestRequest.setPassword(utils.randomChar(8));
                    createLoginRequestRequest.setSchoolName(createRequest.getName());
                    String createLoginResponse = authProxy.createLogin(createLoginRequestRequest);
                    if (createLoginResponse != null) {
                        UserMapping userMapping = new UserMapping();
                        userMapping.setUserType(UserType.SCHOOL);
                        userMapping.setLogin(UUID.fromString(createLoginResponse));
                        userMapping.setUserId(school.getUuid());
                        userMapping.setSchool(school.getUuid());
                        iUserMappingRepo.save(userMapping);
                        return createLoginResponse;
                    }
                }catch (Exception e){
                    iSchoolRepo.delete(school);
                    throw e;
                }

            }
        } catch (Exception e) {
            throw e;
        }
        return null;
    }

    public UUID findSchoolForUser(String userId) {
        return iUserMappingRepo.findByLogin(UUID.fromString(userId)).getSchool();
    }
}
