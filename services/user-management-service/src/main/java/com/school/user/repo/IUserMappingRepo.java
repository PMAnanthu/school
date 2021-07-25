package com.school.user.repo;

import com.school.user.dto.UserMapping;
import com.school.user.dto.UserType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IUserMappingRepo extends CrudRepository<UserMapping, UUID> {
    @Query("FROM UserMapping WHERE userId=:userId")
    UserMapping findByUserId(UUID userId);


    @Query("FROM UserMapping WHERE  school=:school AND userType=:userType")
    List<UserMapping> findAllStudentBySchoolAndType(UUID school, UserType userType);

    @Query("FROM UserMapping WHERE login=:login")
    UserMapping findByLogin(UUID login);

    @Query("FROM UserMapping WHERE userType=:userType")
    List<UserMapping> findAllByUserType(UserType userType);
}
