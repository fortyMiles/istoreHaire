package com.istore.common.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.istore.common.core.bean.Catentry;
import com.istore.common.core.bean.Picture;
import com.istore.common.core.mng.CatentryMng;
import com.istore.common.web.util.BaseController;
import com.istore.common.web.util.ImageUtils;

@Controller
@RequestMapping("/catmng")
public class CatentrylistmngController extends BaseController{

	
	@Autowired
	HttpServletRequest request;
		
	 @Autowired
	 private	CatentryMng catentryMng;
	 
	 String catentid;
	
     /**
	 * 商品页面
	 */
	@RequestMapping(value="/list.do", method = RequestMethod.GET)
	public String list_cat(){
		return "catentry/catlistmng/list";
	}
	
	
	/**
	 * 查询所有商品
	 */
	@RequestMapping(value="/json.do", method = RequestMethod.POST)
	public String dingyi() throws IOException{		
		int page = Integer.valueOf(request.getParameter("page").toString());
		int rows = Integer.valueOf(request.getParameter("rows").toString());
		int startIndex = (page - 1) * rows;
		int endIndex = startIndex + rows;

		int listSize = catentryMng.getCatListSize();

		List<Catentry> CatList = catentryMng.getCatList(startIndex,endIndex);

		int total = 0;
		if (listSize % rows == 0) {
			total = listSize / rows;
		} else {
			total = listSize / rows + 1;
		}

		request.setAttribute("total", total);
		request.setAttribute("page", page);
		//request.setAttribute("records", CatList.size());
		request.setAttribute("CatList", CatList);
		return "catentry/catlistmng/json";
	}
	/**
	 * 查出要修改的商品
	 * @return
	 */
	@RequestMapping(value="/updatelist.do", method = RequestMethod.GET)
	public String update_cat(){
		String catentryId=request.getParameter("catentryId");
		List<Catentry> CatList = catentryMng.getEditCatListByID(catentryId);
		
		request.setAttribute("CatList", CatList);
		return "catentry/catlistmng/catdetail";
	}
	
	/**
	 * 更新要修改的商品
	 * @return
	 */
	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	@ResponseBody
	public String edit() {
		String description = request.getParameter("description");
		String catentryId = request.getParameter("catentryId");
		String name = request.getParameter("name");
		String shortdec = request.getParameter("shortdec");
		String partnumber = request.getParameter("partnumber");
		
		Catentry catentry=new Catentry();
		catentry.setCatentryId(Long.parseLong(catentryId));
		catentry.setDescription(description);
		catentry.setShortdescription(shortdec);
		catentry.setName(name);
		catentry.setPartnumber(partnumber);
		return catentryMng.editCatByID(catentry);				
	}
	
	/**
	 * 商品搜索
	 */
	@RequestMapping(value="/search.do", method = RequestMethod.POST)
	public String searchcat() throws IOException{	
		String key=request.getParameter("catentry_key");
		String type=request.getParameter("type");
		int page = Integer.valueOf(request.getParameter("page").toString());
		int rows = Integer.valueOf(request.getParameter("rows").toString());
		int startIndex = (page - 1) * rows;
		int endIndex = startIndex + rows;

		int listSize = catentryMng.getCatListSize(key,type);

		List<Catentry> CatList = catentryMng.getCatList(startIndex,endIndex,key,type);

		int total = 0;
		if (listSize % rows == 0) {
			total = listSize / rows;
		} else {
			total = listSize / rows + 1;
		}

		request.setAttribute("total", total);
		request.setAttribute("page", page);
		//request.setAttribute("records", CatList.size());
		request.setAttribute("CatList", CatList);
		return "catentry/catlistmng/json";
	}
	
	
	
	/**
	 * 查商品图片
	 * @return
	 */
		
		@RequestMapping(value="/imaginlist.do", method = RequestMethod.GET)
		public String list_picture(){		
			catentid=request.getParameter("catentryId");
			request.setAttribute("catentryId", catentid);		
			return "catentry/catlistmng/piclist";
		}
		@RequestMapping(value = "/picturejson.do", method = RequestMethod.POST)
		public String picture() throws IOException{
			
			int page = Integer.valueOf(request.getParameter("page").toString());
			int rows = Integer.valueOf(request.getParameter("rows").toString());
			int startIndex = (page - 1) * rows;
			int endIndex = startIndex + rows;
	        int Piccount=catentryMng.findsize(catentid);
			List<Picture> Piclist = catentryMng.getPicList(startIndex,endIndex,catentid);

			int total = 0;
			if (Piccount% rows == 0) {
				total = Piccount/ rows;
			} else {
				total = Piccount / rows + 1;
			}
			request.setAttribute("total", total);
			request.setAttribute("page", page);
			//request.setAttribute("records", CatList.size());
			request.setAttribute("Piclist", Piclist);
			request.setAttribute("catentid", catentid);
			return "catentry/catlistmng/picjson";
		}
		
		
		
