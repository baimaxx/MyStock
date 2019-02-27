package com.me.zs.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class ZsService {

	/**
	 * @param codes 新浪证券代码，多个使用逗号隔开
	 * @return
	 */
	public Map<String, List<Map<String, String>>> getStock(String codes) {
		String url = "https://hq.sinajs.cn/?list=" + codes;

		String retjson = HttpUtil.doGet(url);

		String[] jsonArray = retjson.replace("\n", "").replace("\"", "").split(";");
		String[] codeArray = codes.split(",");
		List<Map<String, String>> retList = new ArrayList<>();

		for (int i = 0; i < jsonArray.length; i++) {
			String json = jsonArray[i];

			String[] arraystr = json.replace("var hq_str_" + codeArray[i] + "=", "").split(",");

			double now = 0;
			double yestoday = 1;

			Map<String, String> tempMap = new LinkedHashMap<>();
			if (codeArray[i].contains("sh")) {
				now = Double.parseDouble(arraystr[3]);
				yestoday = Double.parseDouble(arraystr[2]);
				tempMap.put("name", arraystr[0]);
				tempMap.put("time", arraystr[30] + " " + arraystr[31]);
			}

			if (codeArray[i].contains("hk")) {
				now = Double.parseDouble(arraystr[6]);
				yestoday = Double.parseDouble(arraystr[3]);
				tempMap.put("name", arraystr[1]);
				tempMap.put("time", arraystr[17].replace("/", "-") + " " + arraystr[18].substring(0, 5));
			}

			if (codeArray[i].contains("sz")) {
				now = Double.parseDouble(arraystr[3]);
				yestoday = Double.parseDouble(arraystr[2]);
				tempMap.put("name", arraystr[0]);
				tempMap.put("time", arraystr[30] + " " + arraystr[31]);
			}
			tempMap.put("now", String.format("%.2f", now));
			tempMap.put("percent", String.format("%.2f", (now - yestoday) / yestoday * 100));

			retList.add(tempMap);
		}

		Map<String, List<Map<String, String>>> retMap = new LinkedHashMap<>();
		retMap.put("resultList", retList);
		return retMap;
	}
}
