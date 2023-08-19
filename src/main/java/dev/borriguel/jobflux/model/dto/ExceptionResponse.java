package dev.borriguel.jobflux.model.dto;

import java.time.Instant;

public record ExceptionResponse(String message, int status, Instant timestamp) {
}
