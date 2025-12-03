package org.first_lab.secureapi.service;

import org.first_lab.secureapi.dto.AuthenticationTokenResponse;
import org.first_lab.secureapi.dto.UserCredentialsRequest;
import org.first_lab.secureapi.entity.UserAccount;
import org.first_lab.secureapi.exception.UserLoginAlreadyTakenException;
import org.first_lab.secureapi.repository.UserAccountRepository;
import org.first_lab.secureapi.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AuthenticationService {

    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserAccountRepository userAccountRepository,
                                 PasswordEncoder passwordEncoder,
                                 JwtTokenProvider jwtTokenProvider,
                                 AuthenticationManager authenticationManager) {
        this.userAccountRepository = userAccountRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @Transactional
    public AuthenticationTokenResponse registerUser(UserCredentialsRequest credentials) {
        if (userAccountRepository.existsByUserLogin(credentials.getLogin())) {
            throw new UserLoginAlreadyTakenException("Логин уже занят");
        }

        UserAccount user = new UserAccount();
        user.setUserLogin(credentials.getLogin());
        user.setPasswordHash(passwordEncoder.encode(credentials.getPassword()));
        user.setEmailAddress(credentials.getLogin() + "@example.com");
        user.setFullUserName("Пользователь " + credentials.getLogin());

        userAccountRepository.save(user);

        String token = jwtTokenProvider.generateAccessToken(user.getUserLogin());

        AuthenticationTokenResponse response = new AuthenticationTokenResponse();
        response.setAccessToken(token);
        response.setUserLogin(user.getUserLogin());
        response.setUserFullName(user.getFullUserName());
        response.setExpiresIn(86400000L);

        return response;
    }

    public AuthenticationTokenResponse authenticateUser(UserCredentialsRequest credentials) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.getLogin(),
                        credentials.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateAccessToken(authentication.getName());

        UserAccount user = userAccountRepository.findByUserLogin(authentication.getName())
                .orElseThrow(() -> new IllegalStateException("Пользователь не найден"));

        AuthenticationTokenResponse response = new AuthenticationTokenResponse();
        response.setAccessToken(token);
        response.setUserLogin(user.getUserLogin());
        response.setUserFullName(user.getFullUserName());
        response.setExpiresIn(86400000L);

        return response;
    }
}