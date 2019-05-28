package com.me.zs.web;

import com.me.zs.service.ZsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ZsApiController {

    @Autowired
    private ZsService zsService;

    @GetMapping("/search/{key}")
    public Object search(@PathVariable(name = "key") String key) {
        List<Map<String, String>> list = zsService.search(key);
        return list;
    }

}
