package com.school.academic.service;

import com.school.academic.dto.Score;
import com.school.academic.exception.NotAuthorizedException;
import com.school.academic.http.dto.ScoreRequest;
import com.school.academic.repo.IScoreRepo;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Service
public class ScoreService {
    private final IScoreRepo repo;
    private final ModelMapper mapper;
    private final Utils utils;

    public List<Score> findAll() {
        List<Score> scores = new ArrayList<>();
        repo.findAll().iterator().forEachRemaining(score -> scores.add(score));
        return scores;
    }

    public Score findById(String uuid) {
        return repo.findById(UUID.fromString(uuid)).get();
    }

    public String create(ScoreRequest request, String userId) {
        String value = null;
        if (utils.checkUserPermission(userId)) {
            Score score = mapper.map(request, Score.class);
            value = repo.save(score).getUuid().toString();
        }else{
            throw new NotAuthorizedException("User is not authorised to do this operation.");
        }
        return value;
    }


    public String update(ScoreRequest request, String userId) {
        String value = null;
        if (utils.checkUserPermission(userId)) {
            Score score = mapper.map(request, Score.class);
            value = repo.save(score).getUuid().toString();
        }else{
            throw new NotAuthorizedException("User is not authorised to do this operation.");
        }
        return value;
    }

}
