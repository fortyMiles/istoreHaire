package com.istore.common.web.controller;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import sun.net.TelnetOutputStream;
import sun.net.ftp.FtpClient;

import com.istore.common.core.bean.Advert;
import com.istore.common.core.bean.Catalog;
import com.istore.common.core.bean.FTP;
import com.istore.common.core.mng.AdvertMng;
import com.istore.common.core.mng.CatalogMng;
import com.istore.common.web.util.BaseController;

/**
 * @author wangyan
 * 
 */


@Controller
@RequestMapping("/advert")
public class AdvertController extends BaseController {

	@Autowired
	HttpServletRequest request;
	@Autowired
	private AdvertMng advertMng;
	
	@Autowired
	private CatalogMng CatalogMng;
	
	String storeId; //request.getParameter("storeId");

	@RequestMapping(value = "/advertUpList.do", method = RequestMethod.GET)
	public String advertUpList() {
		String type=request.getParameter("type").toString();
	    request.setAttribute("type", type) ;
	  String priority=  advertMng.findMaxPriority(type);
	  request.setAttribute("priority",Integer.valueOf(priority)+1);
	  
		return "advert/advertUp";
	}

	@RequestMapping(value = "/advertUpListJson.do", method = RequestMethod.POST)
	public String advertUpListJson() {
		String advertType=request.getParameter("advertType");
		storeId=request.getSession().getAttribute("storeId").toString();
		int page = Integer.valueOf(request.getParameter("page").toString());
		int rows = Integer.valueOf(request.getParameter("rows").toString());
		int startIndex = (page - 1) * rows;
		int endIndex = startIndex + rows;
		int listSize = advertMng.getAdvertListSize(Integer.valueOf(advertType),storeId);
		
		int total = 0;
		if (listSize % rows == 0) {
			total = listSize / rows;
		} else {
			total = listSize / rows + 1;
		}
		
		
		List<Advert> advertListJson = advertMng.getAdvertUpList(Integer.valueOf(advertType),startIndex,endIndex,Integer.valueOf(storeId));
	
        
		request.setAttribute("total", 1);
		request.setAttribute("page", page);
		request.setAttribute("records", advertListJson.size());
		request.setAttribute("advertListJson", advertListJson);
		if(Integer.valueOf(advertType)!=3){
			return "advert/json";
		}else{
			return "advert/json3";
		}
	}

	@RequestMapping(value = "/advertDownList.do", method = RequestMethod.GET)
	public String advertDownList() {

		return "advert/list";
	}

