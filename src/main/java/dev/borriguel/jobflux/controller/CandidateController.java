package dev.borriguel.jobflux.controller;

import dev.borriguel.jobflux.model.dto.CandidateRequest;
import dev.borriguel.jobflux.model.entity.Candidate;
import dev.borriguel.jobflux.service.CandidateService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/candidates")
@AllArgsConstructor
@Slf4j
public class CandidateController {
    private final CandidateService service;

    @PostMapping
    @ResponseStatus(CREATED)
    public Mono<Candidate> register(@RequestBody CandidateRequest candidateRequest) {
        var candidate = new Candidate(null, candidateRequest.name(), candidateRequest.email(), candidateRequest.education(), null, null);
        log.info("Registering candidate {}", candidateRequest.name());
        return service.register(candidate);
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public Mono<Candidate> viewCandidate(@PathVariable String id) {
        log.info("Viewing candidate {}", id);
        return service.viewCandidate(id);
    }

    @GetMapping
    @ResponseStatus(OK)
    public Flux<Candidate> listCandidates() {
        log.info("Listing candidates");
        return service.listCandidates();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public Mono<Void> deleteCandidate(@PathVariable String id) {
        log.info("Deleting candidate {}", id);
        return service.deleteCandidate(id);
    }
}
