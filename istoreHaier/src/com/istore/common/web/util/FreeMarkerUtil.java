/**
 * @Projct Name:WebSphereCommerceServerExtensionsLogic
 * @Title FreeMarkerUtil.java
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.rmi.RemoteException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.ibm.commerce.exception.ECException;
import com.ibm.commerce.exception.ECSystemException;
import com.ibm.commerce.ras.ECMessage;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @ClassName: FreeMarkerUtil.java
 * @Description: TODO
 * @author mojilin
 * @date 2014-3-24下午04:52:35
 * 
 */
/**
 * FreeMarker工具
 */
public class FreeMarkerUtil {

	private static final Logger log = LoggerFactory
			.getLogger(FreeMarkerUtil.class);

	private static final String CLASSNAME = FreeMarkerUtil.class.getName();

	// 字符编码
	private String charset = "UTF-8";

	/***
	 * 
	 * @param templateName
	 *            模板文件名称
	 * @param fileName
	 *            生成的文件去路径保存文件名称
	 * @param parameter
	 *            动态参数
	 * @param ftpType
	 *            FTP类别
	 * @param templateName
	 *            模板名称
	 * @param fileName
	 *            文件名称
	 * @param parameter
	 *            参数
	 * @param path
	 *            路径
	 * @param filePath
	 *            文件路径
	 * @throws ECException
	 */
/*	public void analysisTemplate(String ftpType, String templateName,
			String[] fileName, Map<?, ?> parameter, String path, String filePath)
			throws ECException {
		final String METHODNAME = "analysisTemplate()";
		try {
			log.info("starting process freemark temp. this template name is "
					+ templateName);
			log.info("starting process freemark temp. this template path is "
					+ path);
			Configuration config = new Configuration();
			config.setDirectoryForTemplateLoading(new File(path));
			config.setObjectWrapper(new DefaultObjectWrapper());
			config.setDefaultEncoding(this.getCharset());
			Template template = config.getTemplate(templateName, this
					.getCharset());
			log.info(filePath + fileName[0] + "/" + fileName[1]);
			FileOutputStream fos = new FileOutputStream(filePath + fileName[0]
					+ "/" + fileName[1]);
			Writer out = new OutputStreamWriter(fos, this.getCharset());
			template.process(parameter, out);
			out.flush();
			out.close();
			log.info("end process freemark temp.");
			log.info("start to ftp html");
			FtpAppClientUtil ftpAppClientUtil = new FtpAppClientUtil();
			ftpAppClientUtil.uploadFile(ftpType, filePath + fileName[0] + "/"
					+ fileName[1], fileName[0], fileName[1]);
			log.info("end to ftp html");
		} catch (RemoteException e) {
			throw new ECSystemException(ECMessage._ERR_REMOTE_EXCEPTION,
					CLASSNAME, METHODNAME, e);
		} catch (IOException e) {
			log.error("IOException", e);
			throw new ECSystemException(ECMessage._ERR_IO_EXCEPTION, CLASSNAME,
					METHODNAME, e);
		} catch (TemplateException e) {
			log.error("TemplateException", e);
			throw new ECSystemException(ECMessage._ERR_ECDB_EXCEPTION,
					CLASSNAME, METHODNAME, e);
		}
	}*/

	/**分析模板
	 * @param template
	 *            模板
	 * @param param
	 *            參數
	 * @return
	 * @throws ECException
	 */
	public String analysisTemplate1(Template template, Object param)
			throws ECException {
		final String METHODNAME = "analysisTemplate1()";
		String html = "";
		try {
			html = FreeMarkerTemplateUtils.processTemplateIntoString(template,
					param);
		} catch (IOException e) {
			log.error("IOException", e);
			throw new ECSystemException(ECMessage._ERR_IO_EXCEPTION, CLASSNAME,
					METHODNAME, e);
		} catch (TemplateException e) {
			log.error("TemplateException", e);
			throw new ECSystemException(ECMessage._ERR_ECDB_EXCEPTION,
					CLASSNAME, METHODNAME, e);
		}
		return html;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

}
