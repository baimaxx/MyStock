package com.me.zs.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.me.zs.service.ZsService;

@RestController
@RequestMapping("/api")
public class StockController {
	
	@Autowired
	private ZsService zsService;

	@GetMapping
	public Map<String, String> hello(Map<String,String> map) {
		return zsService.getZs();
	}
	
	@GetMapping("/{code}")
	public Map<String, String> hello(@PathVariable(name = "code") String code,Map<String,String> map) {
		return zsService.getStock(code);
	}
}
