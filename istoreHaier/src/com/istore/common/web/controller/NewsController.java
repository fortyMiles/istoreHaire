/**
 * @Project istoreHaier
 * @Package com.istore.common.web.controller
 * @Title NewsController.java
 * @Description TODO
 * @CopyRight CopyRight (c) 2014
 * @Company 江苏太湖云计算信息技术股份有限公司
 *
 * @author mojilin
 * @date 2014-7-20
 * @email mojilin@wuxicloud.com
 * @version V1.0
 */
package com.istore.common.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.istore.common.core.bean.XNews;
import com.istore.common.core.mng.FtpMng;
import com.istore.common.core.mng.NewsMng;
import com.istore.common.web.util.BaseController;

/**
 * @ClassName: NewsController.java
 * @Description: TODO
 * @author mojilin
 * @time 2014-7-20下午2:01:31
 */
@Controller
@RequestMapping("/news")
public class NewsController extends BaseController {

	@Autowired
	HttpServletRequest request;

	@Autowired
	NewsMng newsMng;

	@Autowired
	FtpMng ftpMng;

	/**
	 * 新闻展示列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public String list() {
		return "news/list";
	}

	/**
	 * 新闻JSON数据
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/json.do", method = RequestMethod.POST)
	public String newsList() {
		String getXchannel_id = request.getParameter("xchannel_id");
		long xchannel_id = Long.valueOf(getXchannel_id);
		int page = Integer.valueOf(request.getParameter("page").toString());
		int rows = Integer.valueOf(request.getParameter("rows").toString());
		int startIndex = (page - 1) * rows;
		int endIndex = startIndex + rows;

		int listSize = newsMng.getNewsListSize(xchannel_id);
		List xNewsList = newsMng.getNewsList(xchannel_id, startIndex, endIndex);

		int total = 0;
		if (listSize % rows == 0) {
			total = listSize / rows;
		} else {
			total = listSize / rows + 1;
		}

		request.setAttribute("total", total);
		request.setAttribute("page", page);
		request.setAttribute("records", xNewsList.size());
		request.setAttribute("xNewsList", xNewsList);
		return "news/json";
	}

	/**
	 * 新闻添加
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add.do", method = RequestMethod.POST)
	@ResponseBody
	public String add() {
		String title = request.getParameter("title");
		String add_seq = request.getParameter("seq");
		String details = request.getParameter("details");
		String group = request.getParameter("group");
		String add_xchannel_id = request.getParameter("xchannel_id");
		return newsMng.addNewsByTitle(title, add_seq, details, group,
				add_xchannel_id);
	}

	/**
	 * 当前编辑新闻
	 * 
	 * @return
	 */
	@RequestMapping(value = "/editlist.do", method = RequestMethod.POST)
	public String editlist() {
		String edit_news_id = request.getParameter("xnews_id");
		List<XNews> xNewsList = newsMng.getEditNewsListByID(edit_news_id);
		request.setAttribute("xNewsList", xNewsList);
		return "news/edit";
	}

	/**
	 * 新闻修改保存
	 * 
	 * @return
	 */
	@RequestMapping(value = "/edit.do", method = RequestMethod.POST)
	@ResponseBody
	public String edit() {
		String edit_news_id = request.getParameter("xnews_id");
		String title = request.getParameter("title");
		String edit_seq = request.getParameter("seq");
		String details = request.getParameter("details");
		String group = request.getParameter("group");
		return newsMng.editNewsByID(edit_news_id, title, edit_seq, details,
				group);
	}

	/**
	 * 新闻逻辑删除
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delete.do", method = RequestMethod.POST)
	@ResponseBody
	public String delete() {
		String edit_news_id = request.getParameter("xnews_id");
		return newsMng.deleteNewsByID(edit_news_id);
	}

	/**
	 * 新闻提交审核
	 * 
	 * @return
	 */
	@RequestMapping(value = "/refer.do", method = RequestMethod.POST)
	@ResponseBody
	public String refer() {
		String edit_news_id = request.getParameter("xnews_id");
		return newsMng.referNewsByID(edit_news_id);
	}

	/**
	 * 新闻搜索
	 * 
	 * @return
	 */
	@RequestMapping(value = "/search.do", method = RequestMethod.POST)
	public String search() {
		String search_xchannel_id = request.getParameter("xchannel_id");
		String search_title = request.getParameter("title");
		String search_details = request.getParameter("details");

		int page = Integer.valueOf(request.getParameter("page").toString());
		int rows = Integer.valueOf(request.getParameter("rows").toString());
		int startIndex = (page - 1) * rows;
		int endIndex = startIndex + rows;

		int listSize = newsMng.getSearchNewsListCount(search_xchannel_id,
				search_title, search_details);

		List<XNews> xNewsList = newsMng.getSearchNewsList(search_xchannel_id,
				search_title, search_details, startIndex, endIndex);

		int total = 0;
		if (listSize % rows == 0) {
			total = listSize / rows;
		} else {
			total = listSize / rows + 1;
		}

		request.setAttribute("total", total);
		request.setAttribute("page", page);
		request.setAttribute("records", xNewsList.size());
		request.setAttribute("xNewsList", xNewsList);
		return "news/json";
	}

	/**
	 * 查看当前新闻
	 * 
	 * @return
	 */
	@RequestMapping(value = "/view.do", method = RequestMethod.POST)
	public String view() {
		String edit_news_id = request.getParameter("xnews_id");
		List<XNews> xNewsList = newsMng.viewNewsListByID(edit_news_id);
		request.setAttribute("xNewsList", xNewsList);
		return "news/view";
	}

	/**
	 * 新闻审核通过
	 * 
	 * @return
	 */
	@RequestMapping(value = "/approve.do", method = RequestMethod.POST)
	@ResponseBody
	public String approve() {
		String edit_news_id = request.getParameter("xnews_id");
		return newsMng.approveNewsListByID(edit_news_id);
	}

	/**
	 * 新闻审核拒绝
	 * 
	 * @return
	 */
	@RequestMapping(value = "/reject.do", method = RequestMethod.POST)
	@ResponseBody
	public String reject() {
		String edit_news_id = request.getParameter("xnews_id");
		String xcomment = request.getParameter("xcomment");
		return newsMng.rejectNewsListByID(edit_news_id, xcomment);
	}

	/**
	 * 新闻审核拒绝原因
	 * 
	 * @return
	 */
	@RequestMapping(value = "/rejectcase.do", method = RequestMethod.POST)
	@ResponseBody
	public String rejectcase() {
		String edit_news_id = request.getParameter("xnews_id");
		return newsMng.getRejectcaseByNewsID(edit_news_id);
	}

	/**
	 * 新闻上传图片
	 * 
	 * @return
	 */
	@RequestMapping(value = "/upload.do", method = RequestMethod.POST)
	public String uploadimg(@RequestParam("imgFile") MultipartFile file) {
		String type = "3";
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
		return "news/upload";
	}
}
