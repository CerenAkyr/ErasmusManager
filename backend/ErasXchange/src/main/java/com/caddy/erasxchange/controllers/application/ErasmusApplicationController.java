package com.caddy.erasxchange.controllers.application;


import com.caddy.erasxchange.DTOs.ErasmusApplicationDto;
import com.caddy.erasxchange.DTOs.FullErasmusApplicationDto;
import com.caddy.erasxchange.models.Department;
import com.caddy.erasxchange.models.application.AppStatus;
import com.caddy.erasxchange.services.application.ErasmusApplicationService;
import com.github.javafaker.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("/application/erasmus")
public class ErasmusApplicationController {
    private final ErasmusApplicationService erasmusApplicationService;

    @Autowired
    public ErasmusApplicationController(ErasmusApplicationService erasmusApplicationService) {
        this.erasmusApplicationService = erasmusApplicationService;
    }

    @PutMapping("/placement/{department}")
    public ResponseEntity startPlacements(@PathVariable Department department) throws MessagingException, UnsupportedEncodingException {
        erasmusApplicationService.startPlacements(department);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ErasmusApplicationDto> getApplication(@PathVariable Integer id) {
        ErasmusApplicationDto app =  erasmusApplicationService.getApplication(id);

        return new ResponseEntity<ErasmusApplicationDto>(app,
                                                HttpStatus.OK);
    }

    @GetMapping("/bybilkentid/{bilkentId}")
    public ResponseEntity<ErasmusApplicationDto> getApplicationByBilkentId(@PathVariable Integer bilkentId) {
        ErasmusApplicationDto app =  erasmusApplicationService.getApplicationByBilkentId(bilkentId);

        return new ResponseEntity<ErasmusApplicationDto>(app,
                HttpStatus.OK);
    }

    @DeleteMapping("/{appId}/{cancelAll}")
    public ResponseEntity cancelApplication(@PathVariable Long appId, @PathVariable  boolean  cancelAll) {
        erasmusApplicationService.cancelApplication(appId,cancelAll );

        return new ResponseEntity(HttpStatus.OK);
    }


    @PutMapping("/cancellation/handle/{cancelId}/{approveNewApp}")
    public HttpStatus handleCancellation(@PathVariable  Long cancelId, @PathVariable Boolean approveNewApp) {
        erasmusApplicationService.handleCancelation(cancelId, approveNewApp);

        return HttpStatus.OK;
    }


    @GetMapping("/all")
    public ResponseEntity<List<FullErasmusApplicationDto>> getApplications() {
        List<FullErasmusApplicationDto> apps = erasmusApplicationService.getAll();
        return new ResponseEntity<>( apps, HttpStatus.OK) ;

    }

    @GetMapping("/status/{studentId}")
    public ResponseEntity<AppStatus> getApplicationStatus(@PathVariable Integer studentId) {
        AppStatus status = erasmusApplicationService.getStudentAppStatus(studentId);
        System.out.println(status);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @GetMapping("/wrongsemester/{applicationId}")
    public ResponseEntity<Boolean[]> getCorrectSemesters(@PathVariable Long applicationId) {
        Boolean[] correctness = erasmusApplicationService.getSemesterCorrect(applicationId);

        return new ResponseEntity<>(correctness, HttpStatus.OK);
    }

    @PutMapping("/change/semester/{appId}/{choiceNo}")
    @ResponseStatus(HttpStatus.OK)
    public void changeSemester(@PathVariable Long appId, @PathVariable Integer choiceNo) {
        erasmusApplicationService.changeSemester(appId, choiceNo);
    }

    @PutMapping("/cancelChoice/semester/{appId}/{choiceNo}")
    @ResponseStatus(HttpStatus.OK)
    public void cancelChoice(@PathVariable Long appId, @PathVariable Integer choiceNo) {
        erasmusApplicationService.cancelChoice(appId, choiceNo);
    }

    @GetMapping("/wrongapps/{department}")
    public ResponseEntity<List<FullErasmusApplicationDto>> getWrongAppsByDepartment(@PathVariable Department department) {
      List<FullErasmusApplicationDto> wrongApps = erasmusApplicationService.getWrongAppsByDepartment(department);
      return new ResponseEntity<>(wrongApps, HttpStatus.OK);
    }

    @PutMapping("/activate/{department}")
    @ResponseStatus(HttpStatus.OK)
    public void activateApplications(@PathVariable  Department department) {
        erasmusApplicationService.activateApplications(department);
    }

    /*
    @GetMapping("/checkallapss")
    public ResponseEntity<Boolean> checkApplicationsReady(Department department) {
        Boolean result = erasmusApplicationService.checkApplicationsAreCorrect(department);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    */

}
