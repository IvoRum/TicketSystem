package com.diploma.ticket.system.dao;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
@Repository
public class UserDao {
//TODO replace whit whit a db connection
    private final static List<UserDetails> APPLICATION_USERS
            = Arrays.asList(
            new User(
                    "ivo@mail.com",
                    "12345678",
                    Collections.singleton(new SimpleGrantedAuthority("USER"))

            ),
            new User(
                    "ivoAdmin@mail.com",
                    "12345678",
                    Collections.singleton(new SimpleGrantedAuthority("ADMIN"))

            )
    );

    public UserDetails findUserByEmail(String email){
        return APPLICATION_USERS
                .stream()
                .filter(u->u.getUsername().equals(email))
                .findFirst()
                .orElseThrow(()-> new UsernameNotFoundException("No User whit that email"))
        ;
    }
}
