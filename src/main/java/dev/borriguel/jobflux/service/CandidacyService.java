package dev.borriguel.jobflux.service;

import dev.borriguel.jobflux.model.entity.Candidacy;
import dev.borriguel.jobflux.model.enums.CandidacyStatus;
import reactor.core.publisher.Mono;

public interface CandidacyService {
    Mono<Candidacy> createCandidacy(String jobVacancyId, String candidateId, Candidacy candidacy);
    Mono<Candidacy> getCandidacy(String id);
    Mono<Candidacy> updateStatus(String id, CandidacyStatus candidacyStatus);
    Mono<Boolean> existsCandidacyByCandidateId(String candidateId);
    Mono<Void> deleteCandidacy(String id);
}
