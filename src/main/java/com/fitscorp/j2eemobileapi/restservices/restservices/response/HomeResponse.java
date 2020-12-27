package com.fitscorp.j2eemobileapi.restservices.restservices.response;

import com.fitscorp.j2eemobileapi.restservices.restservices.dto.HomeDTO;


public class HomeResponse extends APIResponse{
    
    private HomeDTO body;

    public HomeDTO getBody() {
        return body;
    }

    public void setBody(HomeDTO body) {
        this.body = body;
    }
    
    
    
}
