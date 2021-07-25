package com.school.studentmanagementservice.repo;

import com.school.studentmanagementservice.dto.Staff;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IStaffRepo extends CrudRepository<Staff, UUID> {

    @Query("FROM Staff WHERE uuid=:uuid")
    Staff findByUserId(UUID uuid);
}
