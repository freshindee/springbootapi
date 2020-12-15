package com.fitscorp.j2eemobileapi.restservices.restservices.controller;

import com.fitscorp.j2eemobileapi.restservices.restservices.dto.HomeDTO;
import com.fitscorp.j2eemobileapi.restservices.restservices.request.HomeRequest;
import com.fitscorp.j2eemobileapi.restservices.restservices.services.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    
    @Autowired
    HomeService homeService;
    
    @PostMapping(value = "/{userId}/home", produces = "application/json")
    public HomeDTO home(@PathVariable("userId") String userId, @RequestBody HomeRequest request) throws Exception {
        return homeService.initHome(0);
//        HomeResponse response = new HomeResponse();
//        try {
//            HomeDTO homeDTO = homeService.initHome(0);
//            
//            if (homeDTO!=null) {
//                response.setStatus("200");
//                response.setBody(homeDTO);
//            } else {
//                response.setErrorMessages(ResponseMsg.RETURN_EMPTY_RESULT);
//            }
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return response;
    }
    
}
