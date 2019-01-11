package com.hisi.common.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.GZIPOutputStream;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

/**
 * Http协议工具类
 *
 * @author 吴羽娟 2017年9月26日
 */
public class HttpUtils {

	/**
	 * Http的GET方式
	 * 
	 * @param url
	 * @param header
	 * @param params
	 * @return
	 */
	public static String doHttpGet(String url, Map<String, String> header,
			Map<String, Object> params) {
		CloseableHttpResponse closeableHttpResponse = null;
		String jsonResult = null;
		// 请求的结果
		try {
			closeableHttpResponse = doHttpGetResponse(url, header, params);
			HttpEntity entity = closeableHttpResponse.getEntity();
			jsonResult = EntityUtils.toString(entity, "UTF-8");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResult;
	}

	public static CloseableHttpResponse doHttpGetResponse(String url,
			Map<String, String> header, Map<String, Object> params) {
		CloseableHttpResponse closeableHttpResponse = null;
		CloseableHttpClient httpclient = HttpClients.custom()
				.setDefaultRequestConfig(getRequestConfig()).build();
		// 参数
		if (params != null) {
			StringBuffer param = setGetParam(params);
			url += param;
		}
		HttpGet httpGet = new HttpGet(url);
		if (header != null) {
			for (Entry<String, String> e : header.entrySet()) {
				httpGet.addHeader(e.getKey(), e.getValue());
			}
		}
		// 请求的结果
		try {
			closeableHttpResponse = httpclient.execute(httpGet);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return closeableHttpResponse;
	}

	/**
	 * https的get请求
	 * 
	 * @param url
	 * @param header
	 * @param params
	 * @return
	 */
	public static String doHttpsGet(String url, Map<String, String> header,
			Map<String, Object> params) {
		CloseableHttpResponse closeableHttpResponse = null;
		String jsonResult = null;
		try {
			closeableHttpResponse = doHttpsGetResponse(url, header, params);
			HttpEntity entity = closeableHttpResponse.getEntity();
			jsonResult = EntityUtils.toString(entity, "UTF-8");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResult;
	}

	public static CloseableHttpResponse doHttpsGetResponse(String url,
			Map<String, String> header, Map<String, Object> params) {
		CloseableHttpResponse closeableHttpResponse = null;
		CloseableHttpClient httpclient = getHttpClient();
		// 参数
		if (params != null) {
			StringBuffer param = setGetParam(params);
			url += param;
		}
		HttpGet httpGet = new HttpGet(url);
		httpGet.setConfig(getRequestConfig());
		if (header != null) {
			for (Entry<String, String> e : header.entrySet()) {
				httpGet.addHeader(e.getKey(), e.getValue());
			}
		}
		try {
			closeableHttpResponse = httpclient.execute(httpGet);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return closeableHttpResponse;
	}

	/**
	 * HttpPost请求
	 * 
	 * @param url
	 * @param json
	 *            json格式的字符串
	 * @param header
	 * @return json格式的字符串
	 */
	public static String doHttpPost(String url, Map<String, String> header,
			String json) {
		String jsonResult = null;
		try {
			CloseableHttpResponse closeableHttpResponse = doHttpPostResponse(
					url, header, json);
			HttpEntity entity = closeableHttpResponse.getEntity();
			jsonResult = EntityUtils.toString(entity, "UTF-8");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return jsonResult;
	}

	public static CloseableHttpResponse doHttpPostResponse(String url,
			Map<String, String> header, String json) {
		CloseableHttpResponse closeableHttpResponse = null;
		CloseableHttpClient httpClient = getHttpClient();
		HttpPost httpPost = new HttpPost(url);
		if (json != null) {
			StringEntity entity = new StringEntity(json, "UTF-8");
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");
			httpPost.setEntity(entity);
		}
		if (header != null) {
			for (Entry<String, String> e : header.entrySet()) {
				httpPost.addHeader(e.getKey(), e.getValue());
			}
		}
		try {
			closeableHttpResponse = httpClient.execute(httpPost);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return closeableHttpResponse;
	}

	/**
	 * https的Post请求
	 * 
	 * @param url
	 * @param header
	 * @param json
	 * @return json格式的字符串
	 */
	public static String doHttpsPost(String url, Map<String, String> header,
			String json) {
		CloseableHttpResponse closeableHttpResponse = null;
		String jsonResult = null;
		try {
			closeableHttpResponse = doHttpsPostResponse(url, header, json);
			HttpEntity entity = closeableHttpResponse.getEntity();
			jsonResult = EntityUtils.toString(entity, "UTF-8");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResult;
	}

	public static CloseableHttpResponse doHttpsPostResponse(String url,
			Map<String, String> header, String json) {
		CloseableHttpResponse closeableHttpResponse = null;
		CloseableHttpClient httpclient = getHttpClient();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(getRequestConfig());
		if (json != null) {
			StringEntity entity = new StringEntity(json, "UTF-8");
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");
			httpPost.setEntity(entity);
		}
		if (header != null) {
			for (Entry<String, String> e : header.entrySet()) {
				httpPost.addHeader(e.getKey(), e.getValue());
			}
		}
		try {
			closeableHttpResponse = httpclient.execute(httpPost);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return closeableHttpResponse;
	}

	/**
	 * 设置连接请求参数
	 * 
	 * @return
	 */
	public static RequestConfig getRequestConfig() {
		return RequestConfig.custom().setSocketTimeout(50000)
				.setConnectTimeout(50000).setConnectionRequestTimeout(50000)
				.build();
	}

	/**
	 * 获取Https方式的连接
	 * 
	 * @return
	 */
	public static CloseableHttpClient getHttpClient() {
		try {
			SSLContext ctx = SSLContext.getInstance("TLS");
			X509TrustManager tm = new X509TrustManager() {
				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				@Override
				public void checkClientTrusted(X509Certificate[] arg0,
						String arg1) throws CertificateException {
				}

				@Override
				public void checkServerTrusted(X509Certificate[] arg0,
						String arg1) throws CertificateException {
				}
			};
			ctx.init(null, new TrustManager[] { tm }, null);
			SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(
					ctx, NoopHostnameVerifier.INSTANCE);
			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
					.<ConnectionSocketFactory> create()
					.register("http", PlainConnectionSocketFactory.INSTANCE)
					.register("https", socketFactory).build();
			RequestConfig defaultRequestConfig = RequestConfig
					.custom()
					.setCookieSpec(CookieSpecs.STANDARD_STRICT)
					.setExpectContinueEnabled(true)
					.setTargetPreferredAuthSchemes(
							Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
					.setProxyPreferredAuthSchemes(
							Arrays.asList(AuthSchemes.BASIC)).build();
			PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(
					socketFactoryRegistry);
			// 在一定时间不活动之后验证连接
			connectionManager.setValidateAfterInactivity(5000);
			return HttpClients.custom()
					.setDefaultRequestConfig(defaultRequestConfig)
					.setConnectionManager(connectionManager).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * unicode转中文
	 * 
	 * @param unicode
	 * @return
	 */
	public static String unicodeToCn(String unicode) {
		String[] strs = unicode.split("\\\\u");
		String returnStr = "";
		for (int i = 1; i < strs.length; i++) {
			returnStr += (char) Integer.valueOf(strs[i], 16).intValue();
		}
		return returnStr;
	}

	/**
	 * 中文转unicode
	 * 
	 * @param cn
	 * @return
	 */
	public static String cnToUnicode(String cn) {
		char[] chars = cn.toCharArray();
		String returnStr = "";
		for (int i = 0; i < chars.length; i++) {
			returnStr += "\\u" + Integer.toString(chars[i], 16);
		}
		return returnStr;
	}

	/**
	 * 压缩字符串
	 * 
	 * @param str
	 * @param encoding
	 * @return
	 */
	public static byte[] compress(String str, String encoding) {
		if (str == null || str.length() == 0) {
			return null;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gzip;
		try {
			gzip = new GZIPOutputStream(out);
			gzip.write(str.getBytes(encoding));
			gzip.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out.toByteArray();
	}

	/**
	 * 转换字符串里的ASCILL为汉字
	 * 
	 * @param asciicode
	 * @return
	 */
	public static String asciiToNative(String asciicode) {
		String[] asciis = asciicode.split("\\\\u");
		String nativeValue = asciis[0];
		try {
			for (int i = 1; i < asciis.length; i++) {
				String code = asciis[i];
				nativeValue += (char) Integer
						.parseInt(code.substring(0, 4), 16);
				if (code.length() > 4) {
					nativeValue += code.substring(4, code.length());
				}
			}
		} catch (NumberFormatException e) {
			return asciicode;
		}
		return nativeValue;
	}

	/**
	 * get方式参数的拼接
	 * 
	 * @param params
	 * @return
	 */
	public static StringBuffer setGetParam(Map<String, Object> params) {
		StringBuffer param = new StringBuffer();
		int i = 0;
		for (String key : params.keySet()) {
			if (i == 0) {
				param.append("?");
			} else {
				param.append("&");
			}
			param.append(key).append("=").append(params.get(key));
			i++;
		}
		return param;
	}
}
