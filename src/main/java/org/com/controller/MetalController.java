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

    @PreAuthorize("hasRole('ROLE_USER')")
    public List<Metal> getAllMetals(){
        return metalAPI.getAll();
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    public Metal getMetalById(Long id){
        return metalAPI.getById(id);
    }
}
