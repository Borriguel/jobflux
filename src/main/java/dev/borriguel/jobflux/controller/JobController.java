package dev.borriguel.jobflux.controller;

import dev.borriguel.jobflux.model.dto.JobRequest;
import dev.borriguel.jobflux.model.entity.Job;
import dev.borriguel.jobflux.service.JobService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/jobs")
@AllArgsConstructor
@Slf4j
public class JobController {
    private final JobService service;

    @PostMapping("/{id}")
    @ResponseStatus(CREATED)
    public Mono<Job> register(@PathVariable String id, @RequestBody JobRequest jobRequest) {
        var job = new Job(null, jobRequest.title(), jobRequest.description(), jobRequest.salary(), jobRequest.location(), jobRequest.type(), jobRequest.category(), null, jobRequest.expiresAt(), null, null);
        log.info("Registering job vacancy for company {}", id);
        return service.register(id, job);
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public Mono<Job> viewJobVacancy(@PathVariable String id) {
        log.info("Viewing job vacancy {}", id);
        return service.viewJob(id);
    }
}
