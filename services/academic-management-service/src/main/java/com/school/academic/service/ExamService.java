package com.school.academic.service;

import com.school.academic.dto.Exam;
import com.school.academic.exception.NotAuthorizedException;
import com.school.academic.http.dto.ExamRequest;
import com.school.academic.repo.IExamRepo;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Service
public class ExamService {
    private final IExamRepo repo;
    private final ModelMapper mapper;
    private final Utils utils;

    public List<Exam> findAll() {
        List<Exam> exams = new ArrayList<>();
        repo.findAll().iterator().forEachRemaining(exam -> exams.add(exam));
        return exams;
    }

    public Exam findById(String uuid) {
        return repo.findById(UUID.fromString(uuid)).get();
    }

    public String create(ExamRequest request, String userId) {
        String value = null;
        if (utils.checkUserPermission(userId)) {
            Exam exam = mapper.map(request, Exam.class);
            value = repo.save(exam).getUuid().toString();
        }else{
            throw new NotAuthorizedException("User is not authorised to do this operation.");
        }
        return value;
    }


    public String update(ExamRequest request, String userId) {
        String value = null;
        if (utils.checkUserPermission(userId)) {
            Exam exam = mapper.map(request, Exam.class);
            value = repo.save(exam).getUuid().toString();
        }else{
            throw new NotAuthorizedException("User is not authorised to do this operation.");
        }
        return value;
    }

}
