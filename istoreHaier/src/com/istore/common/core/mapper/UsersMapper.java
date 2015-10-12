package com.istore.common.core.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.istore.common.core.bean.User;
import com.istore.common.core.provider.UsersSqlProvider;

public interface UsersMapper {
	
	public static final String GET_VERIFYUSERS_LIST = "select users_id, logonid, decode(status,null,' ','R','审核未通过','P','待审核','A','审核通过') status," +
			" decode(customer_name,null,' ',customer_name) customer_name, decode(lastupdate, null,' ',to_char(lastupdate,'yyyy-mm-dd hh24:mi:ss')) lastupdate," +
			" decode(type, null,' ','I','Installer','E','Engineer','O','Other','D','Distributor') type from (select u.USERS_ID,u.LOGONID,x.STATUS, x.CUSTOMER_NAME, x.LASTUPDATE,  x.TYPE,ROWNUM ROWINDEX " +
			" from xusers x join userreg u on u.USERS_ID = x.USERS_ID where x.status !='N' and x.storeId = #{storeId}) a where a.ROWINDEX > #{startIndex} and a.ROWINDEX <= #{endIndex} order by a.lastupdate desc";

	/**
	 * 获得会员数量
	 */
	@SelectProvider(type = UsersSqlProvider.class, method = "getUserList")
	@Results(value={
			@Result(column = "USERS_ID", property = "usersId", id=true),
			@Result(column = "STATUS", property = "status"),
			@Result(column = "customer_name", property = "customer_name"),
			@Result(column = "LASTUPDATE", property = "lastUpdate"),
			@Result(column = "LOGONID", property = "logonId")
	})
	public List<User> getUserListSize(@Param("storeId") String storeId);
	
	public static final String GET_USERS_LIST = "select users_id, logonid, decode(status,null,' ','N','Customer','P','Pending','C','Completed') status," +
			" decode(customer_name,null,' ',customer_name) customer_name, decode(lastupdate, null,' ',to_char(lastupdate,'yyyy-mm-dd hh24:mi:ss')) lastupdate," +
			" decode(type, null,' ','I','Installer','E','Engineer','O','Other','D','Distributor') type from (select u.USERS_ID,u.LOGONID,x.STATUS, x.CUSTOMER_NAME, x.LASTUPDATE,  x.TYPE,ROWNUM ROWINDEX " +
			" from xusers x join userreg u on u.USERS_ID = x.USERS_ID where x.STOREID = #{storeId}) a where a.ROWINDEX > #{startIndex} and a.ROWINDEX <= #{endIndex} order by a.lastupdate desc";
	/**
	 * 分页查询会员信息
	 */
	@Select(GET_USERS_LIST)
	@Results(value={
			@Result(column = "users_id", property = "usersId"),
			@Result(column = "logonid", property = "logonId"),
			@Result(column = "status", property = "status"),
			@Result(column = "customer_name", property = "customer_name"),
			@Result(column = "lastupdate", property = "lastUpdate"),
			@Result(column = "type", property = "type")
	})
	public List<User> getUserList(@Param("startIndex") int startIndex, 
			@Param("endIndex") int endIndex,@Param("storeId") String storeId);
	
	public final String GTE_USRE = "select u.USERS_ID, decode(x.CUSTOMER_NAME,null,' ',x.CUSTOMER_NAME) CUSTOMER_NAME, a.EMAIL1 email, a.PHONE1 phone, decode(x.TYPE, null,' ','I','Installer','E','Engineer','O','Other','D','Distributor') type , a.COUNTRY, a.CITY,a.ZIPCODE,decode(x.LASTUPDATE, null,' ', to_char(x.LASTUPDATE,'yyyy-mm-dd hh24:mi:ss')) createtime from userreg u join address a on u.users_id = a.MEMBER_ID  " +
	"	join xusers x on x.users_id = u.users_id where a.SELFADDRESS = '1' and u.users_id = #{users_id}";
	/**
	 * 查询会员详细信息
	 */
	@Select(GTE_USRE)
	@Results(value={
		@Result(column = "USERS_ID", property = "usersId", id=true),
		@Result(column = "CUSTOMER_NAME", property = "customer_name"),
		@Result(column = "EMAIL", property = "email"),
		@Result(column = "PHONE", property = "phone"),
		@Result(column = "TYPE", property = "type"),
		@Result(column = "COUNTRY", property = "country"),
		@Result(column = "CITY", property = "city"),
		@Result(column = "ZIPCODE", property = "zipcode"),
		@Result(column = "CREATETIME", property = "createtime")
	})
	public User getUserDetail(@Param("users_id") long users_id);
	
	/**
	 * 获得待审核会员数量
	 */
	@SelectProvider(type = UsersSqlProvider.class, method = "getVerifyUserListSize")
	public  int getVerifyUserListSize(@Param("status") String status,@Param("storeId") String storeId);
	
