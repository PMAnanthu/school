package com.school.studentmanagementservice.repo;

import com.school.studentmanagementservice.dto.UserMapping;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IUserMappingRepo extends CrudRepository<UserMapping, UUID> {

}
