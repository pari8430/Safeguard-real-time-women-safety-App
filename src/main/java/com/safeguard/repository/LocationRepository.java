package com.safeguard.repository;

import com.safeguard.model.Location;
import com.safeguard.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Optional<Location> findTopByUserOrderByTimestampDesc(User user);
}
