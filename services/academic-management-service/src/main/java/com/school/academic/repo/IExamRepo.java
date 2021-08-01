package com.school.academic.repo;

import com.school.academic.dto.Exam;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IExamRepo extends CrudRepository<Exam, UUID> {

}
