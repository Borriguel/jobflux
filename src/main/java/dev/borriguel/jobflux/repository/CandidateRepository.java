package dev.borriguel.jobflux.repository;

import dev.borriguel.jobflux.model.entity.Candidate;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends ReactiveMongoRepository<Candidate, String> {
}