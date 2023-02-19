package com.caddy.erasxchange.repositories;

import com.caddy.erasxchange.models.ApplicationState;
import com.caddy.erasxchange.models.Department;
import com.caddy.erasxchange.services.PlacementStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationStateRepository extends JpaRepository<ApplicationState, Long> {
    ApplicationState findByDepartment(Department department);
}