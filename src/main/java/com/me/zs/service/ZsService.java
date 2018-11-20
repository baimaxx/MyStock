package com.me.zs.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class ZsService {

	public Map<String, String> getZs() {
		String sh001url = "https://hq.sinajs.cn/?list=sh000001";
		String hs300url = "https://hq.sinajs.cn/?list=sh000300";
		
		String sh001retjson = HttpUtil.doGet(sh001url);
		String hs300retjson = HttpUtil.doGet(hs300url);
		String[] sh001arraystr = sh001retjson.replace("var hq_str_sh000001=", "").replace("\"", "").split(",");
		String[] hs300arraystr = hs300retjson.replace("var hq_str_sh000300=", "").replace("\"", "").split(",");

		double sh001now = Double.parseDouble(sh001arraystr[3]);
		double sh001yestoday = Double.parseDouble(sh001arraystr[2]);

		double hs300now = Double.parseDouble(hs300arraystr[3]);
		double hs300yestoday = Double.parseDouble(hs300arraystr[2]);

		Map<String, String> retMap = new LinkedHashMap<>();
		retMap.put("time", hs300arraystr[30] + " " + hs300arraystr[31]);

		retMap.put("sh001", String.format("%.2f", sh001now));
		retMap.put("sh001percent", String.format("%.2f", (sh001now - sh001yestoday) / sh001yestoday * 100));
		retMap.put("sh001name", sh001arraystr[0]);
		
		retMap.put("hs300", String.format("%.2f", hs300now));
		retMap.put("hs300percent", String.format("%.2f", (hs300now - hs300yestoday) / hs300yestoday * 100));
		retMap.put("hs300name", hs300arraystr[0]);

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
		if(code.contains("sh")) {
			now = Double.parseDouble(arraystr[3]);
			yestoday = Double.parseDouble(arraystr[2]);
			retMap.put("name", arraystr[0]);
			retMap.put("time", arraystr[30] + " " + arraystr[31]);
		}else if(code.contains("hk")){
			now = Double.parseDouble(arraystr[6]);
			yestoday = Double.parseDouble(arraystr[3]);
			retMap.put("name", arraystr[1]);
			retMap.put("time", arraystr[17].replace("/", "-") + " " + arraystr[18].substring(0, 5));
		}
		retMap.put("now", String.format("%.2f", now));
		retMap.put("percent", String.format("%.2f", (now - yestoday) / yestoday * 100));
		

		return retMap;
	}
}
