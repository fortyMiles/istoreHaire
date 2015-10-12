package com.istore.common.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.google.gson.Gson;
import com.istore.common.core.mng.CatalogMng;
import com.istore.common.web.util.BaseController;

/**
 * @author jiangtao
 * 
 */
@Controller
@RequestMapping("/catalog")
public class CatalogController extends BaseController {

	@Autowired
	private CatalogMng catalogMng;

	@Autowired
	HttpServletRequest request;

	/**
	 * 返回一级目录列表页面.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public String catalogList() {
		return "catalog/list";
	}

	/**
	 * 获得一级目录列表详情.
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/json.do", method = RequestMethod.POST)
	public String getCatalogList(Model model) {
		model.addAttribute("catList",
				super.json(catalogMng.getCatgroupList(request)));
		return "catalog/list-A";
	}

	/**
	 * 返回二级目录页面.
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/viewCatalog.do", method = RequestMethod.GET)
	public String detailList(Model model) {
		String storeId = request.getParameter("storeId");
		String catgroupId = request.getParameter("catgroupId");
		model.addAttribute("storeId", storeId);
		model.addAttribute("catgroupId", catgroupId);
		return "catalog/slevelList";
	}

	/**
	 * 返回三级目录列表页面.
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/viewCatalogB.do", method = RequestMethod.GET)
	public String detailListB(Model model) {
		String storeId = request.getParameter("storeId");
		String catgroupId = request.getParameter("catgroupId");
		model.addAttribute("storeId", storeId);
		model.addAttribute("catgroupId", catgroupId);
		return "catalog/tlevelList";
	}

	/**
	 * 获得下级目录列表详情.
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/jsond.do", method = RequestMethod.POST)
	public String viewCatGroup(Model model) {
		model.addAttribute("catList",
				super.json(catalogMng.getCatDetailList(request)));
		return "catalog/list-A";
	}

	/**
	 * 修改目录名称.
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/modifyCatalog.do", method = RequestMethod.POST)
	@ResponseBody
	public String modCatGroup() {
		Gson gson = new Gson();
		try {
			catalogMng.updateCatgroupName(request);
		} catch (Exception e) {
			e.printStackTrace();
			return gson.toJson("false");
		}
		return gson.toJson("true");
	}

	/**
	 * 添加目录.
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addCatalog.do", method = RequestMethod.POST)
	@ResponseBody
	public String addCatGroup() {
		Gson gson = new Gson();
		try {
			catalogMng.addCatalog(request);
		} catch (Exception e) {
			e.printStackTrace();
			return gson.toJson("false");
		}
		return gson.toJson("true");
	}

	/**
	 * 删除目录（如果该目录及子目录下存在关联商品则无法删除）.
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deleteCatalog.do", method = RequestMethod.GET)
	@ResponseBody
	public String delCatGroup(HttpServletRequest request) {
		Gson gson = new Gson();
		try {
			String status = catalogMng.deleteCatalog(request);
			if (status.equals("exist")) {
				return gson.toJson("exist");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return gson.toJson("false");
		}
		return gson.toJson("true");
	}

	/**
	 * 返回目录商品关系页面.
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/viewCatentryPage.do", method = RequestMethod.GET)
	public String catentryPage(HttpServletRequest request, Model model) {
		String catgroupId = request.getParameter("catgroupId");
		model.addAttribute("catgroup_id", catgroupId);
		return "catalog/catentryList";
	}

	/**
	 * 获得已挂目录的商品详情.
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/viewCatentry.do", method = RequestMethod.POST)
	public String viewCatentry(Model model) {
		model.addAttribute("catList",
				super.json(catalogMng.getCatentryList(request)));
		return "catalog/list-A";
	}

	/**
	 * 获得可挂商品详情.
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "viewAddCatentry.do", method = RequestMethod.POST)
	public String viewAddCatentry(Model model) {
		model.addAttribute("catList",
				super.json(catalogMng.getAddCatentryList(request)));
		return "catalog/list-A";
	}

	/**
	 * 将商品挂入目录.
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "addSaleCatentry.do", method = RequestMethod.POST)
	@ResponseBody
	public String addSaleCatentry() {
		Gson gson = new Gson();
		try {
			catalogMng.addSaleCatentry(request);
		} catch (Exception e) {
			e.printStackTrace();
			return gson.toJson("false");
		}
		return gson.toJson("true");
	}

	/**
	 * 删除目录商品关联.
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteCatentry.do", method = RequestMethod.GET)
	@ResponseBody
	public String deleteCatentry() {
		Gson gson = new Gson();
		try {
			catalogMng.deleteCatentry(request);
		} catch (Exception e) {
			e.printStackTrace();
			gson.toJson("false");
		}
		return gson.toJson("true");
	}

	/**
	 * 一级二级目录图片上传.
	 * 
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "upload.do", method = RequestMethod.POST)
	@ResponseBody
	public String imageUpload(@RequestParam("upload") MultipartFile file) {
		Gson gson = new Gson();
		try {
			catalogMng.uploadImage(file, request);
		} catch (Exception e) {
			e.printStackTrace();
			gson.toJson("false");
		}
		return gson.toJson("true");
	}
}
