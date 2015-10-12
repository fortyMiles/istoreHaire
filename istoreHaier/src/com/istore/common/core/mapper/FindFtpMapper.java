/**
 * @Project istoreHaier
 * @Package com.istore.common.core.mapper
 * @Title FindFtpMapper.java
 * @Description TODO
 * @CopyRight CopyRight (c) 2014
 * @Company 江苏太湖云计算信息技术股份有限公司
 *
 * @author mojilin
 * @date 2014-7-15
 * @email mojilin@wuxicloud.com
 * @version V1.0
 */
package com.istore.common.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;

import com.istore.common.core.bean.FTP;
import com.istore.common.core.provider.FtpSqlProvider;

/**
 * @ClassName: FindFtpMapper.java
 * @Description: TODO
 * @author mojilin
 * @time 2014-7-15下午1:27:42
 */
public interface FindFtpMapper {

	/**
	 * 获得FTP列表
	 * 
	 * @param type
	 * @return
	 */
	@SelectProvider(type = FtpSqlProvider.class, method = "getFtpListByType")
	@Results(value = { @Result(column = "id", property = "id", id = true),
			@Result(column = "host", property = "host"),
			@Result(column = "port", property = "port"),
			@Result(column = "username", property = "username"),
			@Result(column = "password", property = "password"),
			@Result(column = "xpath", property = "xpath"),
			@Result(column = "type", property = "type") })
	public List<FTP> getFtpListByType(@Param("type") String type);

}
