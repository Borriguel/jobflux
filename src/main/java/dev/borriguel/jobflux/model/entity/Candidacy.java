package dev.borriguel.jobflux.model.entity;

import dev.borriguel.jobflux.model.enums.CandidacyStatus;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "candidacies")
public class Candidacy {
    @Id
    private String id;
    private String candidateId;
    private String jobVacancyId;
    @CreatedDate
    private LocalDate candidacyDate;
    @LastModifiedDate
    private LocalDate lastUpdate;
    private CandidacyStatus candidacyStatus;
    private String coverLetter;
}
