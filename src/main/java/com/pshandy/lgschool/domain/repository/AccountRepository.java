package com.pshandy.lgschool.domain.repository;

import com.pshandy.lgschool.domain.model.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Integer> {

    Optional<Account> findByLogin(String login);

}
