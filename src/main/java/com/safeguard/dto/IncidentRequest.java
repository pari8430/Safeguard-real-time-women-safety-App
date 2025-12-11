package com.safeguard.dto;

import lombok.Data;

@Data
public class IncidentRequest {
    private double latitude;
    private double longitude;
    private String description;
}
