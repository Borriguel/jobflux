package dev.borriguel.jobflux.controller;

import dev.borriguel.jobflux.model.dto.CompanyRequest;
import dev.borriguel.jobflux.model.entity.Company;
import dev.borriguel.jobflux.service.CompanyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/companies")
@AllArgsConstructor
@Slf4j
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping
    @ResponseStatus(CREATED)
    public Mono<Company> register(@RequestBody CompanyRequest companyRequest) {
        var company = new Company(null, companyRequest.name(), companyRequest.description(), companyRequest.address(), companyRequest.email(), null, null);
        log.info("Registering company: {}", company);
        return companyService.register(company);
    }

    @GetMapping
    @ResponseStatus(OK)
    public Flux<Company> listCompanies() {
        log.info("Listing companies");
        return companyService.listCompanies();
    }

    @PutMapping("/{id}")
    @ResponseStatus(OK)
    public Mono<Company> updateCompany(@PathVariable String id, @RequestBody CompanyRequest companyRequest) {
        var company = new Company(id, companyRequest.name(), companyRequest.description(), companyRequest.address(), companyRequest.email(), null, null);
        log.info("Updating company: {}", company);
        return companyService.updateCompany(company);
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public Mono<Company> viewCompany(@PathVariable String id) {
        log.info("Viewing company: {}", id);
        return companyService.viewCompany(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public Mono<Void> deleteCompany(@PathVariable String id) {
        log.info("Deleting company: {}", id);
        return companyService.deleteCompany(id);
    }
}
