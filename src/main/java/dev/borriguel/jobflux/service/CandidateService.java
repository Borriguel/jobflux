package dev.borriguel.jobflux.service;

import dev.borriguel.jobflux.model.entity.Candidate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CandidateService {
    Mono<Candidate> register(Candidate candidate);
    Mono<Candidate> viewCandidate(String id);
    Mono<Candidate> updateCandidate(Candidate candidate);
    Mono<Void> deleteCandidate(String id);
    Flux<Candidate> listCandidates();
}
