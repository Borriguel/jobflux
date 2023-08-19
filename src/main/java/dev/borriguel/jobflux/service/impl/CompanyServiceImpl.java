package dev.borriguel.jobflux.service.impl;

import dev.borriguel.jobflux.exception.ResourceNotFoundException;
import dev.borriguel.jobflux.model.entity.Company;
import dev.borriguel.jobflux.repository.CompanyRepository;
import dev.borriguel.jobflux.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository repository;

    @Override
    public Mono<Company> register(Company company) {
        return repository.save(company);
    }

    @Override
    public Mono<Company> viewCompany(String id) {
        return repository.findById(id).switchIfEmpty(Mono.error(new ResourceNotFoundException("Company not found with id -> " + id)));
    }

    @Override
    public Mono<Company> updateCompany(Company companyUpdated) {
        return viewCompany(companyUpdated.getId())
                .flatMap(company -> {
                    companyUpdated.setCreatedAt(company.getCreatedAt());
                    return repository.save(companyUpdated);
                });
    }

    @Override
    public Mono<Void> deleteCompany(String id) {
        return repository.deleteById(id).switchIfEmpty(Mono.error(new ResourceNotFoundException("Company not found with id -> " + id)));
    }

    @Override
    public Flux<Company> listCompanies() {
        return repository.findAll();
    }
}
