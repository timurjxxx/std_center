package com.backend.studycenter.common.model.valueobjects;

import jakarta.persistence.Embeddable;

enum StatusType {REGISTERED, ENROLLED, IN_PROGRESS, FINISHED, CANCELED}

@Embeddable
public class Status {
    private Integer val;
    private StatusType type;
}