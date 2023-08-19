package dev.borriguel.jobflux.service;

import dev.borriguel.jobflux.model.entity.Job;
import reactor.core.publisher.Mono;

public interface JobService {
    Mono<Job> register(String id, Job job);
    Mono<Job> viewJob(String id);
}
