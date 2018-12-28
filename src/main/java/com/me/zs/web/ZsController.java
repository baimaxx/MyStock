package com.me.zs.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.me.zs.service.ZsService;

@Controller
@RequestMapping
public class ZsController {
	
	@Value("${homepage.stock}")
	private String homepageStocks;
	
	@Autowired
	private ZsService zsService;

	@GetMapping
	public String homepage(Map<String, List<Map<String, String>>> map) {
		Map<String, List<Map<String, String>>> map2 = zsService.getStocks(homepageStocks);
		map.putAll(map2);
		return "/ftl/zs";
	}
	
	@GetMapping("/{code}")
	public Object getStocks(@PathVariable(name = "code") String code,Map<String, List<Map<String, String>>> map) {
		Map<String, List<Map<String, String>>> map2 = zsService.getStocks(code);
		map.putAll(map2);
		return "/ftl/zs";
	}
	
}
