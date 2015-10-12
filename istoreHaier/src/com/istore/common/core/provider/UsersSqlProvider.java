package com.istore.common.core.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class UsersSqlProvider {

	/**
	 * 获得会员数量
	 */
	public String getUserList(Map<String, Object> params){
		SQL sql = new SQL().SELECT("xusers.USERS_ID ,xusers.STATUS, xusers.CUSTOMER_ID, xusers.LASTUPDATE, userreg.LOGONID")
		.FROM("xusers,userreg").WHERE("xusers.USERS_ID = userreg.USERS_ID and xusers.STOREID="+params.get("storeId"));
		return sql.toString();
	}
	
	public String getVerifyUserListSize(Map<String, Object> parameters){
		SQL sql = new SQL().SELECT(" count(1) ")
		.FROM("xusers a ,userreg b ").WHERE("a.USERS_ID = b.USERS_ID and a.status='"+parameters.get("status")+"'");
		String q=sql.toString();
		System.out.println("q="+q);
		return sql.toString();
	}
}
