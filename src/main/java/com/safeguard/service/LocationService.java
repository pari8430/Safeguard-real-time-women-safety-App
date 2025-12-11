package com.safeguard.service;

import com.safeguard.dto.LocationUpdateRequest;
import com.safeguard.model.Location;
import com.safeguard.model.User;

public interface LocationService {
    Location updateLocation(User user, LocationUpdateRequest request);

    Location getLastLocation(User user);
}
