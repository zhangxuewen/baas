package com.wxbaas.util.httpService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class HttpClientUtil {
	public static final String SunX509 = "SunX509";
	public static final String JKS = "JKS";
	public static final String PKCS12 = "PKCS12";
	public static final String TLS = "TLS";

	/**
	 * Get方式获取
	 * 
	 * @param webUrl
	 * @param encoding
	 * @return
	 */
	public static String GetUrlContent(String webUrl, String encoding) {
		if (encoding == null || "".equals(encoding))
			encoding = "UTF-8";
		StringBuffer sBuffer = new StringBuffer();
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		// 创建GET方法的实例
		GetMethod getMethod = new GetMethod(webUrl);
		getMethod.addRequestHeader("Content-Type", "text/html;charset=" + encoding);
		// 使用系统提供的默认的恢复策略
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		try {
			// 执行getMethod
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				// System.err.println("Method failed: " +
				// getMethod.getStatusLine());
			}
			// 读取内容
			byte[] responseBody = getMethod.getResponseBody();
			// 处理内容
			sBuffer.append(new String(responseBody, encoding));
		} catch (HttpException e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问题
			// System.out.println("Please check your provided http address!");
			e.printStackTrace();
		} catch (IOException e) {
			// 发生网络异常
			e.printStackTrace();
		} finally {
			// 释放连接
			getMethod.releaseConnection();
		}
		return sBuffer.toString();
	}

	/**
	 * 根据PageUrl获取Content
	 * 
	 * @param webUrl
	 * @param parametersBody
	 * @param encoding
	 * @return
	 */
	public static String PostUrlContent(String webUrl, NameValuePair[] parameters, String encoding) {
		if (encoding == null || "".equals(encoding))
			encoding = "UTF-8";
		StringBuffer sBuffer = new StringBuffer();
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		// 创建POS方法的实例
		PostMethod postMethod = new PostMethod(webUrl);
		postMethod.setRequestBody(parameters);
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, encoding);

		try {
			// 执行getMethod
			int statusCode = httpClient.executeMethod(postMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + postMethod.getStatusLine());
			}
			// 读取内容
			byte[] responseBody = postMethod.getResponseBody();
			// 处理内容
			sBuffer.append(new String(responseBody, encoding));
		} catch (HttpException e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问题
			System.out.println("Please check your provided http address!");
			e.printStackTrace();
		} catch (IOException e) {
			// 发生网络异常
			e.printStackTrace();
		} finally {
			// 释放连接
			postMethod.releaseConnection();
		}
		return sBuffer.toString();
	}

	/**
	 * 根据PageUrl获取Content
	 * 
	 * @param webUrl
	 * @param parametersBody
	 * @param encoding
	 * @return
	 */
	public static String PostUrlContent(String webUrl, Map<String, Object> parameters, String encoding) {
		if (encoding == null || "".equals(encoding))
			encoding = "UTF-8";
		StringBuffer sBuffer = new StringBuffer();
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		// 创建POS方法的实例

		NameValuePair[] pairs = null;
		PostMethod postMethod = new PostMethod(webUrl);
		if (parameters != null && parameters.size() > 0) {
			pairs = new NameValuePair[parameters.size()];
			int i = 0;	
			for (String key : parameters.keySet()) {
				pairs[i] = new NameValuePair(key, parameters.get(key).toString());
				i++;
			}
			postMethod.setRequestBody(pairs);
		}

		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, encoding);

		try {
			// 执行getMethod
			int statusCode = httpClient.executeMethod(postMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + postMethod.getStatusLine());
			}
			// 读取内容
			byte[] responseBody = postMethod.getResponseBody();
			// 处理内容
			sBuffer.append(new String(responseBody, encoding));

		} catch (HttpException e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问题
			System.out.println("Please check your provided http address!");
			e.printStackTrace();
		} catch (IOException e) {
			// 发生网络异常
			e.printStackTrace();
		} finally {
			// 释放连接
			postMethod.releaseConnection();
		}
		return sBuffer.toString();
	}

	@SuppressWarnings("deprecation")
	public static String PostUrlContent(String webUrl, String parameters, String encoding) {
		if (encoding == null || "".equals(encoding))
			encoding = "UTF-8";
		StringBuffer sBuffer = new StringBuffer();
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		// 创建POS方法的实例

		PostMethod postMethod = new PostMethod(webUrl);

		postMethod.setRequestBody(parameters);
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, encoding);

		try {
			// 执行getMethod
			int statusCode = httpClient.executeMethod(postMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + postMethod.getStatusLine());
			}
			// 读取内容
			byte[] responseBody = postMethod.getResponseBody();
			// 处理内容
			sBuffer.append(new String(responseBody, encoding));
		} catch (HttpException e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问题
			System.out.println("Please check your provided http address!");
			e.printStackTrace();
		} catch (IOException e) {
			// 发生网络异常
			e.printStackTrace();
		} finally {
			// 释放连接
			postMethod.releaseConnection();
		}
		return sBuffer.toString();
	}

	/**
	 * 字符串转换成char数组
	 * 
	 * @param str
	 * @return char[]
	 */
	public static char[] str2CharArray(String str) {
		if (null == str)
			return null;

		return str.toCharArray();
	}

	/**
	 * 存储ca证书成JKS格式
	 * 
	 * @param cert
	 * @param alias
	 * @param password
	 * @param out
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws IOException
	 */
	public static void storeCACert(Certificate cert, String alias, String password, OutputStream out)
			throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		KeyStore ks = KeyStore.getInstance("JKS");

		ks.load(null, null);

		ks.setCertificateEntry(alias, cert);

		// store keystore
		ks.store(out, HttpClientUtil.str2CharArray(password));

	}

	public static InputStream String2Inputstream(String str) {
		return new ByteArrayInputStream(str.getBytes());
	}

	/**
	 * 获取不带查询串的url
	 * 
	 * @param strUrl
	 * @return String
	 */
	public static String getURL(String strUrl) {

		if (null != strUrl) {
			int indexOf = strUrl.indexOf("?");
			if (-1 != indexOf) {
				return strUrl.substring(0, indexOf);
			}

			return strUrl;
		}

		return strUrl;

	}

	/**
	 * 获取查询串
	 * 
	 * @param strUrl
	 * @return String
	 */
	public static String getQueryString(String strUrl) {

		if (null != strUrl) {
			int indexOf = strUrl.indexOf("?");
			if (-1 != indexOf) {
				return strUrl.substring(indexOf + 1, strUrl.length());
			}

			return "";
		}

		return strUrl;
	}

	/**
	 * 获取CA证书信息
	 * 
	 * @param cafile
	 *            CA证书文件
	 * @return Certificate
	 * @throws CertificateException
	 * @throws IOException
	 */
	public static Certificate getCertificate(File cafile) throws CertificateException, IOException {
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		FileInputStream in = new FileInputStream(cafile);
		Certificate cert = cf.generateCertificate(in);
		in.close();
		return cert;
	}

	/**
	 * 获取SSLContext
	 * 
	 * @param trustFile
	 * @param trustPasswd
	 * @param keyFile
	 * @param keyPasswd
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws KeyStoreException
	 * @throws IOException
	 * @throws CertificateException
	 * @throws UnrecoverableKeyException
	 * @throws KeyManagementException
	 */
	public static SSLContext getSSLContext(FileInputStream trustFileInputStream, String trustPasswd,
			FileInputStream keyFileInputStream, String keyPasswd) throws NoSuchAlgorithmException, KeyStoreException,
					CertificateException, IOException, UnrecoverableKeyException, KeyManagementException {

		// ca
		TrustManagerFactory tmf = TrustManagerFactory.getInstance(HttpClientUtil.SunX509);
		KeyStore trustKeyStore = KeyStore.getInstance(HttpClientUtil.JKS);
		trustKeyStore.load(trustFileInputStream, HttpClientUtil.str2CharArray(trustPasswd));
		tmf.init(trustKeyStore);

		final char[] kp = HttpClientUtil.str2CharArray(keyPasswd);
		KeyManagerFactory kmf = KeyManagerFactory.getInstance(HttpClientUtil.SunX509);
		KeyStore ks = KeyStore.getInstance(HttpClientUtil.PKCS12);
		ks.load(keyFileInputStream, kp);
		kmf.init(ks, kp);

		SecureRandom rand = new SecureRandom();
		SSLContext ctx = SSLContext.getInstance(HttpClientUtil.TLS);
		ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), rand);

		return ctx;
	}

	/**
	 * get HttpURLConnection
	 * 
	 * @param strUrl
	 *            url地址
	 * @return HttpURLConnection
	 * @throws IOException
	 */
	public static HttpURLConnection getHttpURLConnection(String strUrl) throws IOException {
		URL url = new URL(strUrl);
		HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
		return httpURLConnection;
	}

	/**
	 * get HttpsURLConnection
	 * 
	 * @param strUrl
	 *            url地址
	 * @return HttpsURLConnection
	 * @throws IOException
	 */
	public static HttpsURLConnection getHttpsURLConnection(String strUrl) throws IOException {
		URL url = new URL(strUrl);
		HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
		return httpsURLConnection;
	}

	/**
	 * InputStream转换成Byte 注意:流关闭需要自行处理
	 * 
	 * @param in
	 * @return byte
	 * @throws Exception
	 */
	public static byte[] InputStreamTOByte(InputStream in) throws IOException {

		int BUFFER_SIZE = 4096;
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[BUFFER_SIZE];
		int count = -1;

		while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
			outStream.write(data, 0, count);

		data = null;
		byte[] outByte = outStream.toByteArray();
		outStream.close();

		return outByte;
	}

	/**
	 * InputStream转换成String 注意:流关闭需要自行处理
	 * 
	 * @param in
	 * @param encoding
	 *            编码
	 * @return String
	 * @throws Exception
	 */
	public static String InputStreamTOString(InputStream in, String encoding) throws IOException {

		return new String(InputStreamTOByte(in), encoding);

	}

	/**
	 * 处理输出<br/>
	 * 注意:流关闭需要自行处理
	 * 
	 * @param out
	 * @param data
	 * @param len
	 * @throws IOException
	 */
	public static void doOutput(OutputStream out, byte[] data, int len) throws IOException {
		int dataLen = data.length;
		int off = 0;
		while (off < data.length) {
			if (len >= dataLen) {
				out.write(data, off, dataLen);
				off += dataLen;
			} else {
				out.write(data, off, len);
				off += len;
				dataLen -= len;
			}

			// 刷新缓冲区
			out.flush();
		}

	}
}
