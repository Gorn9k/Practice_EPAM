package org.com.Controller;

import org.com.Api.MetalApi;
import org.com.Entity.Metal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class MetalController {

    @Autowired
    private MetalApi metalAPI;

    public List<Metal> getAllMetals(){
        return metalAPI.getAll();
    }

    public Metal getMetalById(Long id){
        return metalAPI.getById(id);
    }
}
