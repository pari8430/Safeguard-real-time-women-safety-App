package com.safeguard.controller;

import com.safeguard.dto.IncidentRequest;
import com.safeguard.model.Incident;
import com.safeguard.security.CustomUserDetails;
import com.safeguard.service.IncidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;



@RestController
@RequestMapping("/api/incidents")
@RequiredArgsConstructor
public class IncidentController {

    private final IncidentService incidentService;

    @PostMapping("/report")
    public ResponseEntity<?> reportIncident(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody IncidentRequest request
    ) {
        if (userDetails == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }

        Incident incident = incidentService.reportIncident(userDetails.getId(), request);
        return ResponseEntity.ok(incident);
    }
    @GetMapping("/my")
    public ResponseEntity<?> getMyIncidents(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(incidentService.getIncidentsByUser(userDetails.getId()));
    }
    @GetMapping("/recent")
    public ResponseEntity<?> recent() {
        return ResponseEntity.ok(incidentService.getRecentIncidents());
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public List<Incident> all() {
        return incidentService.getAllIncidents();

    }


}
