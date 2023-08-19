package dev.borriguel.jobflux.repository;

import dev.borriguel.jobflux.model.entity.Candidacy;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CandidacyRepository extends ReactiveMongoRepository<Candidacy, String> {
    Mono<Boolean> existsCandidacyByCandidateId(String candidateId);
}
