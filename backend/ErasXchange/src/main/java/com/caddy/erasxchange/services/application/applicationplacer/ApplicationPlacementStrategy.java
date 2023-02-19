package com.caddy.erasxchange.services.application.applicationplacer;

import com.caddy.erasxchange.models.Department;
import com.caddy.erasxchange.models.application.Application;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface ApplicationPlacementStrategy<App extends Application> {


    void startPlacements(List<App> applications) ;
    void startPlacements(List<App> applications, Department department) throws MessagingException, UnsupportedEncodingException;
}
