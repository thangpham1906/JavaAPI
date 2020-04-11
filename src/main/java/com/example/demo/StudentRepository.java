package com.example.demo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student,Integer> {
    @Query("SELECT s FROM Student s WHERE s.age >= :age")
    List<Student> getStudentMoreThan20(int age);

    // call thu tuc
    @Query(value = "CALL myPro(:sAge);", nativeQuery = true)
    List<Student> getAllByProcedure(@Param("sAge") Integer sAge);
}
