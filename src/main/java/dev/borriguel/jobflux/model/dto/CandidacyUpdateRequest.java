package dev.borriguel.jobflux.model.dto;

import dev.borriguel.jobflux.model.enums.CandidacyStatus;

public record CandidacyUpdateRequest(String id, CandidacyStatus candidacyStatus) {
}