		/***
		 * 上传商品列表页图片
		 * @param catentryId
		 * @param file
		 * @return
		 */
		 
		@RequestMapping(value="/product/{catentryId}/uploadListImg.do", method = RequestMethod.POST)
		@ResponseBody
		public String uploadListImg(@PathVariable("catentryId") String catentryId,@RequestParam("imageList")MultipartFile imageList,HttpServletRequest request){
			if(!imageList.isEmpty()){
				if(catentryMng.validate(imageList, 1)){
					String fileName = ImageUtils.getImageName(imageList.getOriginalFilename());
					return catentryMng.uploadListImg(catentryId,imageList,fileName,request);
				}else{
					return "该图片的长宽比例不是系统所要求！(宽度/高度=180/270)";
				}
			}else{
				return "图片不能为空!";
			}
		}
		
		/***
		 * 上传商品详情页图片
		 * @param catentryId
		 * @param file
		 * @return
		 */
		@RequestMapping(value="/uploadImg.do", method = RequestMethod.POST)
		@ResponseBody
		public String uploadImg(@RequestParam("upload")MultipartFile image,HttpServletRequest request){
			String catentryId=request.getParameter("catentryId");
			if(!image.isEmpty()){			
				if(catentryMng.validate(image, 1)){
					return catentryMng.uploadImg(catentryId,image,request);
				}else{
					return  "size";
				}
			}else{
				return "empty";
			}
		}
		
	/**
	 * 图片删除	
	 * @return
	 */
		
		@RequestMapping(value="/deleteimg.do", method = RequestMethod.POST)
		@ResponseBody
		public String delete() {
			String seq = request.getParameter("seq");
			String catentry_id = request.getParameter("catentry_id");
			return catentryMng.deleteImg(seq,catentry_id);
		}
		
		
		/**
		 * 图片顺序修改	
		 * @return
		 */
			
			@RequestMapping(value="/updateimgseq.do", method = RequestMethod.POST)
			@ResponseBody
			public String updateimagseq() {
				String newseq = request.getParameter("newseq");
				String catentry_id = request.getParameter("catentry_id");
				String seq = request.getParameter("seq");
				return catentryMng.updateimagseq(newseq,catentry_id,seq);
			}
	
	
	
	
	@RequestMapping(value="/viewCatentryPage.do",method=RequestMethod.GET)
	public String catentryPage(HttpServletRequest request, Model model){
		String catentryId = request.getParameter("catentryId");
		model.addAttribute("catentryId", catentryId);
		return "catentry/catlistmng/catentryList";
	}

	@RequestMapping(value="/viewCatentry.do",method=RequestMethod.POST)
	public String viewCatentry(HttpServletRequest request, Model model){
		model.addAttribute("catList", super.json(catentryMng.getCatentryList(request)));
		return "catalog/list-A";
	}
	
	@RequestMapping(value="viewAddCatentry.do",method=RequestMethod.POST)
	public String viewAddCatentry(HttpServletRequest request, Model model){
		
		model.addAttribute("catList", super.json(catentryMng.getAddCatentryList(request)));
		return "catalog/list-A";
	}
	
	@RequestMapping(value="/addSaleCatentry.do", method=RequestMethod.POST)
	@ResponseBody
	public String addSaleCatentryRel(HttpServletRequest request){
		Gson gson = new Gson();
		try {
			catentryMng.addSaleCatentry(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return gson.toJson("false");
		}
		
		return gson.toJson("true");
	}
	
	@RequestMapping(value="deleteCatentry.do", method=RequestMethod.GET)
	@ResponseBody
	public String deleteCatentryRel(HttpServletRequest request){
		Gson gson = new Gson();
		try {
			catentryMng.deleteCatentryRel(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return gson.toJson("false");
		}
		
		return gson.toJson("true");
	}
	
	@RequestMapping(value="modifySeq.do", method=RequestMethod.POST)
	@ResponseBody
	public String modifySeq(HttpServletRequest request){
		Gson gson = new Gson();
		try {
			catentryMng.modifySeq(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return gson.toJson("false");
		}
		return gson.toJson("true");
	}
	
	
	
	
}
