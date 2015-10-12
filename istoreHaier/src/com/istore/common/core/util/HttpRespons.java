package com.istore.common.core.util;

import java.util.Vector;

public class HttpRespons {
	/**
	 * ����·��(http://www.csdn.net)
	 */
	private String urlString;  
	/**
	 * ��� URL ����Э���Ĭ�϶˿ں�
	 */
	private int defaultPort;  
    /**
     * �� URL ���ļ���
     */
	private String file;  
	/**
     * ����(www.csdn.net)
     */
	private String host;  
   
	private String path;  
	/**
     * �˿�
     */
	private int port;  
	/**
     * Э��(http)
     */
	private String protocol;  
    /**
     * �� URL �Ĳ�ѯ����
     */
	private String query;  
    /**
     * �� URL ��ê��(Ҳ��Ϊ�����á�)
     */
	private String ref;  
    /**
     * �� URL �� userInfo ����
     */
	private String userInfo;  
    /**
     * ���ݱ��뷽ʽ
     */
	private String contentEncoding;  
	/**
     * �� URL ������
     */
	private String content;  
	/**
     * ��������
     */
	private String contentType;  
	/**
     * ��ʾ��λ����� HTTP ״̬��
     */
	private int code;  
    /**
     * HTTP ��Ӧ��Ϣ
     */
	private String message;  
    /**
     * ���󷽷�(GET��POST)
     */
	private String method;  
    /**
     * ���ӳ�ʱʱ��
     */
	private int connectTimeout;  
   
	private int readTimeout;  
   
	private Vector<String> contentCollection;

	public String getUrlString() {
		return urlString;
	}

	public void setUrlString(String urlString) {
		this.urlString = urlString;
	}

	public int getDefaultPort() {
		return defaultPort;
	}

	public void setDefaultPort(int defaultPort) {
		this.defaultPort = defaultPort;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

	public String getContentEncoding() {
		return contentEncoding;
	}

	public void setContentEncoding(String contentEncoding) {
		this.contentEncoding = contentEncoding;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public int getReadTimeout() {
		return readTimeout;
	}

	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}

	public Vector<String> getContentCollection() {
		return contentCollection;
	}

	public void setContentCollection(Vector<String> contentCollection) {
		this.contentCollection = contentCollection;
	}
}
