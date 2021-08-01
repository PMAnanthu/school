package com.school.academic.service;

import com.school.academic.dto.Division;
import com.school.academic.exception.NotAuthorizedException;
import com.school.academic.http.dto.DivisionRequest;
import com.school.academic.http.dto.UserRequest;
import com.school.academic.repo.IDivisionRepo;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Service
public class DivisionService {
    private final IDivisionRepo repo;
    private final ModelMapper mapper;
    private final Utils utils;

    public List<Division> findAll() {
        List<Division> divisions = new ArrayList<>();
        repo.findAll().iterator().forEachRemaining(division -> divisions.add(division));
        return divisions;
    }

    public Division findById(String division) {
        return repo.findById(UUID.fromString(division)).get();
    }

    public String create(DivisionRequest request, String userId) {
        String value = null;
        if (utils.checkUserPermission(userId)) {
            Division division = mapper.map(request, Division.class);
            value = repo.save(division).getUuid().toString();
        }else{
            throw new NotAuthorizedException("User is not authorised to do this operation.");
        }
        return value;
    }


    public String update(DivisionRequest request, String userId) {
        String value = null;
        if (utils.checkUserPermission(userId)) {
            Division division = mapper.map(request, Division.class);
            value = repo.save(division).getUuid().toString();
        }else{
            throw new NotAuthorizedException("User is not authorised to do this operation.");
        }
        return value;
    }

    public String addStudent(UserRequest request, String userId) {
        String value = null;
        if (utils.checkUserPermission(userId) &&  utils.checkValidStudent(request.getObj())) {
            Division division =repo.findById(request.getDivision()).get();
            if(division!=null && !division.getStudents().contains(request.getObj())) {
                division.getStudents().add(request.getObj());
                value = repo.save(division).getUuid().toString();
            }
        }else{
            throw new NotAuthorizedException("User is not authorised to do this operation.");
        }
        return value;
    }

    public String removeStudent(UserRequest request, String userId) {
        String value = null;
        if (utils.checkUserPermission(userId) &&  utils.checkValidStudent(request.getObj())) {
            Division division =repo.findById(request.getDivision()).get();
            if(division!=null) {
                division.getStudents().remove(request.getObj());
                value = repo.save(division).getUuid().toString();
            }
        }else{
            throw new NotAuthorizedException("User is not authorised to do this operation.");
        }
        return value;
    }

    public String updateTeacher(UserRequest request, String userId) {
        String value = null;
        if (utils.checkUserPermission(userId)&&  utils.checkValidTeacher(request.getObj())) {
            Division division =repo.findById(request.getDivision()).get();
            if(division!=null) {
                division.setInCharge(request.getObj());
                value = repo.save(division).getUuid().toString();
            }
        }else{
            throw new NotAuthorizedException("User is not authorised to do this operation.");
        }
        return value;
    }
}
