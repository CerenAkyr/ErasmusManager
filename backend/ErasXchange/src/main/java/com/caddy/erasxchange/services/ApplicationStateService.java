package com.caddy.erasxchange.services;

import com.caddy.erasxchange.models.ApplicationState;
import com.caddy.erasxchange.models.Department;
import com.caddy.erasxchange.repositories.ApplicationStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This service handles the application state information. The server should have one ApplicationState
 * Entity at all times. If an entity doesn't exist it should be initialised
 */
@Service
public class ApplicationStateService {
    private final ApplicationStateRepository repository;

    @Autowired
    public ApplicationStateService(ApplicationStateRepository repository) {
        this.repository = repository;
        initState();
    }

    private void initState() {
        if (repository.findAll().isEmpty()) {
            for (Department dep :
                    Department.values()) {
                repository.save(new ApplicationState(dep, PlacementStatus.NO_FILE));
            }
        }
    }

    /**
     * returns the erasmus application placement state for erasmus application of a given
     * @param department of the status you want to get
     * @return the placement state of the given department
     */
    public PlacementStatus getErasmusPlacementState(Department department) {
        return repository.findByDepartment(department).getPlacementStatus();
    }

    /**
     * sets the  placement state for the erasmus application with the givent department, sets it to the passeds tate
     * @param department
     * @param state
     */
    public void setErasmusAppState(Department department, PlacementStatus state) {
        ApplicationState applicationState = repository.findByDepartment(department);
        applicationState.setPlacementStatus(state);
        repository.save(applicationState);
    }
}
