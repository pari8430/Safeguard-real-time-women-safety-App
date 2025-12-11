package com.safeguard.controller;

import com.safeguard.dto.LocationUpdateRequest;
import com.safeguard.model.Location;
import com.safeguard.model.User;
import com.safeguard.service.LocationService;
import com.safeguard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/location")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;
    private final UserService userService;

    @PostMapping("/update")
    public ResponseEntity<Location> updateLocation(@RequestBody LocationUpdateRequest request) {
        User user = userService.getCurrentUser();
        Location savedLocation = locationService.updateLocation(user, request);
        return ResponseEntity.ok(savedLocation);
    }

    @GetMapping("/last")
    public ResponseEntity<Location> getLastLocation() {
        User user = userService.getCurrentUser();
        Location lastLocation = locationService.getLastLocation(user);
        return ResponseEntity.ok(lastLocation);
    }
}
