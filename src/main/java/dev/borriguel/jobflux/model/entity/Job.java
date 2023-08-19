package dev.borriguel.jobflux.model.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "jobs")
public class Job {
    @Id
    private String id;
    private String title;
    private String description;
    private BigDecimal salary;
    private String location;
    private String type;
    private String category;
    @CreatedDate
    private LocalDate publishedAt;
    private LocalDate expiresAt;
    @LastModifiedDate
    private LocalDate updatedAt;
    private String companyId;

}