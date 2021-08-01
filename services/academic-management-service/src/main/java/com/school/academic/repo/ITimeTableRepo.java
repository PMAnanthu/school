package com.school.academic.repo;

import com.school.academic.dto.TimeTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ITimeTableRepo extends CrudRepository<TimeTable, UUID> {

}
