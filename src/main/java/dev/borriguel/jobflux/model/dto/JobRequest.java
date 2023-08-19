package dev.borriguel.jobflux.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record JobRequest(String title, String description, BigDecimal salary, String location, String type, String category, LocalDate expiresAt) {
}
