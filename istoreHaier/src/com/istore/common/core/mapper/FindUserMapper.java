package com.istore.common.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.istore.common.core.bean.User;
import com.istore.common.core.provider.UserSqlProvider;

public interface FindUserMapper {

	// 依据条件查询加盟商的会员总数
	@SelectProvider(type = UserSqlProvider.class, method = "getTotalCountForAdmin")
	@Results(value = { @Result(column = "count", property = "count"), })
	public User getTotalCountForAdmin(@Param("shopId") String shopId,
			@Param("userName") String userName, @Param("code") String code,
			@Param("phone") String phone,
			@Param("searchShopId") String searchShopId,
			@Param("startDate") String startDate,
			@Param("endDate") String endDate);
	
	// 依据条件查询加盟商的会员列表
	@SelectProvider(type = UserSqlProvider.class, method = "getUserListForAdmin")
	@Results(value = {
			@Result(column = "users_id", property = "usersId", id = true),
			@Result(column = "logonid", property = "logonId"),
			@Result(column = "codeNum", property = "codeNum"),
			@Result(column = "phone1", property = "phone"),
			@Result(column = "birthDay", property = "birthDay"),
			@Result(column = "shopid_pos", property = "shopId"),
			@Result(column = "firstname", property = "userName"), })
	public List<User> getUserListForAdmin(@Param("shopId") String shopId,
			@Param("userName") String userName, @Param("code") String code,
			@Param("phone") String phone,
			@Param("searchShopId") String searchShopId,
			@Param("startDate") String startDate,
			@Param("endDate") String endDate, @Param("index") int index,
			@Param("sum") int sum);

	public static final String FIND_BY_ID_USER_BYID = "select g.users_id, g.logonid ,s.field7 as codeNum, a.phone1,a.firstname,to_char(s.dateofbirth,'yyyy-MM-dd') as birthday,a.email1,p.fullname,u.field3 as newCardNum"
			+ " from users u left join userreg g on u.users_id = g.users_id left join userdemo s on u.users_id = s.users_id left join address a on u.users_id=a.member_id "
			+ " left join x_sap p on u.field1=p.shopid_sap where a.selfaddress='1' and u.users_id =#{usersId}";

	@Select(FIND_BY_ID_USER_BYID)
	@Results(value = {
			@Result(column = "users_id", property = "usersId", id = true),
			@Result(column = "logonid", property = "logonId"),
			@Result(column = "codeNum", property = "codeNum"),
			@Result(column = "phone1", property = "phone"),
			@Result(column = "firstname", property = "userName"),
			@Result(column = "birthday", property = "birthDay"),
			@Result(column = "email1", property = "email"),
			@Result(column = "fullname", property = "shopName"),
			@Result(column = "newCardNum", property = "newCode"), })
	public List<User> getUsersInfo(@Param("usersId") String usersId);

	/**
	 * LogonId获得Users_Id
	 * 
	 * @param logonid
	 * @return
	 */
	@SelectProvider(type = UserSqlProvider.class, method = "getUsersIdByLogonId")
	@Results(value = { @Result(column = "users_id", property = "users_id") })
	public String getUsersIdByLogonId(@Param("logonid") String logonid);

}
