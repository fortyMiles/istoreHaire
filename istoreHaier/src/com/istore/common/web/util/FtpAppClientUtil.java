/**
 * @Projct Name:WebSphereCommerceServerExtensionsLogic
 * @Title FtpAppClientUtil.java
 * @Package com.ibm.commerce.web.admin.databeans
 * @Description TODO
 * Copyright: CopyRight (c) 2014
 * Company: 江苏太湖云计算信息技术股份有限公司
 *  
 * @author Administrator
 * @date 2014-3-24
 * @email mojilin@wuxicloud.com
 * @version V1.0
 *
 */
package com.istore.common.web.util;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.commerce.exception.ECException;
import com.ibm.commerce.exception.ECSystemException;
import com.ibm.commerce.ras.ECMessage;

import com.istore.common.core.bean.FTP;
import com.istore.common.core.dao.impl.AdvertDaoImpl;

/**
 * @ClassName: FtpAppClientUtil.java
 * @Description: TODO
 * @author mojilin
 * @date 2014-3-24下午04:52:46
 * 
 */
/**
 * ftp工具
 */
public class FtpAppClientUtil {
	private static final Logger log = LoggerFactory
			.getLogger(FtpAppClientUtil.class);
	
	//AdvertDaoImpl ftpDaoImpl = new AdvertDaoImpl();

	/**
	
	  * getClientConfig(获得服务器配置)
	  * @Title: getClientConfig
	  * @Description: TODO
	  * @param @param sysType
	  * @param @return    设定文件
	  * @return FTPClientConfig    返回类型
	  * @throws
	  */
	private FTPClientConfig getClientConfig(String sysType) {
		FTPClientConfig config = new FTPClientConfig(sysType);
		config.setRecentDateFormatStr("yyyy-MM-dd HH:mm");
		return config;
	}

	/**上传文件
	 * @param ftpType
	 * @param path
	 * @param type
	 * @param name
	 * @throws SocketException
	 * @throws IOException
	 * @throws ECException
	 */
	/*
	public void uploadFile(String ftpType, String path, String type, String name)
			throws SocketException, IOException, ECException {
	
		List<FTP> ftpBeans;
		try {
			ftpBeans = ftpDaoImpl.getFTPinfo();
			for (FTP ftpBean : ftpBeans) {
				log.info("start to connect to app ip :" + ftpBean.getHost());
				FTPClient ftpClient = new FTPClient();
				ftpClient.configure(getClientConfig(FTPClientConfig.SYST_NT));
				ftpClient.connect(ftpBean.getHost(), Integer.valueOf(ftpBean.getPort()));
				ftpClient.login(ftpBean.getUsername(), ftpBean.getPassword());
				int reply = ftpClient.getReplyCode();
				if (!FTPReply.isPositiveCompletion(reply)) {
					ftpClient.disconnect();
					log.info("ftp  app server refused connection.");
				}
				ftpClient.changeWorkingDirectory(ftpBean.getXpath()+ "/");
				log.info("changeed working directory to "
						+ ftpBean.getXpath() + "/");
				ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
				FileInputStream is = new FileInputStream(path);
				ftpClient.storeFile(name, is);
				is.close();
				ftpClient.disconnect();
				log.info("logout ftp ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/
	/**上传html文件
	 * @param ftpType
	 * @param name
	 * @param content
	 * @throws SocketException
	 * @throws IOException
	 * @throws ECException
	 */
	public void uploadHtmlFile(String ftpType, String name, String content,List ftplist)
			throws SocketException, IOException, ECException {
	
		List<FTP> ftpBeans;
		ftpBeans = ftplist;
		try {
			
			for (FTP ftpBean : ftpBeans) {
				log.info("start to connect to app ip :" + ftpBean.getHost());
				FTPClient ftpClient = new FTPClient();
				ftpClient.configure(getClientConfig(FTPClientConfig.SYST_NT));
				ftpClient.connect(ftpBean.getHost(), Integer.valueOf(ftpBean.getPort()));
				ftpClient.login(ftpBean.getUsername(), ftpBean.getPassword());
				int reply = ftpClient.getReplyCode();
				if (!FTPReply.isPositiveCompletion(reply)) {
					ftpClient.disconnect();
					log.info("ftp  app server refused connection.");
				}
				ftpClient.changeWorkingDirectory(ftpBean.getXpath());
				log.info("changeed working directory to "
						+ ftpBean.getXpath());
				ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
				InputStream is = new ByteArrayInputStream(content
						.getBytes("utf-8"));
				ftpClient.storeFile(name, is);
				is.close();
				ftpClient.disconnect();
				log.info("logout ftp ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
