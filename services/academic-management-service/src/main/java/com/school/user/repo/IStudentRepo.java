package com.school.user.repo;

import com.school.user.dto.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IStudentRepo extends CrudRepository<Student, UUID> {


    @Query("FROM Student WHERE uuid=:uuid")
    Student findByUserId(UUID uuid);
}
