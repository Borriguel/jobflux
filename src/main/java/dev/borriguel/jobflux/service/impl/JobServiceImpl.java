package dev.borriguel.jobflux.service.impl;

import dev.borriguel.jobflux.exception.ResourceNotFoundException;
import dev.borriguel.jobflux.model.entity.Job;
import dev.borriguel.jobflux.repository.JobRepository;
import dev.borriguel.jobflux.service.CompanyService;
import dev.borriguel.jobflux.service.JobService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobRepository repository;
    private final CompanyService companyService;

    @Override
    public Mono<Job> register(String id, Job job) {
        return companyService.viewCompany(id)
                .flatMap(company -> {
                    job.setCompanyId(company.getId());
                    return repository.save(job);
                });

    }

    @Override
    public Mono<Job> viewJob(String id) {
        return repository.findById(id).switchIfEmpty(Mono.error(new ResourceNotFoundException("Job not found with id -> " + id)));
    }
}
