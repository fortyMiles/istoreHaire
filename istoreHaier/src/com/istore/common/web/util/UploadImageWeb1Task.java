package com.istore.common.web.util;

import java.io.IOException;
import java.net.SocketException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.istore.common.core.bean.ImageFile;




public class UploadImageWeb1Task implements Runnable{
	private static final Logger log = LoggerFactory.getLogger(UploadImageWeb1Task.class);
	private List<ImageFile> imageFiles;
	
	public UploadImageWeb1Task(List<ImageFile> imageFiles) {
		this.imageFiles=imageFiles;
	}
	
	public void run() {
		log.info("_____start to upload web1 images :"+imageFiles.size());
		try {
			FtpWeb1ClientUtil ftpWeb1ClientUtil=SpringUtil.getBean("ftpWeb1ClientUtil", FtpWeb1ClientUtil.class);
			ftpWeb1ClientUtil.uploadFile(imageFiles);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info("_____upload  web1 images success!");
	}


	public List<ImageFile> getImageFiles() {
		return imageFiles;
	}


	public void setImageFiles(List<ImageFile> imageFiles) {
		this.imageFiles = imageFiles;
	}


	
	
	

}
