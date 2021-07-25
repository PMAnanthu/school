package com.school.user.repo;

import com.school.user.dto.Parent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IParentRepo extends CrudRepository<Parent, UUID> {

    @Query("FROM Parent WHERE uuid=:uuid")
    Parent findByUserId(UUID uuid);
}
