package com.safeguard.service;

import com.safeguard.dto.RegisterRequest;
import com.safeguard.model.User;

public interface UserService {
    User registerUser(RegisterRequest request);
    User getUserById(Long id);


    User getCurrentUser();
}
