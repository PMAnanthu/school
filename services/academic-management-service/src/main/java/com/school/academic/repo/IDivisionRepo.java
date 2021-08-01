package com.school.academic.repo;

import com.school.academic.dto.Division;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IDivisionRepo extends CrudRepository<Division, UUID> {

}
