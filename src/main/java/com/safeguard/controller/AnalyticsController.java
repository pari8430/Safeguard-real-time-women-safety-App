package com.safeguard.controller;

import com.safeguard.model.IncidentStatus;
import com.safeguard.service.IncidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/analytics")
@RequiredArgsConstructor
public class AnalyticsController {

    private final IncidentService incidentService;

    @GetMapping("/summary")
    public ResponseEntity<?> getSummary() {

        long total = incidentService.countTotalIncidents();
        long pending = incidentService.countIncidentsByStatus(IncidentStatus.PENDING);
        long resolved = incidentService.countIncidentsByStatus(IncidentStatus.RESOLVED);
        long last24 = incidentService.countIncidentsLast24Hours();

        return ResponseEntity.ok(
                new AnalyticsSummary(total, pending, resolved, last24)
        );
    }

    record AnalyticsSummary(
            long total,
            long pending,
            long resolved,
            long last24h
    ) {}
}
