package com.school.studentmanagementservice.repo;

import com.school.studentmanagementservice.dto.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface IStudentRepo extends CrudRepository<Student, UUID> {

    @Query("FROM Student")
    List<Student> findAll();

    @Query("FROM Student WHERE userId=:userId")
    Student findByUserId(String userId);

    @Query("FROM Student WHERE admissionNumber=:admissionNumber")
    Student findByAdmissionNumber(String admissionNumber);

    @Query("FROM Student WHERE firstName=:firstName")
    List<Student> findByFirstName(String firstName);

    @Query("FROM Student WHERE  lastName=:lastName")
    List<Student> findByLastName(String lastName);

    @Query("FROM Student WHERE  middleName=:middleName")
    List<Student> findByMiddleName(String middleName);

    @Query("FROM Student WHERE  dateOfBirth=:dateOfBirth")
    List<Student> findDateOfBirth(LocalDate dateOfBirth);

    @Query("FROM Student WHERE  onlyFemaleChild=:onlyFemaleChild")
    List<Student> findOnlyFemaleChild(Boolean onlyFemaleChild);

    @Query("FROM Student WHERE email=:email")
    Student findByEmail(String email);

    @Query("FROM Student WHERE dateOfAdmission=:dateOfAdmission")
    List<Student> findDateOfAdmission(LocalDate dateOfAdmission);

    @Query("FROM Student  WHERE parent=:parent")
    List<Student> findByParent(UUID parent);

    @Query("FROM Student WHERE  bloodGroup=:bloodGroup")
    List<Student> findByBloodGroup(int bloodGroup);

    @Query("FROM Student WHERE admissionNumber=:admissionNumber")
    Student findIDByAdmissionNumber(String admissionNumber);
}
