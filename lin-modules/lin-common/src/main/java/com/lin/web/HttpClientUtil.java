package com.lin.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.DeflateDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import com.lin.exception.HttpConnectException;

/**
 * 
 * @ClassName: HttpClientUtil 
 * @Description: 模拟浏览器工具
 * @author xuelin 
 * @date Jul 28, 2015 12:23:08 PM 
 *
 */
public class HttpClientUtil {
	/**
	 * 获取HttpClient
	 * 
	 * @return HttpClient
	 */
	public static HttpClient getHttpClient(HttpHost proxy) {
		HttpClient httpClient = new DefaultHttpClient();
		// 设置默认策略（设置请求重试处理，请求三次）
		DefaultHttpRequestRetryHandler handler = new DefaultHttpRequestRetryHandler(1, false);
		((AbstractHttpClient) httpClient).setHttpRequestRetryHandler(handler);

		httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 4000);// 连接时间
		httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 6000);// 数据传输时间

		// 代理
		if (null != proxy) {
			httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
		}

		return httpClient;
	}

	/**
	 * 获取HttpGet
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws URISyntaxException
	 */
	public static HttpGet getHttpGet(String url, Map<String, String> param, String sessionId, String referer, String encode) throws Exception {
		if (null == encode) {
			encode = "UTF-8";
		}

		HttpGet httpGet = new HttpGet();
		// 模拟浏览器请求头，防止被屏蔽
		httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6,zh-TW;q=0.4");
		httpGet.setHeader("Accept-Encoding", "gzip, deflate, sdch");
		httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.0.3) Gecko/2008092417 Firefox/3.0.3");
		httpGet.setHeader("Accept-Charset", "utf-8");
		httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		httpGet.setHeader("Keep-Alive", "300");
		httpGet.setHeader("Connection", "Keep-Alive");
		httpGet.setHeader("Cache-Control", "no-cache");
		// 设置 HttpClient 接收 Cookie,用与浏览器一样的策略
		httpGet.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);
		if (null != referer) {
			httpGet.setHeader("Referer", referer);
		}
		if (null != sessionId) {
			httpGet.setHeader("Cookie", sessionId);
		}

		StringBuffer urlBuffer = new StringBuffer(url);
		if (param != null && !param.isEmpty()) {
			Set<String> set = param.keySet();
			int i = 0;
			for (String key : set) {
				if (null != param.get(key)) {
					if (0 == i) {
						urlBuffer.append("?");
					} else {
						urlBuffer.append("&");
					}
					urlBuffer.append(key + "=" + URLEncoder.encode(param.get(key), encode));
					i++;
				}
			}
		}

		// 设置链接
		URI uri = new URI(urlBuffer.toString());
		httpGet.setURI(uri);

		return httpGet;
	}

	/**
	 * 获取HttpPost
	 * 
	 * @return HttpPost
	 * @throws UnsupportedEncodingException
	 */
	public static HttpPost getHttpPost(String url, Map<String, String> param, String sessionId, String referer, String encode) throws Exception {
		if (null == encode) {
			encode = "UTF-8";
		}

		HttpPost httpPost = new HttpPost(url);
		// 模拟浏览器请求头，防止被屏蔽
		httpPost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6,zh-TW;q=0.4");
		httpPost.setHeader("Accept-Encoding", "gzip, deflate, sdch");
		httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.0.3) Gecko/2008092417 Firefox/3.0.3");
		httpPost.setHeader("Accept-Charset", "utf-8");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		httpPost.setHeader("Keep-Alive", "300");
		httpPost.setHeader("Connection", "Keep-Alive");
		httpPost.setHeader("Cache-Control", "no-cache");
		// 设置 HttpClient 接收 Cookie,用与浏览器一样的策略
		httpPost.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);
		if (null != referer) {
			httpPost.setHeader("Referer", referer);
		}
		if (null != sessionId) {
			httpPost.setHeader("Cookie", sessionId);
		}

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if (param != null && !param.isEmpty()) {
			Set<String> set = param.keySet();
			for (String key : set) {
				if (null != param.get(key)) {
					params.add(new BasicNameValuePair(key, param.get(key)));
				}
			}
		}
		httpPost.setEntity(new UrlEncodedFormEntity(params, encode));

		return httpPost;
	}

	/**
	 * 获取sessionId
	 * 
	 * @param response
	 * @return
	 */
	public static String getSessionId(HttpResponse response) {
		if(HttpStatus.SC_OK != response.getStatusLine().getStatusCode()){
			return null;
		}
		
		Header[] headers = response.getHeaders("Set-Cookie");
		for (Header header : headers) {
			if (header.getValue().contains("JSESSIONID")) {
				String str = header.getValue();

				if (str.contains(";")) {
					return header.getValue().split(";")[0];
				}

				return header.getValue();
			}
		}

		return null;
	}

	/**
	 * 响应内容
	 * 
	 * @param response
	 * @param encode
	 * @return
	 * @throws Exception
	 */
	public static String getResponse(HttpResponse response, String encode) throws Exception {
		if (null == encode) {
			encode = "UTF-8";
		}

		if (HttpStatus.SC_OK != response.getStatusLine().getStatusCode()) {
			throw new HttpConnectException("响应失败，响应码为" + response.getStatusLine().getStatusCode());
		}

		HttpEntity httpEntity = response.getEntity();
		if (httpEntity.getContentEncoding() != null && httpEntity.getContentEncoding().getValue().toLowerCase().indexOf("gzip") >= 0) {
			// For GZip response
			BufferedReader br = new BufferedReader(new InputStreamReader(new GZIPInputStream(httpEntity.getContent()), encode));
			StringBuffer sb = new StringBuffer();
			String tempbf;
			while ((tempbf = br.readLine()) != null) {
				sb.append(tempbf);
				sb.append("\r\n");
			}
			br.close();
			return sb.toString();
		} else if (httpEntity.getContentEncoding() != null && httpEntity.getContentEncoding().getValue().toLowerCase().indexOf("deflate") >= 0) {
			DeflateDecompressingEntity deflateEntity = new DeflateDecompressingEntity(httpEntity);
			BufferedReader br = new BufferedReader(new InputStreamReader(deflateEntity.getContent(), encode));
			StringBuffer sb = new StringBuffer();
			String tempbf;
			while ((tempbf = br.readLine()) != null) {
				sb.append(tempbf);
				sb.append("\r\n");
			}
			br.close();
			return sb.toString();
		} else {
			return httpEntity != null ? EntityUtils.toString(httpEntity, encode) : null;
		}
	}

	/**
	 * 释放资源
	 * 
	 * @param httpClient
	 */
	public static void close(HttpClient httpClient, HttpUriRequest request) {
		if (null != httpClient) {
			httpClient.getConnectionManager().shutdown();
		}
		if (null != request) {
			request.abort();
		}
	}

	public static String sendRequest(String requestUrl, String paramStr, String referer, String sessionId) throws Exception {
		InputStream in = null;
		OutputStream os = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		HttpURLConnection httpURLConnection = null;

		try {
			URL url = new URL(requestUrl);
			httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setConnectTimeout(1000);
			httpURLConnection.setUseCaches(false);
			httpURLConnection.setReadTimeout(3000);
			httpURLConnection.setRequestMethod("GET");
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);

			httpURLConnection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			httpURLConnection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6,zh-TW;q=0.4");
			httpURLConnection.setRequestProperty("Accept-Encoding", "gzip, deflate, sdch");
			httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.0.3) Gecko/2008092417 Firefox/3.0.3");
			httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
			httpURLConnection.setRequestProperty("Keep-Alive", "300");
			httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
			httpURLConnection.setRequestProperty("Cache-Control", "no-cache");
			httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
			httpURLConnection.setRequestProperty("Referer", referer);
			httpURLConnection.setRequestProperty(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);
			if (null != sessionId) {
				httpURLConnection.setRequestProperty("Cookie", sessionId);
			}

			// 写入参数
			httpURLConnection.connect();
			if (null != paramStr && !"".equals(paramStr)) {
				os = httpURLConnection.getOutputStream();
				os.write(paramStr.getBytes());
			}

			if (HttpStatus.SC_OK == httpURLConnection.getResponseCode()) {
				// 获取响应
				in = httpURLConnection.getInputStream();
				isr = new InputStreamReader(in);
				br = new BufferedReader(isr);
				String line = null;
				StringBuilder sb = new StringBuilder();
				while (null != (line = br.readLine())) {
					sb.append(line);
				}

				// 获取sessionID
				String uid = httpURLConnection.getHeaderField("Set-Cookie");
				if (null != uid) {
					String[] strs = uid.split(";");
					for (String str : strs) {
						if (str.contains("JSESSIONID=")) {
							sessionId = str;
							break;
						}
					}
				}

				return sb.toString();
			} else {
				throw new HttpConnectException("响应失败，响应码为" + httpURLConnection.getResponseCode());
			}
		} finally {
			// 释放资源
			try {
				if (null != os) {
					os.close();
				}
				if (null != in) {
					in.close();
				}
				if (null != isr) {
					isr.close();
				}
				if (null != br) {
					br.close();
				}
				httpURLConnection.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * 
	 * @param url
	 * @param param
	 * @param sessionId
	 * @param referer
	 * @param proxy
	 * @param encode
	 * @return
	 * @throws Exception
	 */
	public static String sendGetRequest(String url, Map<String, String> param, String sessionId, String referer, HttpHost proxy, String encode) throws Exception {
		HttpClient httpClient = null;
		HttpGet httpGet = null;
		try {
			httpClient = getHttpClient(proxy);
			httpGet = getHttpGet(url, param, sessionId, referer, encode);
			HttpResponse response = httpClient.execute(httpGet);

			// 获取sessionId
			String uid = getSessionId(response);
			if (null != uid) {
				sessionId = uid;
			}

			return getResponse(response, encode);
		} finally {
			close(httpClient, httpGet);
		}
	}

	/**
	 * 
	 * 
	 * @param url
	 * @param param
	 * @param sessionId
	 * @param referer
	 * @param proxy
	 * @param encode
	 * @return
	 * @throws Exception
	 */
	public static String sendPostRequest(String url, Map<String, String> param, String sessionId, String referer, HttpHost proxy, String encode) throws Exception {
		HttpClient httpClient = null;
		HttpPost httpPost = null;
		try {
			httpClient = getHttpClient(proxy);
			httpPost = getHttpPost(url, param, sessionId, referer, encode);
			HttpResponse response = httpClient.execute(httpPost);

			// 获取sessionId
			String uid = getSessionId(response);
			if (null != uid) {
				sessionId = uid;
			}

			return getResponse(response, encode);
		} finally {
			close(httpClient, httpPost);
		}
	}

}