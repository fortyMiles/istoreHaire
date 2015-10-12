package com.istore.common.core.mng.impl;

import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import com.istore.common.core.bean.Catentry;
import com.istore.common.core.bean.CatentryImage;
import com.istore.common.core.bean.ImageFile;
import com.istore.common.core.bean.Picture;
import com.istore.common.core.dao.CatentryDao;
import com.istore.common.core.mng.CatentryMng;
import com.istore.common.web.util.DateFormatUtil;
import com.istore.common.web.util.FtpWeb1ClientUtil;
import com.istore.common.web.util.ImageUtils;
import com.istore.common.web.util.JsonResult;
import com.istore.common.web.util.UploadImageWeb1Task;
import com.istore.common.web.util.UploadImageWeb2Task;
@Service
@Transactional
public class CatentryMngImpl implements CatentryMng {
	@Autowired
	public CatentryDao catDao;
	
	@Autowired
	private FtpWeb1ClientUtil ftpWeb1ClientUtil;
	
	@Autowired
	private TaskExecutor taskExecutor;
	
	
	public void updateDisplay(String catentryId) {
		if (catDao.findRows(catentryId, "1") == 0) {
			catDao.updateField(catentryId, "1");
			catDao.updateSolrIndex(catentryId);
			catDao.updateLastupdate(catentryId);
		}
	}
	
	
/**
 * 查询所有商品列表
 */
	public List<Catentry> getCatList(int startIndex, int endIndex) {
		

		return  catDao.getCatList(startIndex, endIndex);
	}
/**
 * 查询所有商品数量
 */
	public int getCatListSize() {
		
		return  catDao.getCatListSize();
	}
	/**
	 * 搜索商品列表
	 */
		public List<Catentry> getCatList(int startIndex, int endIndex,String key,String type) {
			

			return  catDao.getCatList(startIndex, endIndex,key,type);
		}
	/**
	 * 搜索商品数量
	 */
		public int getCatListSize(String key,String type) {
			
			return  catDao.getCatListSize(key,type);
		}
	
public List<Catentry> getEditCatListByID(String catentryId) {
		
		return catDao.getEditCatListByID(catentryId);
	}

