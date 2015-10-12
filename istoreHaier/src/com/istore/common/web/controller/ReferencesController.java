/**
 * @Project istoreHaier
 * @Package com.istore.common.web.controller
 * @Title ChannelController.java
 * @Description TODO
 * @CopyRight CopyRight (c) 2014
 * @Company 江苏太湖云计算信息技术股份有限公司
 *
 * @author mojilin
 * @date 2014-7-9
 * @email mojilin@wuxicloud.com
 * @version V1.0
 */
package com.istore.common.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.istore.common.core.bean.Catgroup;
import com.istore.common.core.bean.XReferences;
import com.istore.common.core.mng.FtpMng;
import com.istore.common.core.mng.ReferencesMng;
import com.istore.common.web.util.BaseController;

/**
 * @ClassName: ReferencesController.java
 * @Description: TODO
 * @author mojilin
 * @time 2014-7-9上午10:08:40
 */
@Controller
@RequestMapping("/references")
public class ReferencesController extends BaseController {

	@Autowired
	HttpServletRequest request;

	@Autowired
	HttpSession session;

	@Autowired
	ReferencesMng referencesMng;

	@Autowired
	FtpMng ftpMng;

	/**
	 * 样板工程展示列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public String list() {
		long store_id = Long
				.valueOf(session.getAttribute("storeId").toString());
		List<Catgroup> catgroupList = referencesMng
				.getCatgroupByStoreID(store_id);
		request.setAttribute("catgroupList", catgroupList);
		return "references/list";
	}

	/**
	 * 样板工程JSON数据
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/json.do", method = RequestMethod.POST)
	public String referencesList() {
		String getXchannel_id = request.getParameter("xchannel_id");
		long xchannel_id = Long.valueOf(getXchannel_id);
		int page = Integer.valueOf(request.getParameter("page").toString());
		int rows = Integer.valueOf(request.getParameter("rows").toString());
		int startIndex = (page - 1) * rows;
		int endIndex = startIndex + rows;

		int listSize = referencesMng.getReferencesListSize(xchannel_id);

		List xReferencesList = referencesMng.getReferencesList(xchannel_id,
				startIndex, endIndex);

		int total = 0;
		if (listSize % rows == 0) {
			total = listSize / rows;
		} else {
			total = listSize / rows + 1;
		}

		request.setAttribute("total", total);
		request.setAttribute("page", page);
		request.setAttribute("records", xReferencesList.size());
		request.setAttribute("xReferencesList", xReferencesList);
		return "references/json";
	}

	/**
	 * 样板工程添加
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add.do", method = RequestMethod.POST)
	@ResponseBody
	public String add() {
		String serialnumber = request.getParameter("serialnumber");
		String country = request.getParameter("country");
		String projectplace = request.getParameter("projectplace");
		String description = request.getParameter("description");
		String type = request.getParameter("type");
		String installtime = request.getParameter("installtime");
		String installseries = request.getParameter("installseries");
		String installdetails = request.getParameter("installdetails");
		String totalcapacity = request.getParameter("totalcapacity");
		String keycapacity = request.getParameter("keycapacity");
		String add_seq = request.getParameter("seq");
		String group = request.getParameter("group");
		String catgroup = request.getParameter("catgroup");
		String pictures = request.getParameter("pictures");
		String add_xchannel_id = request.getParameter("xchannel_id");
		return referencesMng.addReferencesBySerialNumber(serialnumber, country,
				projectplace, description, type, installtime, installseries,
				installdetails, totalcapacity, keycapacity, add_seq, group,
				catgroup, pictures, add_xchannel_id);
	}

	/**
	 * 当前编辑样板工程
	 * 
	 * @return
	 */
	@RequestMapping(value = "/editlist.do", method = RequestMethod.POST)
	public String editlist() {
		String edit_xreferences_id = request.getParameter("xreferences_id");
		long store_id = Long
				.valueOf(session.getAttribute("storeId").toString());
		List<XReferences> xReferencesList = referencesMng
				.getEditReferencesListByID(edit_xreferences_id);
		List<Catgroup> catgroupList = referencesMng
				.getCatgroupByStoreID(store_id);
		request.setAttribute("catgroupList", catgroupList);
		request.setAttribute("xReferencesList", xReferencesList);
		return "references/edit";
	}

	/**
	 * 样板工程修改保存
	 * 
	 * @return
	 */
	@RequestMapping(value = "/edit.do", method = RequestMethod.POST)
	@ResponseBody
	public String edit() {
		String edit_xreferences_id = request.getParameter("xreferences_id");
		String serialnumber = request.getParameter("serialnumber");
		String country = request.getParameter("country");
		String projectplace = request.getParameter("projectplace");
		String description = request.getParameter("description");
		String type = request.getParameter("type");
		String installtime = request.getParameter("installtime");
		String installseries = request.getParameter("installseries");
		String installdetails = request.getParameter("installdetails");
		String totalcapacity = request.getParameter("totalcapacity");
		String keycapacity = request.getParameter("keycapacity");
		String edit_seq = request.getParameter("seq");
		String group = request.getParameter("group");
		String catgroup = request.getParameter("catgroup");
		String pictures = request.getParameter("pictures");
		return referencesMng.editReferencessByID(edit_xreferences_id,
				serialnumber, country, projectplace, description, type,
				installtime, installseries, installdetails, totalcapacity,
				keycapacity, edit_seq, group, catgroup, pictures);
	}

