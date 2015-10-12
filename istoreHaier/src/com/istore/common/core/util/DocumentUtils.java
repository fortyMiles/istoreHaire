/**
 * @Project Haieristore
 * @Package com.istore.common.core.util
 * @Title DocumentUtils.java
 * @Description TODO
 * @CopyRight CopyRight (c) 2014
 * @Company 江苏太湖云计算信息技术股份有限公司
 *
 * @author mojilin
 * @date 2014-7-13
 * @email mojilin@wuxicloud.com
 * @version V1.0
 */
package com.istore.common.core.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @ClassName: DocumentUtils.java
 * @Description: TODO
 * @author mojilin
 * @time 2014-7-13下午4:46:58
 */
public class DocumentUtils {
	/**
	 * @param content
	 * @return
	 */
	public static String getDocumentImageSrc(String content) {
		String result = "";
		Document doc = Jsoup.parse(content);
		Elements elements = doc.getElementsByTag("img");
		if (elements.size() > 0) {
			Element element = elements.get(0);
			result = element.attr("src");
		}
		if (result.isEmpty()) {
			result = "/wcsstore/AuroraStorefrontAssetStore/css/img/newsgood.jpg";
		}
		return result;
	}

	/**
	 * @param content
	 * @return
	 */
	public static String getDocumentSummary(String content) {
		String result = "";
		Document doc = Jsoup.parse(content);
		Elements elements = doc.getElementsByTag("p");
		if (elements.size() > 0) {
			for (int i = 0; i < elements.size(); i++) {
				Element element = elements.get(0);
				if (element.hasText()) {
					result = element.text();
					break;
				}
			}
		}
		if (result.isEmpty()) {
			result = content.substring(0, 200);
		}
		return result;
	}
}
