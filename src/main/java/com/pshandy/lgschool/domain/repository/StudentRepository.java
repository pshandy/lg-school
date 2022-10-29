package com.pshandy.lgschool.domain.repository;

import com.pshandy.lgschool.domain.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Integer> {

    @Query(value = "SELECT CASE WHEN COUNT(s) > 0 THEN 'true' ELSE 'false' END FROM Student s WHERE s.account.login = ?1")
    boolean existsByLogin(String login);

}
