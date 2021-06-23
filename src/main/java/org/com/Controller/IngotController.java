package org.com.Controller;

import org.com.Api.IngotApi;
import org.com.Entity.Ingot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class IngotController {

    @Autowired
    private IngotApi ingotApi;

    public List<Ingot> getAllIngotsOnMetalsByDate(String date){
        return ingotApi.getAllByDate(date);
    }

    public List<Ingot> getIngotsOnMetalsByIdAndDate(Long id, String date){
        return ingotApi.getByIdAndDate(id, date);
    }
}
