package org.com.controller;

import org.com.api.IngotApi;
import org.com.entity.Ingot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class IngotController {

    @Autowired
    private IngotApi ingotApi;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Ingot> getAllIngotsOnMetalsByDate(String date){
        return ingotApi.getAllByDate(date);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Ingot> getIngotsOnMetalsByIdAndDate(Long id, String date){
        return ingotApi.getByIdAndDate(id, date);
    }
}
