package com.caddy.erasxchange.DTOs;

import com.caddy.erasxchange.models.Department;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.caddy.erasxchange.models.users.Student} entity
 */
@Data
public class StudentDto implements Serializable {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Integer bilkentId;
    private final Department department;
    private final Double exchangeScore;
    private final Double gpa;
}