package com.istore.common.web.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.istore.common.core.bean.FtpUser;
import com.istore.common.core.bean.ImageFile;
import com.istore.common.core.dao.CatentryDao;



public class FtpWeb1ClientUtil {
	
	@Autowired
	public CatentryDao catDao;
	
	private static final Logger log = LoggerFactory.getLogger(FtpWeb1ClientUtil.class);
	
	private String username;
	private String password;
	private int port;
	private String remoteDir;
	private String host;
	private FTPClient ftpClient;
	
	
	public FtpWeb1ClientUtil() throws IOException {
		ftpClient=this.getFTPClient();
	}
	

	private FTPClientConfig getClientConfig(String sysType) {
		FTPClientConfig config = new FTPClientConfig(sysType);
		config.setRecentDateFormatStr("yyyy-MM-dd HH:mm");
		return config;
	}
	
	public void close() throws IOException{
		if(null!=ftpClient){
			ftpClient.disconnect();
		}
	}
	
	public FTPClient getFTPClient() throws IOException{
		ftpClient = new FTPClient(); 
		ftpClient.setDefaultPort(port);
		ftpClient.configure(getClientConfig(FTPClientConfig.SYST_NT));
		ftpClient.setControlEncoding("UTF-8");
		return ftpClient; 
	}
	
	/***
	 * 
	 * @param imageFiles
	 * @param bytes
	 * @return
	 * @throws SocketException
	 * @throws IOException
	 */
	public synchronized boolean uploadFile(List<ImageFile> imageFiles) throws SocketException, IOException{
		log.info("start to connect to web1 ip :"+host);
		FtpUser ftpuser=catDao.getFtpuser(6);
		host=ftpuser.getHost();
		port=ftpuser.getPort();
		username=ftpuser.getUsername();
		password=ftpuser.getPassword();
		ftpClient.connect(host, port);	
		ftpClient.login(username, password);
		int reply = ftpClient.getReplyCode();  
		if (!FTPReply.isPositiveCompletion(reply)) {
			 ftpClient.disconnect();
			 log.info("ftp server refused connection.");
		     return false;
		}
		this.setRemoteDir(ftpuser.getRemoteDir());
      
		ftpClient.changeWorkingDirectory(this.getRemoteDir());
		log.info("changeed working directory to "+this.getRemoteDir());
		for(ImageFile imageFile:imageFiles){
			String []paths=imageFile.getPath();
			for(String path:paths){
				ftpClient.makeDirectory(path);
				ftpClient.changeWorkingDirectory(path);
				log.info("changeed working directory to "+path);
			}
			log.info("starting to upload image "+imageFile.getName());
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE); 
			InputStream is = new ByteArrayInputStream(ImageUtils.zoom(imageFile.getBytes(),imageFile.getWidth(),imageFile.getHeight(),false)); 
			ftpClient.storeFile(imageFile.getName(), is);
			log.info("end to upload image "+imageFile.getName());
			ftpClient.changeWorkingDirectory(this.getRemoteDir());
			is.close();
		}
		log.info("logout ftp ");
		return true;
		
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getRemoteDir() {
		return remoteDir;
	}
	public void setRemoteDir(String remoteDir) {
		this.remoteDir = remoteDir;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}


	
	
	
	
}
