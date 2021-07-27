package com.school.user.repo;

import com.school.user.dto.School;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ISchoolRepo extends CrudRepository<School, UUID> {
    @Query("FROM School WHERE uuid=:uuid")
    School findByUserId(UUID uuid);
}