	@RequestMapping(value = "/advertSecList.do", method = RequestMethod.GET)
	public String advertSecList() {

		return "advert/list";
	}
	
	
	@RequestMapping(value = "/WebAdvertUpload.do", method = RequestMethod.POST)
	@ResponseBody
	public String uploadImg(HttpServletResponse response,@RequestParam("uploadfile") MultipartFile file) {
		
		
		response.setCharacterEncoding("utf-8");
		String m="";
		
		try {
			
			int isOK=0;
		    if(!file.isEmpty())
		    {
		    	
			InputStream in = file.getInputStream();	
			String filename=file.getOriginalFilename();
		    String uuid=UUID.randomUUID().toString();
		  List<FTP> list= advertMng.getFTPinfo();
		  for(int i=0;i<list.size();i++)
		  {  
			  String host=list.get(i).getHost();
		     String port=list.get(i).getPort();
		     String username=list.get(i).getUsername();
		     String password=list.get(i).getPassword();
		     String xpath=list.get(i).getXpath();
		     
		  
			FtpClient ftpClient =new FtpClient(host,Integer.valueOf(port));
			ftpClient.login(username, password); // 登录FTP服务器
			ftpClient.cd(xpath); // 图片目标目录
			ftpClient.binary();
			 TelnetOutputStream telOut = ftpClient.put(uuid+filename);
			 DataOutputStream out = new DataOutputStream(telOut);
			 byte[] b = new byte[1024];
			 int temp;
			 if(i!=0)
			 {
			 in.reset();
			 while ((temp = in.read(b)) != -1) {
			 out.write(b, 0, temp);
			 }	 }
			 else {
				 while ((temp = in.read(b)) != -1) {
					 out.write(b, 0, temp);
					 }	
		 }
		  }
		  String finalfilename=uuid+filename;
			 request.setAttribute("imagePath", finalfilename);
			
		  isOK = 1;
		 m = "{\"message\":\"" + isOK + "\",finalfilename:\""
					+ finalfilename + "\"}";
		} 
		    
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return m;
	}
	
	
	@RequestMapping(value = "/WebAdvertUploadEdit.do", method = RequestMethod.POST)
	@ResponseBody
	public String uploadImgEdit(HttpServletResponse response,@RequestParam("uploadfile1") MultipartFile file) {
		
		
		response.setCharacterEncoding("utf-8");
		String m="";
		
		try {
			
			int isOK=0;
		    if(!file.isEmpty())
		    {
		    	
			InputStream in = file.getInputStream();	
			String filename=file.getOriginalFilename();
		    String uuid=UUID.randomUUID().toString();
		  List<FTP> list= advertMng.getFTPinfo();
	  for(int i=0;i<list.size();i++)
	  {
			  String host=list.get(i).getHost();
		     String port=list.get(i).getPort();
		     String username=list.get(i).getUsername();
		     String password=list.get(i).getPassword();
		     String xpath=list.get(i).getXpath();
		     
		  
			FtpClient ftpClient =new FtpClient(host,Integer.valueOf(port));
			ftpClient.login(username, password); // 登录FTP服务器
			ftpClient.cd(xpath); // 图片目标目录
			ftpClient.binary();
			 TelnetOutputStream telOut = ftpClient.put(uuid+filename);
			 DataOutputStream out = new DataOutputStream(telOut);
			 byte[] b = new byte[1024];
			 int temp;
			 if(i!=0)
			 {
				 in.reset();
			 while ((temp = in.read(b)) != -1) {
			 out.write(b, 0, temp);
			 }	 }
			 else {
				 while ((temp = in.read(b)) != -1) {
					 out.write(b, 0, temp);
					 }	
		 }
			 }
			 
		  
		    
		  //
		  String finalfilename=uuid+filename;
			 request.setAttribute("imagePath", finalfilename);
			
		  isOK = 1;
		 m = "{\"message\":\"" + isOK + "\",finalfilename:\""
					+ finalfilename + "\"}";
		} 
		    
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return m;
	}
	
	@RequestMapping(value = "/advertImageAdd.do", method = RequestMethod.POST)
	@ResponseBody
	public String advertImageAdd() {
		String priority=request.getParameter("priority");
		String url=request.getParameter("link");
		String imageUrl=request.getParameter("imageUrl");
	
		 String desc=request.getParameter("desc");
		String type=request.getParameter("type");
		String prodouctGroup=request.getParameter("prodouctGroup");
		if(prodouctGroup==null || prodouctGroup.equals("")){
			prodouctGroup="";
		}
		//String storeId=request.getSession().getAttribute("storeId").toString();
		return advertMng.advertImageAdd(Integer.valueOf(priority),url,imageUrl,desc,Integer.valueOf(type),prodouctGroup,storeId);
	}
	

	@RequestMapping(value = "/editAdvert.do", method = RequestMethod.POST)
	public String editAdvert() {
		String advertId =request.getParameter("advert_id");
		storeId=request.getSession().getAttribute("storeId").toString();
		String advertType =request.getParameter("advertType");
		if(Integer.valueOf(advertType)==3){
			 request.setAttribute("storeId", storeId) ;
			
			List<Catalog> cataloglist=CatalogMng.getCatgroupLists(request);
			request.setAttribute("catalogList", cataloglist);
		}
		request.setAttribute("advertType", advertType);
		
		List<Advert>  advertlist=advertMng.getAdvertById(Integer.valueOf(advertId));
		
		
         request.setAttribute("advertlist", advertlist);
		return "advert/edit";
	}
	
	@RequestMapping(value = "/addAdvert.do", method = RequestMethod.POST)
	public String addAdvert() {
		String advertType =request.getParameter("advertType");
		if(Integer.valueOf(advertType)==3){
	String storeId=request.getSession().getAttribute("storeId").toString();
			 //request.setAttribute("storeId", storeId) ;
		//	 request.setAttribute("page", "10051") ;
			List<Catalog> cataloglist=CatalogMng.getCatgroupLists(request);
			request.setAttribute("catalogList", cataloglist);
		}
		request.setAttribute("advertType", advertType);
		
		return "advert/dialog";
	}
	
	@RequestMapping(value = "/edit.do", method = RequestMethod.POST)
	@ResponseBody
	public String edit() {
		String advert_id=request.getParameter("advert_id");
		String finalfilename =request.getParameter("finalfilename");
		String link =request.getParameter("link");
		String desc =request.getParameter("desc");
		String prodouctGroup=request.getParameter("prodouctGroup");
		String advertType=request.getParameter("advertType");
		if(prodouctGroup==null || prodouctGroup.equals("")){
			prodouctGroup="";
		}
	
		
		
		return advertMng.updateAdvert(Integer.valueOf(advert_id),finalfilename,link,desc,prodouctGroup);
	}
	
	
	@RequestMapping(value="/delete.do",method=RequestMethod.POST)
	@ResponseBody
	public String deleteAdvert()
	{
		String advertId=request.getParameter("advert_id");
		
		return advertMng.deleteAdvert(Integer.valueOf(advertId));
		
	}
	
	
	@RequestMapping(value="/AdvertAjaxUpMove.do",method=RequestMethod.POST)
	@ResponseBody
	public String AdvertAjaxUpMove()
	{
		String beforeId=request.getParameter("beforeId");
		String id=request.getParameter("id");
		String beforePriority=request.getParameter("beforePriority");
		String idPriority=request.getParameter("idPriority");
		
		String i=advertMng.updateAdvertPrority(Integer.valueOf(beforeId),idPriority);
		
		String j=advertMng.updateAdvertPrority(Integer.valueOf(id),beforePriority);
		if(i.equals("success")&&j.equals("success"))
		{
			return "success";
		}
		return "false";
		
	}
	
	//createAdvert
	@RequestMapping(value="/createAdvert.do",method=RequestMethod.GET)
	public String createAdvert()
	{
		
		return "advert/createAdvert";
		
	}
	
	//CreateAdvertHtml.do
	@RequestMapping(value="CreateAdvertHtml.do",method=RequestMethod.POST)
	@ResponseBody
	public String createAdvertHtml()
	{
		 String templatePath=request.getRealPath("/")+"static/newTemplate";
		 String usage=request.getParameter("usage");
		 List<FTP> ftplist= advertMng.getFTPinfo();
		int isOk= advertMng.createAdvertHtml(usage,templatePath,ftplist);
		String message="";
		if(isOk==1)
		{
			message="1";
		}
		else
		{
			 message="0";
		}
		return message;
	}
	

}
