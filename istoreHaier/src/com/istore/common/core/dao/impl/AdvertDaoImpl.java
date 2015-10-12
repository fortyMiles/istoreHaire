/**
 * 
 */
package com.istore.common.core.dao.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibm.commerce.exception.ECException;
import com.istore.common.core.bean.Advert;
import com.istore.common.core.bean.FTP;
import com.istore.common.core.dao.AdvertDao;
import com.istore.common.core.mapper.FindAdvertMapper;
import com.istore.common.core.mapper.FindUserMapper;
import com.istore.common.web.util.FreeMarkerUtil;
import com.istore.common.web.util.FtpAppClientUtil;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * @author wangyan
 *
 */

@Repository
public class AdvertDaoImpl implements AdvertDao {
	
	@Autowired
	private FindAdvertMapper findAdvertMapper;

	/* (non-Javadoc)
	 * @see com.istore.common.core.dao.AdvertDao#getAdvertUpList()
	 */
	public List<Advert> getAdvertUpList(int advertType,int startIndex,int endIndex,int storeId) {
		// TODO Auto-generated method stub
		if(advertType==3)
		{
			return findAdvertMapper.getAdvertList3(advertType, startIndex, endIndex, storeId);
		}
		else{
		return findAdvertMapper.getAdvertList(advertType,startIndex,endIndex,storeId);}
	}

	public List<FTP> getFTPinfo() {
		// TODO Auto-generated method stub
		/*List<FTP> ftpInfo=new ArrayList<FTP>();
		ftpInfo=findAdvertMapper.getFTPinfo();
	   int i=ftpInfo.size();*/
		return findAdvertMapper.getFTPinfo();
	}

	public String advertImageAdd(int priority, String url, String imageUrl,
			String desc, int type,String prodouctGroup,String storeId) {
		// TODO Auto-generated method stub
		String flag="false";
		int result=findAdvertMapper.addAdvertByName(priority, url, imageUrl, desc, type,prodouctGroup,storeId);
		if(result==1)
		{
			flag="success";
		}
		return flag;
	}

	public String findMaxPriority(String type) {
		// TODO Auto-generated method stub
		String  priority = findAdvertMapper.findMaxPriority(type);
		if(priority==null || priority.equals("") ){
			priority = "0";
		}
		return priority;
	}

	public List<Advert> getAdvertById(int advertId) {
		// TODO Auto-generated method stub
		return findAdvertMapper.getAdvertById(advertId);
	}

	public String updateAdvert(int advertId, String finalfilename, String link,
			String desc,String prodouctGroup) {
		// TODO Auto-generated method stub
		String flag="false";
		int result=findAdvertMapper.updateAdvertById(advertId,finalfilename, link, desc,prodouctGroup);
		if(result==1)
		{
			flag="success";
		}
		return flag;
	}

	public String deleteAdvert(int advertId) {
		// TODO Auto-generated method stub
		String flag="false";
		int result =findAdvertMapper.deleteAdvertById(advertId);
		if(result==1)
		{
			flag="success";
		}
		return flag;
	}

	public String updateAdvertPrority(int id, String priority) {
		// TODO Auto-generated method stub
		String flag="false";
		int result=findAdvertMapper.updateAdvertPrority(id,priority);
		if(result==1)
		{
			flag="success";
		}
		return flag;
	}
	
	//根据usage获取不同的LIST
    private Map getParamObjectByEId(int espotId, String usage){
    	Map map=new HashMap();
    	
    	if("1".equals(usage))
    	{
    		
    		  Object hpbImageList = this.getImageMapByEId(espotId, 1);
              map.put("hpbImageList", hpbImageList);
    	}
    	 if("2".equals(usage))
    	{
    		 Object hpbImageList = this.getImageMapByEId(espotId, 2);
             map.put("hpbImageList", hpbImageList);
    	}
    	 if("3".equals(usage))
     	{
     		 Object hpbImageList = this.getImageMapByEId(espotId, 3);
              map.put("hpbImageList", hpbImageList);
     	}
    	
    	return map;
    	
    }
    
    private Object getImageMapByEId(int advertId, int type){
    	
    	
    	return findAdvertMapper.getImageMapByEId(advertId, type);
    	
    }
    
    

	public int createAdvertHtml(String usage, String templateName,List ftplist) {
		// TODO Auto-generated method stub
		Configuration config = new Configuration();
		 try {
			config.setDirectoryForTemplateLoading(new File(templateName));
			config.setObjectWrapper(new DefaultObjectWrapper());
		    config.setDefaultEncoding("UTF-8");
		    
		    Template template = config.getTemplate(usage + ".html", "UTF-8");
            FreeMarkerUtil freeMarker = new FreeMarkerUtil();
            
            List<Advert> espotIdList = new ArrayList<Advert>();
            espotIdList = this.getAdvertUpList(Integer.valueOf(usage),0,999,10151);
            StringBuffer sb = new StringBuffer();
            
            for(int i=0;i<espotIdList.size();i++)
            {
            	Map map=new HashMap();
            	int advertId=espotIdList.get(i).getId();
            	
            	map=this.getParamObjectByEId(advertId, usage);
            	 String html = freeMarker.analysisTemplate1(template, map);
            	 sb.append(html);
            	
            }
            
            FtpAppClientUtil ftpAppClientUtil = new FtpAppClientUtil();
            ftpAppClientUtil.uploadHtmlFile("1", usage + ".html", sb.toString(),ftplist);
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ECException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
		
		return 1;
	}

	public int getAdvertListSize(int advertType,String storeId) {
		// TODO Auto-generated method stub
		return findAdvertMapper.getAdvertListSize(advertType,storeId);
	}

}
