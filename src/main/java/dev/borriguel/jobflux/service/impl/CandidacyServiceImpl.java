package dev.borriguel.jobflux.service.impl;

import dev.borriguel.jobflux.exception.BadRequestException;
import dev.borriguel.jobflux.exception.ResourceNotFoundException;
import dev.borriguel.jobflux.model.entity.Candidacy;
import dev.borriguel.jobflux.model.enums.CandidacyStatus;
import dev.borriguel.jobflux.repository.CandidacyRepository;
import dev.borriguel.jobflux.service.CandidacyService;
import dev.borriguel.jobflux.service.CandidateService;
import dev.borriguel.jobflux.service.JobService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class CandidacyServiceImpl implements CandidacyService {
    private final CandidacyRepository repository;
    private final JobService jobService;
    private final CandidateService candidateService;

    @Override
    public Mono<Candidacy> createCandidacy(String jobVacancyId, String candidateId, Candidacy candidacy) {
        return jobService.viewJob(jobVacancyId)
                .flatMap(job -> {
                    if (job.getExpiresAt().isBefore(LocalDate.now()))
                        return Mono.error(new BadRequestException("Job vacancy expired"));
                    candidacy.setJobVacancyId(jobVacancyId);
                    return candidateService.viewCandidate(candidateId)
                            .flatMap(candidate -> existsCandidacyByCandidateId(candidateId)
                                    .flatMap(exists -> {
                                        if (exists)
                                            return Mono.error(new BadRequestException("Candidate already applied"));
                                        candidacy.setCandidateId(candidateId);
                                        candidacy.setCandidacyStatus(CandidacyStatus.PENDING);
                                        return repository.save(candidacy);
                                    }));
                });
    }

    @Override
    public Mono<Candidacy> getCandidacy(String id) {
        return repository.findById(id).switchIfEmpty(Mono.error(new RuntimeException("Candidacy not found")));
    }

    @Override
    public Mono<Candidacy> updateStatus(String id, CandidacyStatus candidacyStatus) {
        return getCandidacy(id).flatMap(candidacy -> {
            candidacy.setCandidacyStatus(candidacyStatus);
            return repository.save(candidacy);
        });
    }

    @Override
    public Mono<Boolean> existsCandidacyByCandidateId(String candidateId) {
        return repository.existsCandidacyByCandidateId(candidateId);
    }

    @Override
    public Mono<Void> deleteCandidacy(String id) {
        return repository.deleteById(id).switchIfEmpty(Mono.error(new ResourceNotFoundException("Candidacy not found with -> " + id)));
    }
}
