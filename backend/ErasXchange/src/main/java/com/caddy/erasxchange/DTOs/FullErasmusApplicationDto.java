package com.caddy.erasxchange.DTOs;

import com.caddy.erasxchange.DTOs.ErasmusUniversityDto;
import com.caddy.erasxchange.DTOs.StudentDto;
import com.caddy.erasxchange.models.Semester;
import com.caddy.erasxchange.models.application.AppStatus;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.caddy.erasxchange.models.application.ErasmusApplication} entity
 */
@Data
public class FullErasmusApplicationDto implements Serializable {
    private final Long id;
    private final StudentDto student;
    private final AppStatus status;
    private final Semester semester1;
    private final Semester semester2;
    private final Semester semester3;
    private final Semester semester4;
    private final Semester semester5;
    private final ErasmusUniversityDto choice1;
    private final ErasmusUniversityDto choice2;
    private final ErasmusUniversityDto choice3;
    private final ErasmusUniversityDto choice4;
    private final ErasmusUniversityDto choice5;
    private final ErasmusUniversityDto placedSchool;
}