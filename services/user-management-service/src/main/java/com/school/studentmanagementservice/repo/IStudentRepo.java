package com.school.studentmanagementservice.repo;

import com.school.studentmanagementservice.dto.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IStudentRepo extends CrudRepository<Student, UUID> {


    @Query("FROM Student WHERE uuid=:uuid")
    Student findNameByUserId(UUID uuid);
}