	public String editCatByID(Catentry catentry) {
		
		return catDao.editCatByID(catentry);
	}
	
	
	//中户图片 处理方法
	public boolean validate(MultipartFile image, int i) {
	//	log.info("____image validate!");
		try {
			if (1== i) {
				
				return ImageUtils.validate(image.getBytes(),720,720);
			}else if(2 == i){
				
				return ImageUtils.validate(image.getBytes(), 350,525);
			}
			//活动
			else if(3 == i){
              
                return ImageUtils.validate(image.getBytes(), 480, 164);
            }
			//组合款
			else if(4 == i){
            
                return ImageUtils.validate(image.getBytes(), 1248, 118);
            }
			//二级幻灯片
			else if(5 == i){
                
                return ImageUtils.validate(image.getBytes(), 980, 355);
            }
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
		
	}

	
	//列表页图片
	public String uploadListImg(String catentryId, MultipartFile imageList,
			String fileName,HttpServletRequest request) {

		try {
			List<ImageFile> imageFiles = new ArrayList<ImageFile>();
			List<ImageFile> web2 = new ArrayList<ImageFile>();
			// 上传列表页图片 176*234
			String dateFile = DateFormatUtil.getDate();
			byte[] bytes = imageList.getBytes();
			ImageFile s = new ImageFile();
			s.setHeight(234);
			s.setWidth(176);
			s.setName(fileName);
			s.setPath(new String[] { "product", "w176h234", dateFile });
			s.setBytes(bytes);
			imageFiles.add(s);
			web2.add(s);
			ftpWeb1ClientUtil.uploadFile(imageFiles);//上传图片到服务器web1测试机开启
//			HostUploadImagesUtil imagesUtil= new HostUploadImagesUtil();//本地开启
//			uploadImagesHost(imageFiles,request);//上传到本地
//			imagesUtil.uploadImagesHost(imageFiles);//本地开启
			CatentryImage catentryImage = new CatentryImage();
			catentryImage.setCatentryId(catentryId);
			catentryImage.setImageUrl(dateFile + "/" + fileName);
			catDao.updateImage(catentryImage);
			catDao.updateSolrIndex(catentryId);

			// 上传列表页大图片 250*316
			ImageFile b = new ImageFile();
			b.setHeight(316);
			b.setWidth(250);
			b.setName(fileName);
			b.setPath(new String[] { "product", "w250h316", dateFile });
			b.setBytes(bytes);
			List<ImageFile> imageFileAysc = new ArrayList<ImageFile>();
			imageFileAysc.add(b);
			web2.add(b);
			taskExecutor.execute(new UploadImageWeb1Task(imageFileAysc));//测试机
			taskExecutor.execute(new UploadImageWeb2Task(web2));//测试机开启
//			uploadImagesHost(imageFileAysc,request);//本地开启
//			imagesUtil.uploadImagesHost(imageFileAysc);//本地开启
//			uploadImagesHost(web2,request);//本地开启
//			imagesUtil.uploadImagesHost(web2);//本地开启
			
			this.catDao.updateLastupdate(catentryId);
			return dateFile+"/"+fileName;

		} catch (SocketException e) {
			e.printStackTrace();
			return "上传失败！";
		} catch (IOException e) {
			e.printStackTrace();
			return "上传失败！";
		}

	}
		
	/**
	 * 查询图片
	 */
public List<Picture> getPicList(int startIndex, int endIndex,String catentid) {
		
		return catDao.getPicList(startIndex,endIndex,catentid);
	}
public int findsize(String catentid) {
	
	return catDao.findsize(catentid);
}


//详情页图片(中化)
public String uploadImg(String catentryId, MultipartFile image,HttpServletRequest request) {
	try {
		//String dateFile = DateFormatUtil.getDate();//上传图片日期
		List<ImageFile> imageFiles = new ArrayList<ImageFile>();
		List<ImageFile> web2 = new ArrayList<ImageFile>();
		String fileName =image.getOriginalFilename();
		//String fileName = ImageUtils.getImageName(image.getOriginalFilename());//随机生成图片名
		CatentryImage catentryImage = new CatentryImage();
		catentryImage.setCatentryId(catentryId);
		catentryImage.setUseage(1);
		catentryImage.setImageUrl(fileName);
		catDao.save(catentryImage);
		

		this.updateDisplay(catentryId);
		
		List<ImageFile> image90 = new ArrayList<ImageFile>();
		byte[] bytes = image.getBytes();
		
		ImageFile ss = new ImageFile();
		ss.setHeight(75);
		ss.setWidth(75);
/*			s1.setHeight(60);
		s1.setWidth(40);*/
		ss.setName(fileName);
		ss.setBytes(bytes);
		ss.setPath(new String[] { catentryId, "e" });
		image90.add(ss);
		web2.add(ss);
		
		ImageFile s1 = new ImageFile();
		s1.setHeight(100);
		s1.setWidth(100);
/*			s1.setHeight(60);
		s1.setWidth(40);*/
		s1.setName(fileName);
		s1.setBytes(bytes);
		s1.setPath(new String[] { catentryId, "d" });
		image90.add(s1);
		web2.add(s1);
		//ftpWeb1ClientUtil.uploadFile(image90);//测试机房
		
//		byte[] bytes = image.getBytes();
		ImageFile s = new ImageFile();
		s.setHeight(200);
		s.setWidth(200);
/*			s.setHeight(60);
		s.setWidth(40);*/
		s.setName(fileName);
		s.setBytes(bytes);
		s.setPath(new String[] { catentryId, "c" });
		image90.add(s);
		web2.add(s);
		//ftpWeb1ClientUtil.uploadFile(imageFiles);//测试机房
//		HostUploadImagesUtil imagesUtil= new HostUploadImagesUtil();//本地开启
//		uploadImagesHost(imageFiles,request);//本地开启
//		imagesUtil.uploadImagesHost(imageFiles);//本地开启
		

		// 传送异步上传大图片
		List<ImageFile> imageFilesAysc = new ArrayList<ImageFile>();
		ImageFile m = new ImageFile();
/*			m.setHeight(450);
		m.setWidth(300);*/
		m.setHeight(360);
		m.setWidth(360);
		m.setName(fileName);
		m.setPath(new String[] { catentryId, "b" });
		m.setBytes(bytes);
		image90.add(m);
		web2.add(m);
		//ftpWeb1ClientUtil.uploadFile(imageFilesAysc);//测试机房
		List<ImageFile> images = new ArrayList<ImageFile>();
		ImageFile b = new ImageFile();
		b.setHeight(720);
		b.setWidth(720);
		b.setName(fileName);
		b.setPath(new String[] { catentryId, "a" });
		b.setBytes(bytes);
		image90.add(b);
		web2.add(b);
		ftpWeb1ClientUtil.uploadFile(image90);//测试机房
		
//		taskExecutor.execute(new UploadImageWeb1Task(imageFilesAysc));//测试机开启
//		taskExecutor.execute(new UploadImageWeb2Task(web2));//测试机开启
//		uploadImagesHost(imageFilesAysc,request);//本地开启
//		imagesUtil.uploadImagesHost(imageFilesAysc);//本地开启
//		uploadImagesHost(web2,request);//本地开启
//		imagesUtil.uploadImagesHost(web2);//本地开启
		this.catDao.updateLastupdate(catentryId);
		return "success";

	} catch (SocketException e) {
		e.printStackTrace();
		return "failure";
	} catch (IOException e) {
		e.printStackTrace();
		return "failure";
	}

}


/**
 * 删除图片
 */

public String deleteImg(String seq,String catentry_id) {
	return catDao.deleteImg(seq,catentry_id);
}

/**
 * 图片修改顺序
 */

public String updateimagseq(String newseq, String catentry_id, String seq) {
	// TODO Auto-generated method stub
	return catDao.updateimagseq(newseq,catentry_id,seq);
}

	
	
	
	
	
	public JsonResult getCatentryList(HttpServletRequest request) {
		long catentry_id = Long.parseLong(request.getParameter("catentryid"));
		long storeId = Long.parseLong(String.valueOf(request.getSession().getAttribute("storeId")));
		
		long markforDelete = 0;
		int count = catDao.getCatentryListSize(catentry_id,storeId, markforDelete);
		
		Map<String, Integer> pageInfo = pageInfo(count, request);
		
		List<Catentry> list = catDao.getCatentryList(catentry_id,storeId, markforDelete, pageInfo.get("startIndex"), pageInfo.get("endIndex"));
		return new JsonResult(pageInfo.get("page"), pageInfo.get("total"), list);
		
	}
	/**
	 * 分页数据
	 */
	public Map<String, Integer> pageInfo(int count, HttpServletRequest request){
		int page = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("rows"));
		int total = count%pageSize==0?count/pageSize:count/pageSize+1;
		int startIndex = (page-1)*pageSize;
		int endIndex = (page)*pageSize;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("total", total);
		map.put("startIndex", startIndex);
		map.put("endIndex", endIndex);
		map.put("page", page);
		return map;
	}

