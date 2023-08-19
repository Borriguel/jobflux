package dev.borriguel.jobflux.controller;

import dev.borriguel.jobflux.model.dto.CandidacyRequest;
import dev.borriguel.jobflux.model.dto.CandidacyUpdateRequest;
import dev.borriguel.jobflux.model.entity.Candidacy;
import dev.borriguel.jobflux.service.CandidacyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/candidacy")
@AllArgsConstructor
@Slf4j
public class CandidacyController {
    private final CandidacyService service;

    @PostMapping("/{jobVacancyId}/{candidateId}")
    @ResponseStatus(CREATED)
    public Mono<Candidacy> createCandidacy(@PathVariable String jobVacancyId, @PathVariable String candidateId, @RequestBody CandidacyRequest candidacyRequest) {
        var candidacy = new Candidacy(null, null, null, null, null, null, candidacyRequest.coverLetter());
        log.info("Creating candidacy for job vacancy {} and candidate {}", jobVacancyId, candidateId);
        return service.createCandidacy(jobVacancyId, candidateId, candidacy);
    }

    @GetMapping("/{id}")
    public Mono<Candidacy> getCandidacy(@PathVariable String id) {
        log.info("Getting candidacy {}", id);
        return service.getCandidacy(id);
    }

    @PutMapping("/{id}")
    public Mono<Candidacy> updateStatus(@PathVariable String id, @RequestBody CandidacyUpdateRequest updateRequest) {
        log.info("Updating candidacy {} with status {}", id, updateRequest.candidacyStatus());
        return service.updateStatus(id, updateRequest.candidacyStatus());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public Mono<Void> deleteCandidacy(@PathVariable String id) {
        log.info("Deleting candidacy {}", id);
        return service.deleteCandidacy(id);
    }
}
