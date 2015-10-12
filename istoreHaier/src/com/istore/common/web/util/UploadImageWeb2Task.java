package com.istore.common.web.util;

import java.util.List;

import com.istore.common.core.bean.ImageFile;




public class UploadImageWeb2Task implements Runnable{
//	private static final Logger log = LoggerFactory.getLogger(UploadImageWeb2Task.class);
	private List<ImageFile> imageFiles;
	
	public UploadImageWeb2Task(List<ImageFile> imageFiles) {
		this.imageFiles=imageFiles;
	}
	
	public void run() {
//		log.info("_____start to upload web2 images :"+imageFiles.size());
//		try {
//			FtpWeb2ClientUtil ftpWeb2ClientUtil=SpringUtil.getBean("ftpWeb2ClientUtil", FtpWeb2ClientUtil.class);
//			ftpWeb2ClientUtil.uploadFile(imageFiles);
//		} catch (SocketException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		log.info("_____upload web2images success!");
	}


	public List<ImageFile> getImageFiles() {
		return imageFiles;
	}


	public void setImageFiles(List<ImageFile> imageFiles) {
		this.imageFiles = imageFiles;
	}


	
	
	

}
