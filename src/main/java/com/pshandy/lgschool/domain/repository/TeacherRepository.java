package com.pshandy.lgschool.domain.repository;

import com.pshandy.lgschool.domain.model.Account;
import com.pshandy.lgschool.domain.model.Teacher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TeacherRepository extends CrudRepository<Teacher, Integer> {

    @Query(value = "SELECT CASE WHEN COUNT(t) > 0 THEN 'true' ELSE 'false' END FROM Teacher t WHERE t.account.login = ?1")
    boolean existsByLogin(String login);

}
