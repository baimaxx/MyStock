package com.me.zs.service;

import java.util.*;

import com.me.zs.enums.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ZsService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String SINA_STOCK_URL = "https://hq.sinajs.cn/?list=";
    private static final String SINA_SEARCH_URL = "https://suggest3.sinajs.cn/suggest/?name=value&key=";

    /**
     * @param codes 新浪证券代码，多个使用逗号隔开
     * @return
     */
    public Map<String, List<Map<String, String>>> getStock(String codes) {
        String url = SINA_STOCK_URL + codes;

        String retjson = restTemplate.getForObject(url, String.class);

        String[] jsonArray = retjson.replace("\n", "").replace("\"", "").split(";");
        String[] codeArray = codes.split(",");
        List<Map<String, String>> retList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length; i++) {
            String json = jsonArray[i];

            String[] arraystr = json.replace("var hq_str_" + codeArray[i] + "=", "").split(",");
            if (arraystr.length == 1) {
                continue;
            }

            double now = 0;
            double yestoday = 1;

            Map<String, String> tempMap = new LinkedHashMap<>();

            tempMap.put("code", codeArray[i]);
            if (codeArray[i].contains("sh")) {
                now = Double.parseDouble(arraystr[3]);
                yestoday = Double.parseDouble(arraystr[2]);
                tempMap.put("name", arraystr[0]);
                tempMap.put("time", arraystr[30] + " " + arraystr[31]);
            } else if (codeArray[i].contains("hk")) {
                now = Double.parseDouble(arraystr[6]);
                yestoday = Double.parseDouble(arraystr[3]);
                tempMap.put("name", arraystr[1]);
                tempMap.put("time", arraystr[17].replace("/", "-") + " " + arraystr[18].substring(0, 5));
            } else if (codeArray[i].contains("sz")) {
                now = Double.parseDouble(arraystr[3]);
                yestoday = Double.parseDouble(arraystr[2]);
                tempMap.put("name", arraystr[0]);
                tempMap.put("time", arraystr[30] + " " + arraystr[31]);
            } else {
                continue;
            }

            tempMap.put("now", String.format("%.3f", now));
            tempMap.put("percent", String.format("%.2f", (now - yestoday) / yestoday * 100));

            retList.add(tempMap);
        }

        Map<String, List<Map<String, String>>> retMap = new LinkedHashMap<>();
        retMap.put("resultList", retList);
        return retMap;
    }

    public List<Map<String, String>> search(String key) {
        String url = SINA_SEARCH_URL + key;
        String str = restTemplate.getForObject(url, String.class);
        String[] dataArray = str.replace("var value=\"", "").replace("\"", "").split(";");

        List<Map<String, String>> list = new ArrayList<>();
        for (String data : dataArray) {
            String[] array = data.split(",");
            Map<String, String> map = new HashMap<>();
            map.put("name", array[0]);
            map.put("type", Type.getByCode(Integer.valueOf(array[1])).name());
            map.put("code", array[2]);
            map.put("innercode", array[3]);
            list.add(map);
        }

        if (null != list && list.size() > 10) {
            list = list.subList(0, 10);
        }

        return list;
    }
}
