package com.fitscorp.j2eemobileapi.restservices.restservices.services;

import com.fitscorp.j2eemobileapi.restservices.restservices.dto.HomeDTO;
import org.springframework.stereotype.Service;

@Service
public interface HomeService {
    
    public HomeDTO initHome(Integer userId) throws Exception;
    
}
