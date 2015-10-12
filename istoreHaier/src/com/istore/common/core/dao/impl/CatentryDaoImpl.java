package com.istore.common.core.dao.impl;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.istore.common.core.bean.Catentry;
import com.istore.common.core.dao.CatentryDao;
import com.istore.common.core.mapper.FindCatentryMapper;
import com.istore.common.core.util.DeltaUtils;
import com.istore.common.core.bean.CatentryImage;
import com.istore.common.core.bean.FtpUser;
import com.istore.common.core.bean.Picture;

@Repository
public class CatentryDaoImpl implements CatentryDao {
	@Autowired
	HttpSession session;
	@Autowired
	private FindCatentryMapper findCatMapper;
	
	@Autowired
	private DeltaUtils deltaUtils;
	
	String storeId;
	/**
	 * 获取商品列表
	 */
	public List<Catentry> getCatList(int startIndex, int endIndex) {
		storeId = (String) session.getAttribute("storeId");	
		long storeid=Long.parseLong(storeId);
		return findCatMapper.getCatList(startIndex, endIndex,storeid);
	}
	/**
	 * 获取商品列表长度
	 */
	public int getCatListSize() {
		storeId = (String) session.getAttribute("storeId");	
		long storeid=Long.parseLong(storeId);
		return  findCatMapper.getCatListCount(storeid);
		
	}

	/**
	 * 获取搜索商品列表
	 */
	public List<Catentry> getCatList(int startIndex, int endIndex,String key,String type) {
		storeId = (String) session.getAttribute("storeId");	
		long storeid=Long.parseLong(storeId);
		String Key="%"+key+"%";
		if(type.equals("N")){
		return findCatMapper.getSearchName(startIndex, endIndex,storeid,Key);
		}else{
			return findCatMapper.getSearchPart(startIndex, endIndex,storeid,Key);
		}
	}
	/**
	 * 获取搜索商品列表长度
	 */
	public int getCatListSize(String key,String type) {
		storeId = (String) session.getAttribute("storeId");	
		long storeid=Long.parseLong(storeId);
		String Key="%"+key+"%";
		if(type.equals("N")){
		return  findCatMapper.getSearchNameCount(storeid,Key);
		}else{
			return  findCatMapper.getSearchPartCount(storeid,Key);
		}
		
	}
	
	
	
	
	/**
	 * 查询单个商品
	 */
	public List<Catentry> getEditCatListByID(String catentryId) {
		storeId = (String) session.getAttribute("storeId");	
		long storeid=Long.parseLong(storeId);
		long catentryid = Long.valueOf(catentryId);
		return findCatMapper.getEditCatListByID(catentryid,storeid);
	}

