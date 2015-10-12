/**
 * 
 */
package com.istore.common.core.bean;

import java.io.Serializable;

/**
 * @author wangyan
 *
 */
public class Advert  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	//广告位类型
	private int type;
	
	//广告位文字说明
	private String title;
	//广告位配置的URL
	private String url;
	//广告位图片
	private String image;
	
	//优先级
	private int priority;
	
	private String prodouctGroup;
	
	//商品目录名称
	private String name;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProdouctGroup() {
		return prodouctGroup;
	}
	public void setProdouctGroup(String prodouctGroup) {
		this.prodouctGroup = prodouctGroup;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
	

}
