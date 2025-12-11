package com.safeguard.repository;

import com.safeguard.model.Incident;
import com.safeguard.model.IncidentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface IncidentRepository extends JpaRepository<Incident, Long> {

    long countByStatus(IncidentStatus status);

    long countByCreatedAtAfter(LocalDateTime time);

    List<Incident> findTop10ByOrderByCreatedAtDesc();

    List<Incident> findByUserId(Long userId);
}

