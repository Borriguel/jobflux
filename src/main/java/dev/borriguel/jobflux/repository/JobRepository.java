package dev.borriguel.jobflux.repository;

import dev.borriguel.jobflux.model.entity.Job;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends ReactiveMongoRepository<Job, String> {
}
