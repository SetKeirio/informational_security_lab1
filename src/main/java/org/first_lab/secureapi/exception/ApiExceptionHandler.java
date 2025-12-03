package org.first_lab.secureapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(BadCredentialsException.class)
    public ProblemDetail handleBadCredentials() {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.UNAUTHORIZED, "Неправильные учетные данные");
        problem.setTitle("Ошибка аутентификации");
        return problem;
    }

    @ExceptionHandler(UserLoginAlreadyTakenException.class)
    public ProblemDetail handleUsernameExists(UserLoginAlreadyTakenException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
        problem.setTitle("Конфликт данных");
        return problem;
    }
}
