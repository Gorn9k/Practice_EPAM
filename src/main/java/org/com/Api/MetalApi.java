package org.com.Api;

import org.com.Entity.Metal;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(name = "nbrbMetal", url = "https://www.nbrb.by", path = "/api/metals")
public interface MetalApi {

    @RequestMapping(method = GET)
    List<Metal> getAll();

    @RequestMapping(method = GET, value = "/{Id}")
    Metal getById(@PathVariable Long Id);

}
