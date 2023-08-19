package dev.borriguel.jobflux.repository;

import dev.borriguel.jobflux.model.entity.Company;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends ReactiveMongoRepository<Company, String> {
}