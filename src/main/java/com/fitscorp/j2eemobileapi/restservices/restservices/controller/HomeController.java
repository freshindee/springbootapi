package com.fitscorp.j2eemobileapi.restservices.restservices.controller;

import com.fitscorp.j2eemobileapi.restservices.restservices.constant.ResponseMsg;
import com.fitscorp.j2eemobileapi.restservices.restservices.dto.HomeDTO;
import com.fitscorp.j2eemobileapi.restservices.restservices.request.HomeRequest;
import com.fitscorp.j2eemobileapi.restservices.restservices.response.HomeResponse;
import com.fitscorp.j2eemobileapi.restservices.restservices.services.HomeService;
import com.google.gson.Gson;
//import java.net.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    
    @Autowired
    HomeService homeService;
    
    @Autowired
    Gson gson;
    
    @PostMapping(value = "/{userId}/home", produces = "application/json")
    public HomeResponse home(@PathVariable("userId") String userId, @RequestBody HomeRequest request) {

        HomeResponse response = new HomeResponse();

        try {
            HomeDTO homeDTO = homeService.initHome(0);
            
            if (homeDTO!=null) {
                response.setStatus("200");
                response.setBody(homeDTO);
            } else {
                response.setErrorMessages(ResponseMsg.RETURN_EMPTY_RESULT);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return response;
    }
    
}
