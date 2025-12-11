package com.safeguard.service.impl;

import com.safeguard.dto.IncidentRequest;
import com.safeguard.model.Incident;
import com.safeguard.model.IncidentStatus;
import com.safeguard.model.User;
import com.safeguard.repository.IncidentRepository;
import com.safeguard.repository.UserRepository;
import com.safeguard.service.IncidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IncidentServiceImpl implements IncidentService {

    private final IncidentRepository incidentRepository;
    private final UserRepository userRepository;

    @Override
    public Incident reportIncident(Long userId, IncidentRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Incident incident = Incident.builder()
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .description(request.getDescription())
                .status(IncidentStatus.PENDING)
                .user(user)
                .createdAt(LocalDateTime.now())
                .build();

        return incidentRepository.save(incident);
    }

    @Override
    public Incident createSosIncident(User user, Double lat, Double lng) {

        Incident incident = Incident.builder()
                .latitude(lat)
                .longitude(lng)
                .description("SOS Triggered") // FIXED
                .status(IncidentStatus.PENDING)
                .user(user)
                .createdAt(LocalDateTime.now())
                .build();

        return incidentRepository.save(incident);
    }

    @Override
    public long countTotalIncidents() {
        return incidentRepository.count();
    }

    @Override
    public long countIncidentsByStatus(IncidentStatus status) {
        return incidentRepository.countByStatus(status);
    }

    @Override
    public long countIncidentsLast24Hours() {
        return incidentRepository.countByCreatedAtAfter(LocalDateTime.now().minusHours(24));
    }

    @Override
    public List<Incident> getRecentIncidents() {
        return incidentRepository.findTop10ByOrderByCreatedAtDesc();
    }

    @Override
    public List<Incident> getIncidentsByUser(Long userId) {
        return incidentRepository.findByUserId(userId);
    }
    @Override
    public List<Incident> getAllIncidents() {
        return incidentRepository.findAll();
    }

}


