package com.istore.common.core.provider;

import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class CatentrySqlProvider {
	
	public String getTotalCount(Map<String, Object> map){
		StringBuffer buffer = new StringBuffer();
		buffer.append("".equals( map.get("userName"))?"":" and a.firstname like '%"+  map.get("userName")+"%'");
		buffer.append("".equals( map.get("code"))?"":" and o.field7 = '"+  map.get("code") +"'");
		buffer.append("".equals(map.get("phone"))?"":" and a.phone1 = '"+  map.get("phone") +"'");
		buffer.append("".equals(map.get("startDate"))?"":" and o.dateofbirth>=to_date('"+ map.get("startDate") +"','yyyy-MM-dd')");
		buffer.append("".equals(map.get("endDate"))?"":" and o.dateofbirth<=to_date('"+ map.get("endDate") +"','yyyy-MM-dd')");
		String str = buffer.toString();
		SQL sql=new SQL()
		.SELECT("count(g.users_id)as count")
		.FROM(" userreg g")
		.LEFT_OUTER_JOIN(" users s on g.users_id = s.users_id")
		.LEFT_OUTER_JOIN(" userdemo o on g.users_id = o.users_id")
		.LEFT_OUTER_JOIN(" address a on g.users_id=a.member_id ")
		.WHERE("a.SELFADDRESS='1' and (o.field1='1' or o.field1='2')"+str);
		return sql.toString();
	}
	
	
	
	
	public String getUserJson(Map<String, Object> parameters){
		String index =  parameters.get("index").toString();
		String sum =  parameters.get("sum").toString();
		StringBuffer buffer = new StringBuffer();
		buffer.append("".equals( parameters.get("userName"))?"":" and a.firstname like'%"+  parameters.get("userName")+"%'");
		buffer.append("".equals( parameters.get("code"))?"":" and o.field7 = '"+  parameters.get("code") +"'");
		buffer.append("".equals(parameters.get("phone"))?"":" and a.phone1 = '"+  parameters.get("phone") +"'");
		buffer.append("".equals(parameters.get("startDate"))?"":" and o.dateofbirth>=to_date('"+ parameters.get("startDate") +"','yyyy-MM-dd')");
		buffer.append("".equals(parameters.get("endDate"))?"":" and o.dateofbirth<=to_date('"+ parameters.get("endDate") +"','yyyy-MM-dd')");
		String str = buffer.toString();
		SQL sql=new SQL()
			.SELECT("g.users_id, g.logonid ,o.field7 as codeNum, a.phone1,a.firstname,o.dateofbirth as birthDay,s.field1")
			.FROM("userreg g")
			.LEFT_OUTER_JOIN("users s on g.users_id = s.users_id")
			.LEFT_OUTER_JOIN(" userdemo o on g.users_id = o.users_id")
			.LEFT_OUTER_JOIN("address a on g.users_id=a.member_id")
			.WHERE("a.selfaddress='1' and (o.field1='1' or o.field1='2')"+str);
		return "SELECT w.users_id, w.logonid ,w.codeNum, w.phone1,w.birthDay,w.field1, w.firstname FROM (SELECT k.users_id, k.logonid ,k.codeNum, " +
				"k.phone1,k.firstname,k.birthDay,k.field1,rownum rn  FROM ("+sql.toString()+") k)w WHERE  rn between "+ index +" and "+ sum ;
	}
	
	public String getOldTotalCount(Map<String, Object> map){
		StringBuffer buffer = new StringBuffer();
		buffer.append("".equals( map.get("code"))?"":" and d.gbkh = '"+  map.get("code") +"'");
		buffer.append("".equals(map.get("phone"))?"":" and d.lxdh = '"+  map.get("phone") +"'");
		String str = buffer.toString();
		SQL sql=new SQL()
		.SELECT("count(d.visiid)as count")
		.FROM(" t_vip_visitantcard d")
		.WHERE("1=1"+str);
		return sql.toString();
	}

	
	public String getOldUserJson(Map<String, Object> parameters){
		String index =  parameters.get("index").toString();
		String sum =  parameters.get("sum").toString();
		StringBuffer buffer = new StringBuffer();
		buffer.append("".equals( parameters.get("code"))?"":" and d.gbkh = '"+  parameters.get("code") +"' and d.gbkh not in (select o.field7 from userdemo o where o.field7 is not null)");
		buffer.append("".equals(parameters.get("phone"))?"":" and d.lxdh = '"+  parameters.get("phone") +"'");
		String str = buffer.toString();
		SQL sql=new SQL()
			.SELECT("d.visiid,d.gbkh,d.xm,d.csny,d.djfd,d.lxdh")
			.FROM(" t_vip_visitantcard d")
//			.LEFT_OUTER_JOIN("x_sap s on d.djfd = s.shopid_pos")
			.WHERE("1=1"+str);
		String ssq = "SELECT w.visiid,w.gbkh,w.xm,w.csny,w.shopid_sap,w.lxdh FROM (SELECT k.visiid,k.gbkh,k.xm,k.csny,k.djfd as shopid_sap," +
			" k.lxdh,rownum rn  FROM ("+sql.toString()+") k)w WHERE  rn between "+ index +" and "+ sum ;
		return ssq ;
	}
	
}
