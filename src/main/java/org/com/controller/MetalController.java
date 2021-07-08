package org.com.controller;

import org.com.api.MetalApi;
import org.com.entity.Metal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class MetalController {

    @Autowired
    private MetalApi metalAPI;

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public List<Metal> getAllMetals(){
        return metalAPI.getAll();
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public Metal getMetalById(Long id){
        return metalAPI.getById(id);
    }
}
