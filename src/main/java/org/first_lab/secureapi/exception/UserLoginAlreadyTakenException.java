package org.first_lab.secureapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserLoginAlreadyTakenException extends RuntimeException {
    public UserLoginAlreadyTakenException(String message) {
        super(message);
    }
}
