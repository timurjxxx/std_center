package com.backend.studycenter.common.model.valueobjects;

import jakarta.persistence.Embeddable;

enum DurationType {Day, Month, Year}

@Embeddable
public class Duration {
    private Integer value;
    private DurationType type;
}