package com.me.zs.service;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpUtil {

//	private final static String ContentType = "application/json";
	private final static String EncodeType = "UTF-8";

	public static String doGet(String url) {
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000) // 设置连接超时时间
				.setConnectionRequestTimeout(5000) // 设置请求超时时间
				.setSocketTimeout(5000)
				.setRedirectsEnabled(true)// 默认允许自动重定向
				.build();

		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet(url);
		get.setConfig(requestConfig);
		CloseableHttpResponse response;

		String respStr = null;
		try {
			response = client.execute(get);

			// 服务器返回码
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == 200) {
				respStr = EntityUtils.toString(response.getEntity(), EncodeType);
			} else {
				respStr = "请求失败: " + get.getRequestLine();
			}

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return respStr;

	}
}
