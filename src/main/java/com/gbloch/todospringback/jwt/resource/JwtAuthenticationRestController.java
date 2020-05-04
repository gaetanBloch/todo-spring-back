package com.gbloch.todospringback.jwt.resource;

import com.gbloch.todospringback.controllers.AbstractController;
import com.gbloch.todospringback.jwt.JwtInMemoryUserDetailsService;
import com.gbloch.todospringback.jwt.JwtTokenUtil;
import com.gbloch.todospringback.jwt.JwtUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author Gaëtan Bloch
 * Created on 03/05/2020
 */
@RestController
@RequiredArgsConstructor
public class JwtAuthenticationRestController extends AbstractController {

    @Value("${jwt.http.request.header}")
    private String tokenHeader;

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService jwtInMemoryUserDetailsService;

    @PostMapping("${jwt.get.token.uri}")
    public JwtTokenResponse createAuthenticationToken(
            @RequestBody JwtTokenRequest authenticationRequest)
            throws AuthenticationException {

        return this.authenticateAndGenerateToken(authenticationRequest);
    }

    @PostMapping("${jwt.create.user.uri}")
    public JwtTokenResponse createUserAndGetToken(
            @RequestBody JwtTokenRequest authenticationRequest) {

        JwtInMemoryUserDetailsService.addUser(
                authenticationRequest.getUsername(),
                authenticationRequest.getPassword()
        );

        return this.authenticateAndGenerateToken(authenticationRequest);
    }

    @GetMapping("${jwt.refresh.token.uri}")
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String authToken = request.getHeader(tokenHeader);
        final String token = authToken.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUserDetails user = (JwtUserDetails) jwtInMemoryUserDetailsService
                .loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token)) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new JwtTokenResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    private JwtTokenResponse authenticateAndGenerateToken(JwtTokenRequest authenticationRequest) {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = jwtInMemoryUserDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return new JwtTokenResponse(token);
    }

    private void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
        } catch (DisabledException e) {
            throw new AuthenticationException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("INVALID_CREDENTIALS", e);
        }
    }
}
