package dev.borriguel.jobflux.service;

import dev.borriguel.jobflux.model.entity.Company;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CompanyService {
    Mono<Company> register(Company company);

    Mono<Company> viewCompany(String id);

    Mono<Company> updateCompany(Company company);

    Mono<Void> deleteCompany(String id);

    Flux<Company> listCompanies();
}
