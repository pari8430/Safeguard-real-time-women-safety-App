package com.safeguard.service;

import com.safeguard.dto.IncidentRequest;
import com.safeguard.model.Incident;
import com.safeguard.model.IncidentStatus;
import com.safeguard.model.User;

import java.util.List;

public interface IncidentService {

    Incident reportIncident(Long userId, IncidentRequest request);

    Incident createSosIncident(User user, Double lat, Double lng);

    long countTotalIncidents();

    long countIncidentsByStatus(IncidentStatus status);

    long countIncidentsLast24Hours();

    List<Incident> getRecentIncidents();

    List<Incident> getIncidentsByUser(Long userId);
    List<Incident> getAllIncidents();

}
