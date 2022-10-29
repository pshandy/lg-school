package com.pshandy.lgschool.security;

import com.pshandy.lgschool.domain.model.Account;
import com.pshandy.lgschool.domain.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class HUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Supplier<UsernameNotFoundException> s =
                () -> new UsernameNotFoundException(
                        "Problem during authentication!");
        Account account = accountRepository
                .findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Problem during authentication!")
                );
        return new HUserDetails(account);
    }
}
