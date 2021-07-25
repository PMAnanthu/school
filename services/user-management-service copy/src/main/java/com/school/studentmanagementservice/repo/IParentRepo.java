package com.school.studentmanagementservice.repo;

import com.school.studentmanagementservice.dto.Parent;
import com.school.studentmanagementservice.dto.Staff;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IParentRepo extends CrudRepository<Parent, UUID> {

    @Query("FROM Parent WHERE uuid=:uuid")
    Parent findByUserId(UUID uuid);
}
