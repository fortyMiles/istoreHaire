/**
 * @Project istoreHaier
 * @Package com.istore.common.core.dao.impl
 * @Title FtpDaoImpl.java
 * @Description TODO
 * @CopyRight CopyRight (c) 2014
 * @Company 江苏太湖云计算信息技术股份有限公司
 *
 * @author mojilin
 * @date 2014-7-15
 * @email mojilin@wuxicloud.com
 * @version V1.0
 */

package com.istore.common.core.dao.impl;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import sun.net.TelnetOutputStream;
import sun.net.ftp.FtpClient;

import com.istore.common.core.bean.FTP;
import com.istore.common.core.dao.FtpDao;
import com.istore.common.core.mapper.FindFtpMapper;

/**
 * @ClassName: FtpDaoImpl.java
 * @Description: TODO
 * @author mojilin
 * @time 2014-7-15下午1:20:05
 */
@Repository
public class FtpDaoImpl implements FtpDao {

	@Autowired
	private FindFtpMapper findFtpMapper;

	/*
	 * 获得FTP列表
	 * 
	 * @see com.istore.common.core.dao.FtpDao#getFtpListByType(java.lang.String)
	 */
	public List<FTP> getFtpListByType(String type) {
		return findFtpMapper.getFtpListByType(type);
	}

	/*
	 * KindEditor图片上传
	 * 
	 * @see com.istore.common.core.dao.FtpDao#uploadImageFileByKindEditor(org.
	 * springframework.web.multipart.MultipartFile, java.lang.String)
	 */
	public String uploadImageFile(MultipartFile file, String type) {
		String finalfilename = "";
		try {
			if (!file.isEmpty()) {
				// WEB图片显示路径
				String _WEB_PATH = "";
				// 获得上传文件流
				InputStream in = file.getInputStream();

				// 获得上传文件类型
				String fileType = file.getContentType();

				// 随机数生成
				String uuid = UUID.randomUUID().toString();

				// 服务器上传文件名
				String remoteFile = uuid + "." + fileType.substring(6);

				// 读取FTP列表
				List<FTP> ftpList = findFtpMapper.getFtpListByType(type);
				if (ftpList.size() > 0) {
					for (int i = 0; i < ftpList.size(); i++) {

						// 获得主机IP地址
						String host = ftpList.get(0).getHost();

						// 获得端口号
						String port = ftpList.get(0).getPort();

						// 获得用户名
						String username = ftpList.get(0).getUsername();

						// 获得密码
						String password = ftpList.get(0).getPassword();

						// 获得文件上传路径
						String xpath = ftpList.get(0).getXpath();

						// 获得WEB图片显示路径
						_WEB_PATH = xpath.substring(28);

						// 初始化FtpClient
						FtpClient ftpClient = new FtpClient(host,
								Integer.valueOf(port));

						// 登录FTP服务器
						ftpClient.login(username, password);

						// 打开目标文件夹
						ftpClient.cd(xpath);

						// 以二进制的方式上传
						ftpClient.binary();

						// 向服务器写入文件
						TelnetOutputStream telOut = ftpClient.put(remoteFile);
						DataOutputStream out = new DataOutputStream(telOut);

						// 再次上传，重置文件流
						if (i != 0) {
							in.reset();
						}

						// 按字节读取文件，并写入
						byte[] b = new byte[1024];
						int temp;
						while ((temp = in.read(b)) != -1) {
							out.write(b, 0, temp);
						}
					}
				}

				// 获得WEB显示文件和路径名
				finalfilename = _WEB_PATH + remoteFile;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return finalfilename;
	}
}
