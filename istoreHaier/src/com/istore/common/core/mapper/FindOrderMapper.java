package com.istore.common.core.mapper;


import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import com.istore.common.core.bean.Order;
import com.istore.common.core.provider.OrderSqlProvider;


public interface FindOrderMapper {
	
	//查询订单的总数
	@SelectProvider(type = OrderSqlProvider.class, method = "getCountOrder")
	@Results(value = { 
			@Result(column = "count", property = "count"),
	})
	public Order getCountOrder(@Param("shopId") String shopId);
	
	
	
	//查询订单列表
	@SelectProvider(type = OrderSqlProvider.class, method = "getOrderList")
	@Results(value = { 
			@Result(column = "orders_id", property = "orderId",id=true),
			@Result(column = "totalproduct", property = "totalProduct"),
			@Result(column = "orderTime", property = "orderTime"),
			@Result(column = "status", property = "status"),
			@Result(column = "member_id", property = "memberId")
	})
	public  List<Order> getOrderList(@Param("shopId") String shopId,@Param("index") int index,@Param("sum") int sum);
	
	//根据订单的id查询orderitems中的子订单总数
	@SelectProvider(type = OrderSqlProvider.class, method = "getCountOrderDetail")
	@Results(value = { 
			@Result(column = "count", property = "count"),
	})
	public Order getCountOrderDetail(@Param("orderId") String orderId);
	
	
	
	//根据订单的id查询orderitems中的子订单列表
	@SelectProvider(type = OrderSqlProvider.class, method = "getOrderDetailList")
	@Results(value = { 
			@Result(column = "orderitems_id", property = "orderitemsId",id=true),
			@Result(column = "catentry_id", property = "catentryId"),
			@Result(column = "partnum", property = "partnum"),
			@Result(column = "member_id", property = "memberId"),
			@Result(column = "price", property = "price"),
			@Result(column = "quantity", property = "quantity"),
			@Result(column = "totalproduct", property = "totalProduct"),
			@Result(column = "status", property = "status"),
			@Result(column = "lastupdate", property = "lastupdate")
	})
	public  List<Order> getOrderDetailList(@Param("orderId") String orderId,@Param("index") int index,@Param("sum") int sum);
	

	
}
