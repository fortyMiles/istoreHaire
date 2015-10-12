/**
 * @Project Name:WebSphereCommerceServerExtensionsLogic 
 * @Title: SMSService.java
 * @Package com.cloud.commerce.service
 * @Description: TODO
 * Copyright: Copyright (c) 2013
 * Company:江苏无锡太湖云计算信息技术股份有限公司
 *
 * @author chenqiang
 * @date 2013-10-8 下午05:49:47
 * @email qiangchen@wuxcloud.com
 * @version V1.0
 */

package com.istore.common.core.util;

import java.io.IOException;
import java.util.Map;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.istore.common.core.mng.UserMng;

/**
 * @ClassName: SMSService
 * @Description: TODO
 * @author wude
 * @date 2013-10-8 下午05:49:47
 *
 */

@Service
@Transactional
public class SMSService {
	
	@Autowired
	UserMng userMng;
	
	private static final String CLASSNAME = SMSService.class.getName();
	private static final java.util.logging.Logger LOGGER = com.ibm.commerce.foundation.common.util.logging.LoggingHelper
			.getLogger(SMSService.class);
	private static final int FAIL = 1;
	private static final int SUCCESS = 0;
	//返回1代表发送失败，返回0代表发送成功。
	public  int sendSMS(String mobile,String message,Map<String, Object> mapParam) {
		//从数据库中账号，密码以及超时信息
		String SmsUrl="";
		String SpCode="";
		String LoginName="";
		String Password="";
		String SerialNumber="";
		String ScheduleTime="";
		String SmsTimeOut="";
		if(!mapParam.isEmpty())
		{
			SmsUrl=mapParam.get("SmsUrl")==null ?"" :mapParam.get("SmsUrl").toString();
			SpCode=mapParam.get("SpCode")==null ?"" :mapParam.get("SpCode").toString();
			LoginName=mapParam.get("LoginName")==null ?"" :mapParam.get("LoginName").toString();
			Password=mapParam.get("Password")==null?"" : mapParam.get("Password").toString();
			SerialNumber=mapParam.get("SerialNumber")==null ? "" :mapParam.get("SerialNumber").toString();
			ScheduleTime=mapParam.get("ScheduleTime")==null ? "" :mapParam.get("ScheduleTime").toString();
			SmsTimeOut=mapParam.get("SmsTimeOut")==null ? "" :mapParam.get("SmsTimeOut").toString();
		}
		String f = "1";
		if(SmsUrl == null || "".equals(SmsUrl)){
			SmsUrl = "http://gd.ums86.com:8899/sms/Api/Send.do?";
		}
		if(SpCode == null || "".equals(SpCode)){
			SpCode = "104221";
		}
		if(LoginName == null || "".equals(LoginName)){
			LoginName = "dslrb2c";
		}
		if(Password == null || "".equals(Password)){
			Password = "dslr2013";
		}
		if(SerialNumber == null || "".equals(SerialNumber)){
			SerialNumber = "";
		}
		if(ScheduleTime == null || "".equals(ScheduleTime)){
			ScheduleTime = "";
		}
		if(SmsTimeOut == null || "".equals(SmsTimeOut)){
			SmsTimeOut = "";
		}
		HttpClient httpClient =new HttpClient();
		int time = 10;
		try {
			time=Integer.parseInt(SmsTimeOut);
		} catch (NumberFormatException e1) {
			time=30;
		}
		//设置发送超时时间
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(time*1000);
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(time*1000);
	    NameValuePair[] data = { 
	    		new NameValuePair("SpCode", SpCode),
	    		new NameValuePair("LoginName", LoginName),
	    		new NameValuePair("Password", Password),
	    		new NameValuePair("MessageContent",message),
	    		new NameValuePair("UserNumber", mobile),
	    		new NameValuePair("f",f),
	    		new NameValuePair("SerialNumber",""),
	    		new NameValuePair("ScheduleTime","")
	    		
	    };
		PostMethod postMethod = new PostMethod(SmsUrl);
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"GBK");  
		postMethod.setRequestBody(data);
		try {
			int statusCode = httpClient.executeMethod(postMethod);
			if (statusCode != HttpStatus.SC_OK) {
			    return SMSService.FAIL;
		   }else{
			   String result= postMethod.getResponseBodyAsString();
			   if(result!=null && result.indexOf("result=0")!=-1){
				   return SMSService.SUCCESS;
			   }else {
				   return SMSService.FAIL;
			   }
		   }
		} catch (HttpException e) {
			e.printStackTrace();
			return SMSService.FAIL;	
		} catch (IOException e) {
			e.printStackTrace();
			return SMSService.FAIL;
		}finally{
			//释放链接
			postMethod.releaseConnection();
		}
	    }
}
