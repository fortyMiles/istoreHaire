package com.istore.common.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * java ����http���󹤾���
 * @author Administrator
 *
 */
public class HttpRequester {
	/**
	 * Ĭ���ַ�
	 */
	private String defaultContentEncoding;  
	   
    public HttpRequester() {  
        this.defaultContentEncoding = Charset.defaultCharset().name();  
    }
    
    /** 
     * Ĭ�ϵ���Ӧ�ַ� 
     */  
    public String getDefaultContentEncoding() {  
        return this.defaultContentEncoding;  
    }  
   
    /** 
     * ����Ĭ�ϵ���Ӧ�ַ� 
     */  
    public void setDefaultContentEncoding(String defaultContentEncoding) {  
        this.defaultContentEncoding = defaultContentEncoding;  
    }  
   
    /** 
     * ����GET���� 
     *  
     * @param urlString 
     *            URL��ַ 
     * @return ��Ӧ���� 
     * @throws IOException 
     */  
    public HttpRespons sendGet(String urlString) throws IOException {  
        return this.send(urlString, "GET", null, null);  
    }  
   
    /** 
     * ����GET���� 
     *  
     * @param urlString 
     *            URL��ַ 
     * @param params 
     *            ����� 
     * @return ��Ӧ���� 
     * @throws IOException 
     */  
    public HttpRespons sendGet(String urlString, Map<String, String> params)  
            throws IOException {  
        return this.send(urlString, "GET", params, null);  
    }  
   
    /** 
     * ����GET���� 
     *  
     * @param urlString 
     *            URL��ַ 
     * @param params 
     *            ����� 
     * @param propertys 
     *            �������� 
     * @return ��Ӧ���� 
     * @throws IOException 
     */  
    public HttpRespons sendGet(String urlString, Map<String, String> params,  
            Map<String, String> propertys) throws IOException {  
        return this.send(urlString, "GET", params, propertys);  
    }  
   
    /** 
     * ����POST���� 
     *  
     * @param urlString 
     *            URL��ַ 
     * @return ��Ӧ���� 
     * @throws IOException 
     */  
    public HttpRespons sendPost(String urlString) throws IOException {  
        return this.send(urlString, "POST", null, null);  
    }  
   
    /** 
     * ����POST���� 
     *  
     * @param urlString 
     *            URL��ַ 
     * @param params 
     *            ����� 
     * @return ��Ӧ���� 
     * @throws IOException 
     */  
    public HttpRespons sendPost(String urlString, Map<String, String> params)  
            throws IOException {  
        return this.send(urlString, "POST", params, null);  
    }  
   
    /** 
     * ����POST���� 
     *  
     * @param urlString 
     *            URL��ַ 
     * @param params 
     *            ����� 
     * @param propertys 
     *            �������� 
     * @return ��Ӧ���� 
     * @throws IOException 
     */  
    public HttpRespons sendPost(String urlString, Map<String, String> params,  
            Map<String, String> propertys) throws IOException {  
        return this.send(urlString, "POST", params, propertys);  
    }  
   
    /** 
     * ����HTTP���� 
     *  
     * @param urlString 
     * @return ��ӳ���� 
     * @throws IOException 
     */  
    private HttpRespons send(String urlString, String method,  
            Map<String, String> parameters, Map<String, String> propertys)  
            throws IOException {  
        HttpURLConnection urlConnection = null;  
   
        if (method.equalsIgnoreCase("GET") && parameters != null) {  
            StringBuffer param = new StringBuffer();  
            int i = 0;  
            for (String key : parameters.keySet()) {  
                if (i == 0)  
                    param.append("?");  
                else  
                    param.append("&");  
                param.append(key).append("=").append(parameters.get(key));  
                i++;  
            }  
            urlString += param;  
        }  
        URL url = new URL(urlString);  
        urlConnection = (HttpURLConnection) url.openConnection();  
   
        urlConnection.setRequestMethod(method);  
        urlConnection.setDoOutput(true);  
        urlConnection.setDoInput(true);  
        urlConnection.setUseCaches(false);  
   
        if (propertys != null)  
            for (String key : propertys.keySet()) {  
                urlConnection.addRequestProperty(key, propertys.get(key));  
            }  
   
        if (method.equalsIgnoreCase("POST") && parameters != null) {  
            StringBuffer param = new StringBuffer();  
            for (String key : parameters.keySet()) {  
                param.append("&");  
                param.append(key).append("=").append(parameters.get(key));  
            }  
            urlConnection.getOutputStream().write(param.toString().getBytes());  
            urlConnection.getOutputStream().flush();  
            urlConnection.getOutputStream().close();  
        }  
   
        return this.makeContent(urlString, urlConnection);  
    }  
   
