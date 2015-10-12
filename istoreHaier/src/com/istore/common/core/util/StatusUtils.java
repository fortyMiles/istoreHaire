/**
 * @Project istoreHaier
 * @Package com.istore.common.core.util
 * @Title StatusUtils.java
 * @Description TODO
 * @CopyRight CopyRight (c) 2014
 * @Company 江苏太湖云计算信息技术股份有限公司
 *
 * @author mojilin
 * @date 2014-7-16
 * @email mojilin@wuxicloud.com
 * @version V1.0
 */
package com.istore.common.core.util;

/**
 * @ClassName: StatusUtils.java
 * @Description: TODO
 * @author mojilin
 * @time 2014-7-16下午2:33:51
 */
public class StatusUtils {
	/**
	 * @param content
	 * @return
	 */
	public static String getStatusName(String status) {
		String statusName = "";
		if ("D".equals(status)) {
			statusName = "草稿";
		} else if ("W".equals(status)) {
			statusName = "待审核";
		} else if ("R".equals(status)) {
			statusName = "未通过";
		} else if ("P".equals(status)) {
			statusName = "已发布";
		} else if ("C".equals(status)) {
			statusName = "已删除";
		}
		return statusName;
	}
}