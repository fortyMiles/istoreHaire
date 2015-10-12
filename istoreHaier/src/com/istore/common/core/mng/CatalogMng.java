package com.istore.common.core.mng;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.istore.common.web.util.JsonResult;

/**
 * @author jiangtao
 * 
 */
public interface CatalogMng {

	/**
	 * 获得一级目录列表详情.
	 * 
	 * @param request
	 * @return JsonResult
	 */
	public JsonResult getCatgroupList(HttpServletRequest request);

	/**
	 * 获得一级目录列表详情.
	 * 
	 * @param request
	 * @return List
	 */
	@SuppressWarnings("rawtypes")
	public List getCatgroupLists(HttpServletRequest request);

	/**
	 * 获得下级目录列表详情.
	 * 
	 * @param request
	 * @return
	 */
	public JsonResult getCatDetailList(HttpServletRequest request);

	/**
	 * 添加目录.
	 * 
	 * @param request
	 * @throws Exception
	 */
	public void addCatalog(HttpServletRequest request) throws Exception;

	/**
	 * 更新目录名称.
	 * 
	 * @param request
	 * @throws Exception
	 */
	public void updateCatgroupName(HttpServletRequest request) throws Exception;

	/**
	 * 删除目录.
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String deleteCatalog(HttpServletRequest request) throws Exception;

	/**
	 * 获得已挂目录的商品详情.
	 * 
	 * @param request
	 * @return
	 */
	public JsonResult getCatentryList(HttpServletRequest request);

	/**
	 * 获得可挂商品详情.
	 * 
	 * @param request
	 * @return
	 */
	public JsonResult getAddCatentryList(HttpServletRequest request);

	/**
	 * 将商品挂入目录.
	 * 
	 * @param request
	 * @throws Exception
	 */
	public void addSaleCatentry(HttpServletRequest request) throws Exception;

	/**
	 * 删除目录商品关联.
	 * 
	 * @param request
	 * @throws Exception
	 */
	public void deleteCatentry(HttpServletRequest request) throws Exception;

	/**
	 * 一二级目录图片上传
	 * 
	 * @param file
	 * @param request
	 * @throws Exception
	 */
	public void uploadImage(MultipartFile file, HttpServletRequest request)
			throws Exception;

}
