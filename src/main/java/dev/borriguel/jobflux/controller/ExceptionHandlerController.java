package dev.borriguel.jobflux.controller;

import dev.borriguel.jobflux.exception.BadRequestException;
import dev.borriguel.jobflux.exception.ResourceNotFoundException;
import dev.borriguel.jobflux.model.dto.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerController {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    ExceptionResponse handleResourceNotFoundException(ResourceNotFoundException e) {
        log.info("Handling ResourceNotFoundException: {}", e.getMessage());
        return new ExceptionResponse(e.getMessage(), NOT_FOUND.value(), Instant.now());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(BAD_REQUEST)
    ExceptionResponse handleBadRequestException(BadRequestException e) {
        log.info("Handling BadRequestException: {}", e.getMessage());
        return new ExceptionResponse(e.getMessage(), BAD_REQUEST.value(), Instant.now());
    }
}