	/**
	 * 样板工程逻辑删除
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delete.do", method = RequestMethod.POST)
	@ResponseBody
	public String delete() {
		String edit_xreferences_id = request.getParameter("xreferences_id");
		return referencesMng.deleteReferencesByID(edit_xreferences_id);
	}

	/**
	 * 样板工程提交审核
	 * 
	 * @return
	 */
	@RequestMapping(value = "/refer.do", method = RequestMethod.POST)
	@ResponseBody
	public String refer() {
		String edit_xreferences_id = request.getParameter("xreferences_id");
		return referencesMng.referReferencesByID(edit_xreferences_id);
	}

	/**
	 * 样板工程搜索
	 * 
	 * @return
	 */
	@RequestMapping(value = "/search.do", method = RequestMethod.POST)
	public String search() {
		String search_xchannel_id = request.getParameter("xchannel_id");
		String search_serialnumber = request.getParameter("serialnumber");
		String search_country = request.getParameter("country");
		String search_projectplace = request.getParameter("projectplace");
		String search_description = request.getParameter("description");
		String search_type = request.getParameter("type");
		String search_installtime = request.getParameter("installtime");
		String search_installseries = request.getParameter("installseries");
		String search_installdetails = request.getParameter("installdetails");
		String search_totalcapacity = request.getParameter("totalcapacity");
		String search_keycapacity = request.getParameter("keycapacity");

		int page = Integer.valueOf(request.getParameter("page").toString());
		int rows = Integer.valueOf(request.getParameter("rows").toString());
		int startIndex = (page - 1) * rows;
		int endIndex = startIndex + rows;
		int listSize = referencesMng
				.getSearchReferencesListCount(search_xchannel_id,
						search_serialnumber, search_country,
						search_projectplace, search_description, search_type,
						search_installtime, search_installseries,
						search_installdetails, search_totalcapacity,
						search_keycapacity);

		List<XReferences> xReferencesList = referencesMng
				.getSearchReferencesList(search_xchannel_id,
						search_serialnumber, search_country,
						search_projectplace, search_description, search_type,
						search_installtime, search_installseries,
						search_installdetails, search_totalcapacity,
						search_keycapacity, startIndex, endIndex);

		int total = 0;
		if (listSize % rows == 0) {
			total = listSize / rows;
		} else {
			total = listSize / rows + 1;
		}

		request.setAttribute("total", total);
		request.setAttribute("page", page);
		request.setAttribute("records", xReferencesList.size());
		request.setAttribute("xReferencesList", xReferencesList);
		return "references/json";
	}

	/**
	 * 查看当前样板工程
	 * 
	 * @return
	 */
	@RequestMapping(value = "/view.do", method = RequestMethod.POST)
	public String view() {
		String edit_xreferences_id = request.getParameter("xreferences_id");
		List<XReferences> xReferencesList = referencesMng
				.getViewReferencesListByID(edit_xreferences_id);
		request.setAttribute("xReferencesList", xReferencesList);
		return "references/view";
	}

	/**
	 * 样板工程审核通过
	 * 
	 * @return
	 */
	@RequestMapping(value = "/approve.do", method = RequestMethod.POST)
	@ResponseBody
	public String approve() {
		String edit_xreferences_id = request.getParameter("xreferences_id");
		String xcomment = request.getParameter("xcomment");
		return referencesMng.approveReferencesByID(edit_xreferences_id,
				xcomment);
	}

	/**
	 * 样板工程审核拒绝
	 * 
	 * @return
	 */
	@RequestMapping(value = "/reject.do", method = RequestMethod.POST)
	@ResponseBody
	public String reject() {
		String edit_xreferences_id = request.getParameter("xreferences_id");
		String xcomment = request.getParameter("xcomment");
		return referencesMng
				.rejectReferencesByID(edit_xreferences_id, xcomment);
	}

	/**
	 * 样板工程审核拒绝原因
	 * 
	 * @return
	 */
	@RequestMapping(value = "/rejectcase.do", method = RequestMethod.POST)
	@ResponseBody
	public String rejectcase() {
		String edit_xreferences_id = request.getParameter("xreferences_id");
		return referencesMng.getRejectcaseByReferencesID(edit_xreferences_id);
	}

	/**
	 * 样板工程上传图片
	 * 
	 * @return
	 */
	@RequestMapping(value = "/upload.do", method = RequestMethod.POST)
	public String uploadimg(@RequestParam("imgFile") MultipartFile file) {
		String type = "4";
		String finalfilename = ftpMng.uploadImageFile(file, type);
		if (!finalfilename.isEmpty()) {
			request.setAttribute("error", 0);
			request.setAttribute("message", "上传成功");
			request.setAttribute("url", finalfilename);
		} else {
			request.setAttribute("error", 1);
			request.setAttribute("message", "上传失败");
			request.setAttribute("url", finalfilename);
		}
		return "references/upload";
	}
}
