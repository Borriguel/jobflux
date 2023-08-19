package dev.borriguel.jobflux.service.impl;

import dev.borriguel.jobflux.exception.ResourceNotFoundException;
import dev.borriguel.jobflux.model.entity.Candidate;
import dev.borriguel.jobflux.repository.CandidateRepository;
import dev.borriguel.jobflux.service.CandidateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepository repository;

    @Override
    public Mono<Candidate> register(Candidate candidate) {
        return repository.save(candidate);
    }

    @Override
    public Mono<Candidate> viewCandidate(String id) {
        return repository.findById(id).switchIfEmpty(Mono.error(new ResourceNotFoundException("Candidate not found with id -> " + id)));
    }

    @Override
    public Mono<Candidate> updateCandidate(Candidate candidateUpdated) {
        return viewCandidate(candidateUpdated.getId())
                .flatMap(candidate -> {
                    candidateUpdated.setCreatedAt(candidate.getCreatedAt());
                    return repository.save(candidateUpdated);
                });
    }

    @Override
    public Mono<Void> deleteCandidate(String id) {
        return repository.deleteById(id).switchIfEmpty(Mono.error(new ResourceNotFoundException("Candidate not found with id -> " + id)));
    }

    @Override
    public Flux<Candidate> listCandidates() {
        return repository.findAll();
    }
}