	public JsonResult getAddCatentryList(HttpServletRequest request) {
		String partnumber = request.getParameter("partnumber");
		String catentryName = request.getParameter("catentryName");
		String storeId = (String)request.getSession().getAttribute("storeId");
		if(partnumber==null || partnumber.trim().equals("")){
			partnumber="%%";
		}else{
			partnumber = "%"+partnumber+"%";
		}
		if(catentryName==null || catentryName.trim().equals("")){
			catentryName="%%";
		}else{
			catentryName = "%"+catentryName+"%";
		}
		int count = catDao.getAddCatentryListSize(partnumber, catentryName,storeId);
		Map<String, Integer> pageInfo = pageInfo(count, request);
		List<Catentry> list = catDao.getAddCatentryList(partnumber, catentryName ,pageInfo.get("startIndex"), pageInfo.get("endIndex"), storeId);
		
		
		return new JsonResult(pageInfo.get("page"), pageInfo.get("total"), list);
	}

	public void addSaleCatentry(HttpServletRequest request) throws Exception {
		String[] ids = new String[]{};
		ids = request.getParameter("selectedIds").split(",");
		long storeId = Long.parseLong(String.valueOf(request.getSession().getAttribute("storeId")));
		long catentryId = Long.parseLong(request.getParameter("catentryId"));
		List<Catentry> existList = catDao.getexistCatentry(storeId,catentryId);
		boolean flag = true;
		for (int i = 0; i < ids.length; i++) {
			flag=true;
			if(ids[i].equals(catentryId+"")){
				continue;
			}
			for (int j = 0; j < existList.size(); j++) {
				if(ids[i].equals(existList.get(j).getCatentryId()+"")){
					flag =false;
				}
			}
			if(flag){
				catDao.insertSaleCatentry(Long.parseLong(ids[i]), storeId, catentryId);
			}
			
		}
		
	}

	public void deleteCatentryRel(HttpServletRequest request) throws Exception {
		String catentry_id = request.getParameter("catentry_id");
		String catentry_id_rel = request.getParameter("catentry_id_rel");
		long storeId = Long.parseLong(String.valueOf(request.getSession().getAttribute("storeId")));
		catDao.deleteCatentry(Long.parseLong(catentry_id),Long.parseLong(catentry_id_rel),storeId);
	}

	public void modifySeq(HttpServletRequest request) throws Exception {
		long catentry_id = Long.parseLong(request.getParameter("catentry_id"));
		long catentry_id_rel = Long.parseLong(request.getParameter("catentry_id_rel"));
		long seq = Long.parseLong(request.getParameter("seq"));
		long storeId = Long.parseLong(String.valueOf(request.getSession().getAttribute("storeId")));
		catDao.modifySeq(catentry_id,catentry_id_rel,seq,storeId);
	}
	
}
