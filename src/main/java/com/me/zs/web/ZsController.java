package com.me.zs.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.me.zs.service.ZsService;

@Controller
@RequestMapping
public class ZsController {
	
	@Autowired
	private ZsService zsService;

	@GetMapping
	public String hello(Map<String,String> map) {
		Map<String,String> map2 = zsService.getZs();
		map.putAll(map2);
		return "/ftl/zs";
	}
	
	@GetMapping("/{code}")
	public Object hello(@PathVariable(name = "code") String code,Map<String,String> map) {
		Map<String,String> map2 = zsService.getStock(code);
		map.putAll(map2);
		return "/ftl/stock";
	}
}
