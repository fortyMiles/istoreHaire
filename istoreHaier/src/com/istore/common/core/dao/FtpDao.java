/**
 * @Project istoreHaier
 * @Package com.istore.common.core.dao
 * @Title FtpDao.java
 * @Description TODO
 * @CopyRight CopyRight (c) 2014
 * @Company 江苏太湖云计算信息技术股份有限公司
 *
 * @author mojilin
 * @date 2014-7-15
 * @email mojilin@wuxicloud.com
 * @version V1.0
 */
package com.istore.common.core.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.istore.common.core.bean.FTP;

/**
 * @ClassName: FtpDao.java
 * @Description: TODO
 * @author mojilin
 * @time 2014-7-15下午1:18:06
 */
public interface FtpDao {

	/**
	 * 获得FTP列表
	 * 
	 * @param type
	 * @return
	 */
	public List<FTP> getFtpListByType(String type);

	/**
	 * 图片上传
	 * 
	 * @param file
	 * @param type
	 * @return
	 */
	public String uploadImageFile(MultipartFile file, String type);

}
