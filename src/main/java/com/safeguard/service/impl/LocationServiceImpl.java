package com.safeguard.service.impl;

import com.safeguard.dto.LocationUpdateRequest;
import com.safeguard.model.Location;
import com.safeguard.model.User;
import com.safeguard.repository.LocationRepository;
import com.safeguard.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    @Override
    public Location updateLocation(User user, LocationUpdateRequest request) {
        Location location = Location.builder()
                .user(user)
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .timestamp(LocalDateTime.now())
                .build();

        return locationRepository.save(location);
    }

    @Override
    public Location getLastLocation(User user) {
        return locationRepository.findTopByUserOrderByTimestampDesc(user)
                .orElse(null);
    }
}
