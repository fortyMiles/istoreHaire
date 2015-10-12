package com.istore.common.core.mng.impl;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sun.net.TelnetOutputStream;
import sun.net.ftp.FtpClient;
import com.istore.common.core.bean.Catalog;
import com.istore.common.core.bean.FTP;
import com.istore.common.core.dao.CatalogDao;
import com.istore.common.core.mng.CatalogMng;
import com.istore.common.core.mng.FtpMng;
import com.istore.common.web.util.JsonResult;

@Service
public class CatalogMngImpl implements CatalogMng {

	@Autowired
	private CatalogDao catalogDao;

	@Autowired
	private FtpMng ftpMng;

	/**
	 * 
	 * @param count 总条数
	 * @param request
	 * @return
	 */
	public Map<String, Integer> pageInfo(int count, HttpServletRequest request) {
		// 获得当前页
		int page = Integer.parseInt(request.getParameter("page"));
		// 获得每页显示条数
		int pageSize = Integer.parseInt(request.getParameter("rows"));
		int total = count % pageSize == 0 ? count / pageSize : count / pageSize
				+ 1;
		int startIndex = (page - 1) * pageSize;
		int endIndex = (page) * pageSize;
		Map<String, Integer> map = new HashMap<String, Integer>();
		// 总页数
		map.put("total", total);
		// 开始行号
		map.put("startIndex", startIndex);
		// 结束行号
		map.put("endIndex", endIndex);
		// 当前页
		map.put("page", page);
		return map;
	}

	/**
	 * 获得一级目录列表详情.
	 * 
	 * @param request
	 * @return JsonResult
	 */
	public JsonResult getCatgroupList(HttpServletRequest request) {
		long storeId = Long.parseLong(String.valueOf(request.getSession()
				.getAttribute("storeId")));
		// 获取列表数量
		int count = catalogDao.getCatgroupListSize(storeId);
		
		// 获取分页信息
		Map<String, Integer> pageInfo = pageInfo(count, request);
		
		List<Catalog> list = catalogDao.getCatgroupList(storeId, 1,
				pageInfo.get("startIndex"), pageInfo.get("endIndex"));
		Object[] cells = new Object[list.size()];
		for (int i = 0; i < list.size(); i++) {
			cells[i] = list.get(i);
		}
		return new JsonResult(pageInfo.get("page"), pageInfo.get("total"),
				cells);
	}

	/**
	 * 获得一级目录列表详情.
	 * @param request
	 * @return List
	 */
	@SuppressWarnings("rawtypes")
	public List getCatgroupLists(HttpServletRequest request) {
		long storeId = Long.parseLong(String.valueOf(request.getSession()
				.getAttribute("storeId")));
		@SuppressWarnings("unused")
		int count = catalogDao.getCatgroupListSize(storeId);
		List<Catalog> list = catalogDao.getCatgroupList(storeId, 1, 0, 999);
		return list;
	}

	/**
	 * 获得下级目录列表详情.
	 * @param request
	 * @return
	 */
	public JsonResult getCatDetailList(HttpServletRequest request) {
		String storeId = (String) request.getSession().getAttribute("storeId");

		String catgroupId = request.getParameter("catgroupId");
		int count = catalogDao.getCatGroupDetailSize(storeId, catgroupId);

		Map<String, Integer> pageInfo = pageInfo(count, request);
		List<Catalog> list = catalogDao.getCatgroupDetail(storeId, catgroupId,
				pageInfo.get("startIndex"), pageInfo.get("endIndex"));
		for (int i = 0; i < list.size(); i++) {
			String filePath = list.get(i).getField1();
			filePath = "<img width='300' height='150'  src='http://10.176.0.40/"
					+ filePath + "?v=" + Math.random() + "' />";
			list.get(i).setField1(filePath);
		}
		return new JsonResult(pageInfo.get("page"), pageInfo.get("total"), list);
	}

	/**
	 * 添加目录.
	 * @param request
	 * @throws Exception
	 */
	@Transactional(rollbackFor = SQLException.class)
	public void addCatalog(HttpServletRequest request) throws Exception {
		String catalogName = request.getParameter("catalogName");
		long storeId = Long.parseLong(String.valueOf(request.getSession()
				.getAttribute("storeId")));
		String level = request.getParameter("level");
		String sequence = "5";
		long cId = 0;
		String catgroupId = request.getParameter("catgroupId");
		if (catgroupId == null || catgroupId.equals("")) {

		} else {
			cId = Long.parseLong(catgroupId);
		}
		catalogDao.addCatgroup(storeId, catalogName, cId,
				Integer.parseInt(level), Double.parseDouble(sequence));
	}

	/**
	 * 更新目录名称.
	 * @param request
	 * @throws Exception
	 */
	@Transactional(rollbackFor = SQLException.class)
	public void updateCatgroupName(HttpServletRequest request) throws Exception {
		String catgroupName = request.getParameter("catalogName");
		int catgroupId = Integer.parseInt(request.getParameter("catgroupId"));
		catalogDao.updateCatgroupName(catgroupName, catgroupId);
	}

