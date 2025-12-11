package com.safeguard.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "incidents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private Double latitude;
    private Double longitude;

    private String description;

    @Enumerated(EnumType.STRING)
    private IncidentStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime resolvedAt;
}
