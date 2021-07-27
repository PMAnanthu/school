package com.school.user.repo;

import com.school.user.dto.Staff;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IStaffRepo extends CrudRepository<Staff, UUID> {

    @Query("FROM Staff WHERE uuid=:uuid")
    Staff findByUserId(UUID uuid);
}
