package org.first_lab.secureapi.controller;

import jakarta.validation.Valid;
import org.first_lab.secureapi.dto.AuthenticationTokenResponse;
import org.first_lab.secureapi.dto.UserCredentialsRequest;
import org.first_lab.secureapi.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signin")
    public AuthenticationTokenResponse signIn(@Valid @RequestBody UserCredentialsRequest credentials) {
        return authenticationService.authenticateUser(credentials);
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthenticationTokenResponse signUp(@Valid @RequestBody UserCredentialsRequest credentials) {
        return authenticationService.registerUser(credentials);
    }
}
