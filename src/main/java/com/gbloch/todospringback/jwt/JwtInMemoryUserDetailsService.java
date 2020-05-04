package com.gbloch.todospringback.jwt;

import com.gbloch.todospringback.jwt.resource.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gaëtan Bloch
 * Created on 03/05/2020
 */
@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

    static long id = 0L;
    static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();

    static {
        inMemoryUserList.add(new JwtUserDetails(
                ++id,
                "gbloch",
                "$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e",
                "ROLE_USER_2"
        ));
        inMemoryUserList.add(new JwtUserDetails(
                ++id,
                "admin",
                "$2y$10$4RUgf7fKeaK3DlE2R4GIweAw81FhiP3XMvxKn7Mkevm7su8Ix8YWm",
                "ROLE_USER_2"
        ));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return inMemoryUserList.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException(
                            String.format("USER_NOT_FOUND '%s'.", username)
                    );
                });
    }

    public static void addUser(String username, String password) {
        if (inMemoryUserList.stream().anyMatch(jwtUserDetails ->
                jwtUserDetails.getUsername().equals(username))) {
            throw new AuthenticationException("Username [" + username + "] already in use");
        }

        inMemoryUserList.add(new JwtUserDetails(
                ++id,
                username,
                new BCryptPasswordEncoder().encode(password),
                "ROLE_USER_2"
        ));
    }
}
