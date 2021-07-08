package org.com.api;

import org.com.entity.Ingot;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(name = "nbrbIngot", url = "https://www.nbrb.by", path = "/api/ingots/prices")
public interface IngotApi {

    @RequestMapping(method = GET)
    List<Ingot> getAllByDate(@RequestParam("ondate") String ondate);

    @RequestMapping(method = GET, value = "/{metal_id}")
    List<Ingot> getByIdAndDate(@PathVariable Long metal_id, @RequestParam("ondate") String ondate);

}
