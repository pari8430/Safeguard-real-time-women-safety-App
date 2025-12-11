package com.safeguard.controller;

import com.safeguard.dto.SosRequest;
import com.safeguard.model.Incident;
import com.safeguard.model.User;
import com.safeguard.security.CustomUserDetails;
import com.safeguard.service.IncidentService;
import com.safeguard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sos")
@RequiredArgsConstructor
public class SosController {

    private final IncidentService incidentService;
    private final UserService userService;

    @PostMapping("/send")
    public ResponseEntity<?> sendSos(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody SosRequest request) {

        if (userDetails == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }

        // ✔ FIX: fetch User using ID
        User user = userService.getUserById(userDetails.getId());

        Incident incident = incidentService.createSosIncident(
                user,
                request.getLatitude(),
                request.getLongitude()
        );

        return ResponseEntity.ok(
                "SOS triggered successfully! Incident ID = " + incident.getId()
        );
    }
}
