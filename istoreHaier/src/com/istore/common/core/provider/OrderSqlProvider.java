package com.istore.common.core.provider;

import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class OrderSqlProvider {
	
	
	public String insertSql() {
		return new SQL().INSERT_INTO("t_user")
			.VALUES("USERNAME","#{user.username}")
			.VALUES("PASSWORD", "#{user.password}")
			.VALUES("EMAIL", "#{user.email}").toString();
	}
	
	public String updateSql() {
		return new SQL().UPDATE("t_user")
			.SET("USERNAME = #{user.username}")
			.SET("PASSWORD = #{user.password}")
			.SET("EMAIL = #{user.password}")
	        .WHERE("USER_ID = #{user.id}").toString();  
	}
	
	public String getCountOrder(Map<String, Object> parameters){
		String str = parameters.get("shopId").toString();
		SQL sql=new SQL()
		.SELECT("count(s.orders_id)as count")
		.FROM(" orders s")
		.WHERE("s.STOREENT_ID ='"+str+"'");
		return sql.toString();
	}
	
	public String getOrderList(Map<String, Object> parameters){
		String str = parameters.get("shopId").toString();
		String index =  parameters.get("index").toString();
		String sum =  parameters.get("sum").toString();
		SQL sql=new SQL()
		.SELECT(" s.orders_id, s.totalproduct ,s.timeplaced as orderTime,s.status,s.member_id,s.address_Id")
		.FROM(" orders s")
		.WHERE("s.STOREENT_ID ='"+str+"'");
		return "select w.* from (select k.*,rownum rn from("+ sql.toString() +")k)w where rn between "+ index +" and "+ sum;
	}
	
	public String getCountOrderDetail(Map<String, Object> parameters){
		String str = parameters.get("orderId").toString();
		SQL sql=new SQL()
		.SELECT("count(s.orderitems_id)as count")
		.FROM(" orderitems s")
		.LEFT_OUTER_JOIN("orders o on s.orders_id=o.orders_id ")
		.WHERE("s.orders_id ='"+str+"'");
		return sql.toString();
	}
	
	public String getOrderDetailList(Map<String, Object> parameters){
		String str = parameters.get("orderId").toString();
		String index =  parameters.get("index").toString();
		String sum =  parameters.get("sum").toString();
		SQL sql=new SQL()
		.SELECT(" s.orderitems_id,s.orders_id,s.catentry_id,s.partnum,s.member_id,s.price,s.status,s.lastupdate,s.totalproduct,s.quantity")
		.FROM(" orderitems s")
		.LEFT_OUTER_JOIN("orders o on s.orders_id=o.orders_id ")
		.WHERE("s.orders_id ='"+str+"'");
		return "select w.* from (select k.*,rownum rn from("+ sql.toString() +")k)w where rn between "+ index +" and "+ sum;
	}
	
}
