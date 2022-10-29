package com.pshandy.lgschool.service;

import com.pshandy.lgschool.domain.model.Account;
import com.pshandy.lgschool.domain.repository.AccountRepository;
import com.pshandy.lgschool.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.channels.AcceptPendingException;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(@Autowired AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getAccount(String login) {
        return accountRepository.findByLogin(login).orElseThrow(NotFoundException::new);
    }

    public Account createAccount(Account account) {
        if (accountRepository.existsByLogin(account.getLogin())) {
            throw new AcceptPendingException();
        }
        return accountRepository.save(account);
    }

    public List<Account> getAccounts() {
        return (List<Account>) accountRepository.findAll();
    }

    public Account updateAccount(String login, Account request) {
        Account fromDb = getAccount(login);
        fromDb.setLogin(request.getLogin());
        fromDb.setPassword(request.getPassword());
        fromDb.setRole(request.getRole());
        return accountRepository.save(fromDb);
    }

    public void deleteAccount(String login) {
        Account fromDb = getAccount(login);
        accountRepository.delete(fromDb);
    }
    
}
