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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.istore.common.core.bean.XChannel;
import com.istore.common.core.mng.ChannelMng;
import com.istore.common.web.util.BaseController;

/**
 * @ClassName: ChannelController.java
 * @Description: TODO
 * @author mojilin
 * @time 2014-7-9上午10:08:40
 */
@Controller
@RequestMapping("/channel")
public class ChannelController extends BaseController {

	@Autowired
	HttpServletRequest request;

	@Autowired
	ChannelMng channelMng;

	/**
	 * 频道展示列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public String list() {
		return "channel/list";
	}

	/**
	 * 频道JSON数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/json.do", method = RequestMethod.POST)
	public String channelList() {
		int page = Integer.valueOf(request.getParameter("page").toString());
		int rows = Integer.valueOf(request.getParameter("rows").toString());
		int startIndex = (page - 1) * rows;
		int endIndex = startIndex + rows;

		int listSize = channelMng.getChannelListSize();

		List<XChannel> xChannelList = channelMng.getChannelList(startIndex,
				endIndex);

		int total = 0;
		if (listSize % rows == 0) {
			total = listSize / rows;
		} else {
			total = listSize / rows + 1;
		}

		request.setAttribute("total", total);
		request.setAttribute("page", page);
		request.setAttribute("records", xChannelList.size());
		request.setAttribute("xChannelList", xChannelList);
		return "channel/json";
	}

	/**
	 * 频道添加
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add.do", method = RequestMethod.POST)
	@ResponseBody
	public String add() {
		String xchannel_name = request.getParameter("xchannel_name");
		String add_seq = request.getParameter("seq");
		String type = request.getParameter("type");
		return channelMng.addChannelByName(xchannel_name, add_seq, type);
	}

	/**
	 * 当前编辑频道
	 * 
	 * @return
	 */
	@RequestMapping(value = "/editlist.do", method = RequestMethod.POST)
	public String editlist() {
		String edit_xchannel_id = request.getParameter("xchannel_id");
		List<XChannel> xChannelList = channelMng
				.getEditChannelListByID(edit_xchannel_id);
		request.setAttribute("xChannelList", xChannelList);
		return "channel/edit";
	}

	/**
	 * 频道修改保存
	 * 
	 * @return
	 */
	@RequestMapping(value = "/edit.do", method = RequestMethod.POST)
	@ResponseBody
	public String edit() {
		String edit_xchannel_id = request.getParameter("xchannel_id");
		String edit_xchannel_name = request.getParameter("xchannel_name");
		String edit_seq = request.getParameter("seq");
		String edit_type = request.getParameter("type");
		return channelMng.editChannelByID(edit_xchannel_id, edit_xchannel_name,
				edit_seq, edit_type);
	}

	/**
	 * 频道逻辑删除
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delete.do", method = RequestMethod.POST)
	@ResponseBody
	public String delete() {
		String edit_xchannel_id = request.getParameter("xchannel_id");
		return channelMng.deleteChannelByID(edit_xchannel_id);
	}

	/**
	 * 频道搜索
	 * 
	 * @return
	 */
	@RequestMapping(value = "/search.do", method = RequestMethod.POST)
	public String search() {
		String search_xchannel_name = request.getParameter("xchannel_name");
		String search_type = request.getParameter("type");

		int page = Integer.valueOf(request.getParameter("page").toString());
		int rows = Integer.valueOf(request.getParameter("rows").toString());
		int startIndex = (page - 1) * rows;
		int endIndex = startIndex + rows;

		int listSize = channelMng.getSearchChannelListCount(
				search_xchannel_name, search_type);

		List<XChannel> xChannelList = channelMng.getSearchChannelList(
				search_xchannel_name, search_type, startIndex, endIndex);

		int total = 0;
		if (listSize % rows == 0) {
			total = listSize / rows;
		} else {
			total = listSize / rows + 1;
		}

		request.setAttribute("total", total);
		request.setAttribute("page", page);
		request.setAttribute("records", xChannelList.size());
		request.setAttribute("xChannelList", xChannelList);
		return "channel/json";
	}

}