    /** 
     * �õ���Ӧ���� 
     *  
     * @param urlConnection 
     * @return ��Ӧ���� 
     * @throws IOException 
     */  
    private HttpRespons makeContent(String urlString,  
            HttpURLConnection urlConnection) throws IOException {  
        HttpRespons httpResponser = new HttpRespons();  
        try {  
        	String ecod = urlConnection.getContentEncoding();  
        	if (ecod == null)  
                ecod = this.defaultContentEncoding;  
            InputStream in = urlConnection.getInputStream();  
            BufferedReader bufferedReader = new BufferedReader(  
                    new InputStreamReader(in,ecod));  
            httpResponser.setContentCollection(new Vector<String>());  
            StringBuffer temp = new StringBuffer();  
            String line = bufferedReader.readLine();  
            while (line != null) {  
                httpResponser.getContentCollection().add(line);  
                temp.append(line).append("\r\n");  
                line = bufferedReader.readLine();  
            }  
            bufferedReader.close();  
   
            
            
   
            httpResponser.setUrlString(urlString);  
   
            httpResponser.setDefaultPort(urlConnection.getURL().getDefaultPort());  
            httpResponser.setFile(urlConnection.getURL().getFile());  
            httpResponser.setHost(urlConnection.getURL().getHost());  
            httpResponser.setPath(urlConnection.getURL().getPath());  
            httpResponser.setPort(urlConnection.getURL().getPort());  
            httpResponser.setProtocol(urlConnection.getURL().getProtocol());  
            httpResponser.setQuery(urlConnection.getURL().getQuery());  
            httpResponser.setRef(urlConnection.getURL().getRef());  
            httpResponser.setUserInfo(urlConnection.getURL().getUserInfo());  
   
            httpResponser.setContent(temp.toString());  
            httpResponser.setContentEncoding(ecod);  
            httpResponser.setCode(urlConnection.getResponseCode());  
            httpResponser.setMessage(urlConnection.getResponseMessage());  
            httpResponser.setContentType(urlConnection.getContentType());  
            httpResponser.setMethod(urlConnection.getRequestMethod());  
            httpResponser.setConnectTimeout(urlConnection.getConnectTimeout());  
            httpResponser.setReadTimeout(urlConnection.getReadTimeout());  
   
            return httpResponser;  
        } catch (IOException e) {  
            throw e;  
        } finally {  
            if (urlConnection != null)  
                urlConnection.disconnect();  
        }  
    }
    
    public static void main(String[] args){
    	try {  
            HttpRequester request = new HttpRequester();
            
            Map<String,String> params = new HashMap<String,String>();
            params.put("method", "ecerp.shangpin.get");
            params.put("appkey", "2F14E5396A894C019BF78A7D6D3FC593");
            params.put("page_no", "1");
            params.put("page_size", "10");
            params.put("condition", "SL<10");
            
            HttpRespons hr = request.sendGet("http://42.120.40.208:8038/data.dpk",params);
            
            //HttpRespons hr = request.sendGet("http://www.csdn.net");
   
            System.out.println("urlString:"+hr.getUrlString());  
            System.out.println("protocal:"+hr.getProtocol());  
            System.out.println("host:"+hr.getHost());  
            System.out.println("port:"+hr.getPort());  
            System.out.println("contentEncoding:"+hr.getContentEncoding());  
            System.out.println("method:"+hr.getMethod());
            System.out.println("message:"+hr.getMessage());
            System.out.println("---------------------------------------------");
            System.out.println("conttent:"+hr.getContent());  
   
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }

}