	/**
	 * 删除目录.
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Transactional(rollbackFor = SQLException.class)
	public String deleteCatalog(HttpServletRequest request) throws Exception {
		Long catgroupId = Long.parseLong(request.getParameter("catgroupId"));
		Integer level = Integer.parseInt(request.getParameter("level"));

		int count = catalogDao.isGoodsExist(catgroupId, level);
		if (count != 0) {
			return "exist";
		} else {
			catalogDao.deleteCatgroup(catgroupId, level);
			return "";
		}
	}

	/**
	 * 获得已挂目录的商品详情.
	 * @param request
	 * @return
	 */
	public JsonResult getCatentryList(HttpServletRequest request) {
		long catgroup_id = Long.parseLong(request.getParameter("catgroupId"));
		long storeId = Long.parseLong(String.valueOf(request.getSession()
				.getAttribute("storeId")));

		long markforDelete = 0;
		int count = catalogDao.getCatentryListSize(catgroup_id, storeId,
				markforDelete);

		Map<String, Integer> pageInfo = pageInfo(count, request);

		List<Catalog> list = catalogDao.getCatentryList(catgroup_id, storeId,
				markforDelete, pageInfo.get("startIndex"),
				pageInfo.get("endIndex"));
		return new JsonResult(pageInfo.get("page"), pageInfo.get("total"), list);

	}

	/**
	 * 获得可挂商品详情.
	 * @param request
	 * @return
	 */
	public JsonResult getAddCatentryList(HttpServletRequest request) {
		String partnumber = request.getParameter("partnumber");
		String catentryName = request.getParameter("catentryName");
		String storeId = (String) request.getSession().getAttribute("storeId");
		if (partnumber == null || partnumber.trim().equals("")) {
			partnumber = "%%";
		} else {
			partnumber = "%" + partnumber + "%";
		}
		if (catentryName == null || catentryName.trim().equals("")) {
			catentryName = "%%";
		} else {
			catentryName = "%" + catentryName + "%";
		}
		int count = catalogDao.getAddCatentryListSize(partnumber, catentryName,
				storeId);
		Map<String, Integer> pageInfo = pageInfo(count, request);
		List<Catalog> list = catalogDao.getAddCatentryList(partnumber,
				catentryName, pageInfo.get("startIndex"),
				pageInfo.get("endIndex"), storeId);

		return new JsonResult(pageInfo.get("page"), pageInfo.get("total"), list);

	}

	/**
	 * 将商品挂入目录.
	 * @param request
	 * @throws Exception
	 */
	@Transactional(rollbackFor = SQLException.class)
	public void addSaleCatentry(HttpServletRequest request) throws Exception {
		String[] ids = new String[] {};
		ids = request.getParameter("selectedIds").split(",");
		long storeId = Long.parseLong(String.valueOf(request.getSession()
				.getAttribute("storeId")));
		long catgroupid = Long.parseLong(request.getParameter("catgroupid"));
		List<Catalog> existList = catalogDao.getexistCatentry(storeId,
				catgroupid);
		boolean flag = true;
		for (int i = 0; i < ids.length; i++) {
			flag = true;
			for (int j = 0; j < existList.size(); j++) {
				if (ids[i].equals(existList.get(j).getCatentryId() + "")) {
					flag = false;

				}
			}
			if (flag) {
				catalogDao.insertSaleCatentry(Long.parseLong(ids[i]), storeId,
						catgroupid);
			}

		}
	}

	/**
	 * 删除目录商品关联.
	 * @param request
	 * @throws Exception
	 */
	@Transactional(rollbackFor = SQLException.class)
	public void deleteCatentry(HttpServletRequest request) throws Exception {
		String catgroup_id = request.getParameter("catgroup_id");
		String catentry_id = request.getParameter("catentry_id");
		long storeId = Long.parseLong(String.valueOf(request.getSession()
				.getAttribute("storeId")));
		catalogDao.deleteCatentry(Long.parseLong(catgroup_id),
				Long.parseLong(catentry_id), storeId);
	}

	/**
	 * 一二级目录图片上传
	 * @param file
	 * @param request
	 * @throws Exception
	 */
	@Transactional(rollbackFor = SQLException.class)
	public void uploadImage(MultipartFile file, HttpServletRequest request)
			throws Exception {
		String catgroup_id = request.getParameter("catgroupId");

		if (!file.isEmpty()) {
			InputStream in = file.getInputStream();
			String filename = file.getOriginalFilename();
			filename = filename.endsWith(".jpg") ? catgroup_id + "-"
					+ new Timestamp(new Date().getTime()) + ".jpg"
					: catgroup_id + "-" + new Timestamp(new Date().getTime())
							+ ".png";

			String type = "5";
			List<FTP> ftpList = ftpMng.getFtpListByType(type);
			if (ftpList.size() > 0) {
				String host = ftpList.get(0).getHost();
				String port = ftpList.get(0).getPort();
				String username = ftpList.get(0).getUsername();
				String password = ftpList.get(0).getPassword();
				String xpath = ftpList.get(0).getXpath();

				FtpClient ftpClient = new FtpClient(host, Integer.valueOf(port));
				ftpClient.login(username, password); // 登录FTP服务器
				ftpClient.cd(xpath); // 图片目标目录
				ftpClient.binary();
				TelnetOutputStream telOut = ftpClient.put(filename);
				DataOutputStream out = new DataOutputStream(telOut);
				byte[] b = new byte[1024];
				int temp;
				while ((temp = in.read(b)) != -1) {
					out.write(b, 0, temp);
				}
				catalogDao.updateImageUrl(catgroup_id, filename);

			}

		}

	}
}
