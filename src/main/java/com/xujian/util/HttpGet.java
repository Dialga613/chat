package com.xujian.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpGet {

	public static String httpRequest(String apiPath) {
		BufferedReader in = null;
		StringBuffer result = null;
		// ���õ�api�Ľӿڵ�ַ
		String apiUrl = apiPath;
		try {
			URL url = new URL(apiPath);
			// �򿪺�url֮�������
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Charset", "UTF-8");
			connection.connect();
			result = new StringBuffer();
			// ��ȡURL����Ӧ
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
			return result.toString(); // ����json�ַ���
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return null;
	}

}