	/**
	 * 分页查询待审核会员
	 */
	@Select(GET_VERIFYUSERS_LIST)
	@Results(value={
			@Result(column = "users_id", property = "usersId"),
			@Result(column = "logonid", property = "logonId"),
			@Result(column = "status", property = "status"),
			@Result(column = "customer_name", property = "customer_name"),
			@Result(column = "lastupdate", property = "lastUpdate"),
			@Result(column = "type", property = "type")
	})
	public List<User> getVerifyUserList(@Param("startIndex") int startIndex, 
			@Param("endIndex") int endIndex,@Param("storeId") String storeId);

	
	//getVerifyUserDetail
	
	public final String GET_USRE = "select u.USERS_ID, decode(x.CUSTOMER_NAME,null,' ',x.CUSTOMER_NAME) CUSTOMER_NAME, a.EMAIL1 email, a.PHONE1 phone, " +
			"decode(x.TYPE, null,' ','I','Installer','E','Engineer','O','Other','D','Distributor') type , a.COUNTRY, a.CITY,a.ZIPCODE,decode(x.LASTUPDATE, null,' ', to_char(x.LASTUPDATE,'yyyy-mm-dd hh24:mi:ss')) createtime ,x.status," +
			"g.CUSTOMER_NUMBER,g.BANK_NAME,g.BANK_REGION,g.STREET_ROOM,g.CITY_STREET_ROOM,g.BANK_CATEGORY,g.SWIFT,g.BANK_GROUP from userreg u join address a on u.users_id = a.MEMBER_ID  join xusers x on x.users_id = u.users_id join xuserreg g on g.users_id=u.users_id where a.SELFADDRESS = '1' and u.users_id =#{users_id}";
	
	/**
	 * 查询待审核会员详细信息
	 */
	@Select(GET_USRE)
	@Results(value={
		@Result(column = "USERS_ID", property = "usersId", id=true),
		@Result(column = "CUSTOMER_NAME", property = "customer_name"),
		@Result(column = "EMAIL", property = "email"),
		@Result(column = "PHONE", property = "phone"),
		@Result(column = "TYPE", property = "type"),
		@Result(column = "COUNTRY", property = "country"),
		@Result(column = "CITY", property = "city"),
		@Result(column = "ZIPCODE", property = "zipcode"),
		@Result(column = "CREATETIME", property = "createtime"),
		@Result(column = "CUSTOMER_NUMBER", property = "customerNumber"),
		@Result(column = "BANK_NAME", property = "bankName"),
		@Result(column = "BANK_REGION", property = "bankRegion"),
		@Result(column = "STREET_ROOM", property = "streetRoom"),
		@Result(column = "CITY_STREET_ROOM", property = "cityStreetRoom"),
		@Result(column = "BANK_CATEGORY", property = "backGategory"),
		@Result(column = "SWIFT", property = "swift"),
		@Result(column = "BANK_GROUP", property = "backgroup"),
		@Result(column = "STATU", property = "status")
		
		
	})
	public User getVerifyUserDetail(@Param("users_id") long users_id);

	
	/**
	 * 获得管理员总数
	 */
	final String GETADMINLISTSIZE = "select count(1) from xusers";
	@Select(GETADMINLISTSIZE)
	public int getAdminListSize(@Param("storeId") String storeId);
	
	/**
	 * 审核会员
	 */
	public final String INSERT_APPROVE_USER="insert into XUSERAPPROVAL (id, USERS_ID,APPROVER_ID,STATUS_BEFORE,STATUS_AFTER,APPROVAL_DATE,APPROVE_RESULT) values (seq_XUSERAPPROVAL.nextval,#{users_id},#{approveId},#{statusBefore},#{statusAfter},#{createTime},#{approveResult})";
    
	@Insert(INSERT_APPROVE_USER)
	public int addApprovalUser( @Param("users_id")int users_id,@Param("approveId") int approverId,
			@Param("statusBefore")String statusBefore, @Param("statusAfter")String statusAfter, @Param("createTime")Timestamp createTime,
			@Param("approveResult")String approveResult);
	
	public final String UPDATE_USER_STATUS="update xusers  set status='A' where users_id=#{users_id}";
	
	@Update(UPDATE_USER_STATUS)
	public int updateUserStatus(@Param("users_id")String users_id);
	
	/**
	 * 拒绝会员
	 */
	public final String INSERT_REFUSE_USER="insert into XUSERAPPROVAL (id, USERS_ID,APPROVER_ID,STATUS_BEFORE,STATUS_AFTER,APPROVAL_DATE,APPROVE_RESULT) values (seq_XUSERAPPROVAL.nextval,#{users_id},#{approveId},#{statusBefore},#{statusAfter},#{createTime},#{approveResult})";
    
	@Insert(INSERT_REFUSE_USER)
	public int refuseApprovalUser( @Param("users_id")int users_id,@Param("approveId") int approverId,
			@Param("statusBefore")String statusBefore, @Param("statusAfter")String statusAfter, @Param("createTime")Timestamp createTime,
			@Param("approveResult")String approveResult);
	
	public final String UPDATE_REFUSE_USER_STATUS="update xusers  set status='R' where users_id=#{users_id}";
	
	@Update(UPDATE_REFUSE_USER_STATUS)
	public int updateRefuseUserStatus(@Param("users_id")String users_id);
	
	
}
