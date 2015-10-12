package com.istore.common.core.mng;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.istore.common.core.bean.Catentry;
import com.istore.common.core.bean.Picture;
import com.istore.common.web.util.JsonResult;



public interface CatentryMng {
	/**
	 * 商品列表
	 * @return
	 */
	public int getCatListSize();
	public List<Catentry> getCatList(int startIndex, int endIndex);
	/**
	 * 搜索商品列表
	 * @return
	 */
	public int getCatListSize(String key,String type);
	public List<Catentry> getCatList(int startIndex, int endInde,String key,String type);
	
	/**
	 * 获得当前编辑商品
	 * 
	 */
	public List<Catentry> getEditCatListByID(String catentryId);
	
	/**
	 * 保存商品
	 * 
	 */
	public String editCatByID(Catentry catentry);
	

	/**
	 * 图片查询
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	public List<Picture> getPicList(int startIndex, int endIndex,String catentid);
	public int findsize(String catentid);
	
	
	
	public boolean validate(MultipartFile image,int i);	
	
	
	/***
	 * 上传商品列表页图片
	 * @param catentryId
	 * @param imageList
	 * @param fileName
	 */
	public String uploadListImg(String catentryId,MultipartFile imageList,String fileName,HttpServletRequest request);
	
	
	/***
	 * 上传商品详情页图片
	 * @param catentryId
	 * @param image
	 * @return
	 */
	public String uploadImg(String catentryId,MultipartFile image,HttpServletRequest request);
	
	/**
	 * 删除图片
	 * @param catentryId
	 * @param image
	 * @param request
	 * @return
	 */
	public String deleteImg(String seq,String catentry_id);
	
	/**
	 * 图片修改
	 */
	public String updateimagseq(String newseq,String catentry_id,String seq);
	public JsonResult getCatentryList(HttpServletRequest request);
	
	public JsonResult getAddCatentryList(HttpServletRequest request);
	
	public void addSaleCatentry(HttpServletRequest request) throws Exception;
	
	public void deleteCatentryRel(HttpServletRequest request) throws Exception;
	
	public void modifySeq(HttpServletRequest request) throws Exception;
	
	
	
}
