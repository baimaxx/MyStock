package com.me.zs.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class ZsService {

	public Map<String, List<Map<String, String>>> getStocks(String codes) {
		
		List<String> zsList = Arrays.asList(codes.split(","));

		List<Map<String, String>> retList = new ArrayList<>();
		for (String code : zsList) {
			Map<String, String> temp = this.getStock(code);
			retList.add(temp);
		}

		Map<String, List<Map<String, String>>> retMap = new LinkedHashMap<>();
		retMap.put("resultList", retList);

		return retMap;
	}

	public Map<String, String> getStock(String code) {
		String url = "https://hq.sinajs.cn/?list=" + code;

		String retjson = HttpUtil.doGet(url);
		System.out.println(retjson);
		String[] arraystr = retjson.replace("var hq_str_" + code + "=", "").replace("\"", "").split(",");

		double now = 0;
		double yestoday = 1;

		Map<String, String> retMap = new LinkedHashMap<>();
		if (code.contains("sh")) {
			now = Double.parseDouble(arraystr[3]);
			yestoday = Double.parseDouble(arraystr[2]);
			retMap.put("name", arraystr[0]);
			retMap.put("time", arraystr[30] + " " + arraystr[31]);
		}

		if (code.contains("hk")) {
			now = Double.parseDouble(arraystr[6]);
			yestoday = Double.parseDouble(arraystr[3]);
			retMap.put("name", arraystr[1]);
			retMap.put("time", arraystr[17].replace("/", "-") + " " + arraystr[18].substring(0, 5));
		}

		if (code.contains("sz")) {
			now = Double.parseDouble(arraystr[3]);
			yestoday = Double.parseDouble(arraystr[2]);
			retMap.put("name", arraystr[0]);
			retMap.put("time", arraystr[30] + " " + arraystr[31]);
		}
		retMap.put("now", String.format("%.2f", now));
		retMap.put("percent", String.format("%.2f", (now - yestoday) / yestoday * 100));

		return retMap;
	}
}