	public String editCatByID(Catentry catentry) {
		String flag = "false";
		try {
		storeId = (String) session.getAttribute("storeId");	
		long storeid=Long.parseLong(storeId);
		long catentryid=catentry.getCatentryId();
		String name=catentry.getName();
		String  shortdesc =catentry.getShortdescription();
		String  description=catentry.getDescription();
		String  partnumber=catentry.getPartnumber();
		int LAN=findCatMapper.selectLan(storeid);
		int i = findCatMapper.editCatByID(catentryid,partnumber);
		int j=findCatMapper.editCatdescByID(catentryid,shortdesc,description,name,LAN);
			if(i==1&&j==1){
				flag="success";
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		 return flag;
	}

	/**
	 * 查询图片
	 */
		public List<Picture> getPicList(int startIndex, int endIndex,String catentid) {
			storeId = (String) session.getAttribute("storeId");	
			long catentId=Integer.parseInt(catentid);
			long storeid=Long.parseLong(storeId);
			return findCatMapper.getPicList(startIndex, endIndex,catentId,storeid);
		}
		public int findsize(String catentid) {
			storeId = (String) session.getAttribute("storeId");	
			long catentId=Integer.parseInt(catentid);
			long storeid=Long.parseLong(storeId);
			return findCatMapper.findsize(catentId,storeid);
		}
	
	public boolean updateLastupdate(String catentryId) {
		
		int i = findCatMapper.updateTime(new Date(), catentryId );
		if (i > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public void updateSolrIndex(String catentryId) {
		if(this.findByCatentryId(catentryId)==0){	
			findCatMapper.updateSolrIndex(Long.parseLong("10001"),Long.parseLong(catentryId), "F" );
		}
	}

	
	
	public long findByCatentryId(String catentryId) {
		
		return findCatMapper.findByCatentryId(catentryId);
	}

	public boolean updateImage(CatentryImage catentryImage) {
//		log.info(sql, args);
		int i = findCatMapper.updateImage(catentryImage.getImageUrl(),
				catentryImage.getCatentryId());
		if (i > 0) {
			return true;
		} else {
			return false;
		}
	}

	public FtpUser getFtpuser(int type) {
	
		return findCatMapper.getFtpuser(type);
	}

	
	
	public long findRows(String catentryId, String field) {
	
		return findCatMapper.findRows(catentryId, field);
	}

	public boolean save(CatentryImage catentryImage) {
		int seq;
		storeId = (String) session.getAttribute("storeId");	
		long storeid=Long.parseLong(storeId);
		if(findCatMapper.find(catentryImage.getCatentryId(),storeid)==0){
			seq=0;
		}
		else
		seq=findCatMapper.maxseq(catentryImage.getCatentryId(),storeid);
		
		int i= findCatMapper.update(catentryImage.getImageUrl(),catentryImage.getCatentryId(),seq+1,storeid);
		if(i>0){
			return true;
		}else{
			return false;
		}
	}
	
	
	public boolean updateField(String catentryId, String field1) {
		
		//log.info(sql, args);
		int i = findCatMapper.updateact(field1, catentryId);
		if (i > 0) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 删图
	 */
	public String deleteImg(String seq,String catentry_id) {
		storeId = (String) session.getAttribute("storeId");
		long storeid=Long.parseLong(storeId);
		long catentryId=Long.parseLong(catentry_id);
		int Seq=Integer.parseInt(seq);
		int i=findCatMapper.deleteImg(Seq,catentryId,storeid);
		if(i>0){
			return "success";
		}
		else{
		return "failure";
		}
	}
	
	/**
	 * 修改图片顺序
	 */
	public String updateimagseq(String newseq, String catentry_id, String seq) {
		storeId = (String) session.getAttribute("storeId");
		long storeid=Long.parseLong(storeId);
		int Newseq=Integer.parseInt(newseq);
		long catentryId=Long.parseLong(catentry_id);
		int Seq=Integer.parseInt(seq);
		int i=findCatMapper.checkseq(Newseq,catentryId,storeid);
		if(i==1){
			return "exit";
		}
		else{
			int j=findCatMapper.updateseq(Newseq,Seq,catentryId,storeid);
			if(j==1){
		     return "success";
			}else{
				return "failure";
			}
		}
	}
	
	
	
	
	
	public int getCatentryListSize(long catentry_id, long storeId,
			long markforDelete) {
		Map<String, Long> param = new HashMap<String, Long>();
		param.put("catentry_id", catentry_id);
		param.put("store_id", storeId);
		param.put("markforDelete", 0l);
		String sql="select count(1) from xcatentrel cp join catentry ct on "+ 
		"cp.catentry_id=ct.catentry_id join catentdesc cd on cp.catentry_id=cd.catentry_id "+ 
		"where cp.catentry_id=#{param.catentry_id} and cp.store_id=#{param.store_id} and "+
		"ct.markfordelete=#{param.markforDelete}";
		return findCatMapper.getCatentryListSize(param,sql);
		
	}

	public List<Catentry> getCatentryList(long catentry_id, long storeId,
			long markforDelete, Integer startIndex, Integer endIndex) {
		Map<String, Long> param = new HashMap<String, Long>();
		param.put("catentry_id", catentry_id);
		param.put("store_id", storeId);
		param.put("markforDelete", 0l);
		param.put("startIndex", (long)startIndex);
		param.put("endIndex", (long)endIndex);
		String sql = "select * from (select x.seq,x.catentry_id_rel,x.name,x.partnumber,rownum rn from (select cp.seq,cp.catentry_id_rel,cd.name,ct.partnumber from xcatentrel cp join catentry ct on "+ 
					"cp.catentry_id=ct.catentry_id join catentdesc cd on cp.catentry_id=cd.catentry_id "+ 
					"where cp.catentry_id=#{param.catentry_id} and cp.store_id=#{param.store_id} and "+
					"ct.markfordelete=#{param.markforDelete} order by seq desc) x) t where t.rn>#{param.startIndex} and t.rn<=#{param.endIndex}";	
		return findCatMapper.getCatentryList(param, sql);
	}

	
	/**
	 * 获得可挂商品数量
	 */
	public int getAddCatentryListSize(String partnumber, String catentryName, String storeId) {
		String sql = "select count(1) from catentry a join catentdesc b on a.catentry_id=b.catentry_id join catgpenrel c " +
				"on a.catentry_id=c.catentry_id join XCATENTSTORE d on a.catentry_id=d.catentry_id where " +
				"a.catenttype_id='ProductBean' and c.catalog_id=10001 and a.markfordelete=0 and d.STORE_ID=#{param.storeId}";
		Map<String, String> param = new HashMap<String, String>();
		param.put("partnumber", partnumber.toUpperCase());
		param.put("catentryName", catentryName.toUpperCase());
		param.put("storeId", storeId);
		if(!partnumber.equals("%%")){
			sql+=" and upper(partnumber) like #{param.partnumber}";
		}
		if(!catentryName.equals("%%")){
			sql+=" and upper(name) like #{param.catentryName}";
		}
		return findCatMapper.getAddCatentryListSize(param, sql);
	}

	/**
	 * 获得可挂商品列表
	 */
	public List<Catentry> getAddCatentryList(String partnumber,
			String catentryName,Integer startIndex, Integer endIndex,String storeId) {
		String sql = "select * from (select a.catentry_id,a.partnumber,b.name,rownum rn from catentry a join catentdesc b on a.catentry_id=b.catentry_id join " +
				"catgpenrel c on a.catentry_id=c.catentry_id join XCATENTSTORE d on a.catentry_id=d.catentry_id where a.catenttype_id='ProductBean' " +
				"and c.catalog_id=10001 and a.markfordelete=0 and d.STORE_ID=#{param.storeId}) t where t.rn>#{param.startIndex} and t.rn<=#{param.endIndex} ";
		Map<String, String> param = new HashMap<String, String>();
		param.put("partnumber", partnumber.toUpperCase());
		param.put("catentryName", catentryName.toUpperCase());
		param.put("startIndex", startIndex+"");
		param.put("endIndex", endIndex+"");
		param.put("storeId", storeId);
		if(!partnumber.equals("%%")){
			sql+=" and upper(partnumber) like #{param.partnumber}";
		}
		if(!catentryName.equals("%%")){
			sql+=" and upper(name) like #{param.catentryName}";
		}
		
		return findCatMapper.getAddCatentryList(param, sql);
	}

	public List<Catentry> getexistCatentry(long storeId, long catentryId) {
		return findCatMapper.getExistCatentryId(storeId,catentryId);
	}

	public void insertSaleCatentry(long catentry_id_rel, long storeId, long catentryId) throws Exception{
		findCatMapper.insertCatentryRel(catentry_id_rel, storeId,catentryId );
		deltaUtils.deltaBuild(catentryId, "catentry");
	}

	public void deleteCatentry(long catentry_id, long catentry_id_rel,
			long storeId) {
		findCatMapper.deleteCatentryRel(catentry_id,catentry_id_rel,storeId);
		deltaUtils.deltaBuild(catentry_id, "catentry");
	}

	public void modifySeq(long catentry_id, long catentry_id_rel, long seq, long storeId) {
		findCatMapper.modifySeq(catentry_id, catentry_id_rel, seq, storeId);
		deltaUtils.deltaBuild(catentry_id, "catentry");
	}
	
}
