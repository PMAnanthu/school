package com.school.academic.repo;

import com.school.academic.dto.Score;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IScoreRepo extends CrudRepository<Score, UUID> {

}
