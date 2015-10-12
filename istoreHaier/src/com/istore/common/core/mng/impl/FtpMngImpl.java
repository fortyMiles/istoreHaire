/**
 * @Project Haieristore
 * @Package com.istore.common.core.mng.impl
 * @Title NewsMngImpl.java
 * @Description TODO
 * @CopyRight CopyRight (c) 2014
 * @Company 江苏太湖云计算信息技术股份有限公司
 *
 * @author mojilin
 * @date 2014-7-12
 * @email mojilin@wuxicloud.com
 * @version V1.0
 */
package com.istore.common.core.mng.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.istore.common.core.bean.FTP;
import com.istore.common.core.dao.FtpDao;
import com.istore.common.core.mng.FtpMng;

/**
 * @ClassName: NewsMngImpl.java
 * @Description: TODO
 * @author mojilin
 * @time 2014-7-12上午11:24:50
 */
@Service
@Transactional
public class FtpMngImpl implements FtpMng {

	@Autowired
	FtpDao ftpDao;

	/*
	 * 获得FTP列表
	 * 
	 * @see com.istore.common.core.mng.FtpMng#getFtpList(java.lang.String)
	 */
	public List<FTP> getFtpListByType(String type) {
		return ftpDao.getFtpListByType(type);
	}

	/*
	 * 图片上传
	 * 
	 * @see
	 * com.istore.common.core.mng.FtpMng#uploadImageFile(org.springframework
	 * .web.multipart.MultipartFile, java.lang.String)
	 */
	public String uploadImageFile(MultipartFile file, String type) {
		return ftpDao.uploadImageFile(file, type);
	}

}
