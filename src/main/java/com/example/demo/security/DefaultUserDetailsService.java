package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DefaultUserDetailsService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder bCrypt;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("loadUserByUsername");
        Account account = findUserByUsername(username);
        if (account != null) {
            return toUserDetail(account);
        } else {
            throw new UsernameNotFoundException("User not found.");
        }

    }


    private UserDetails toUserDetail(Account account){
        System.out.println(account.toString());
        return User.withUsername(account.getAccountId())
                .password(account.getPassword())
                .roles(account.getRoles())
                .build();
    }
    private List<SimpleGrantedAuthority> getAuthority() {
        return Collections.emptyList();
    }

    private Account findUserByUsername(String username) {
            return new Account(username, bCrypt.encode("1234"), "CUSTOMER");
//        }
    }
}