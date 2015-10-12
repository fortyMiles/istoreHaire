package com.istore.common.core.dao;

import java.util.List;
import com.istore.common.core.bean.Order;



public interface OrderDao {
	
	//查询订单总数
	public Order findOrderCount(String shopId);
	
	//查询订单列表
	public List<Order> findOrderList(String shopId, int index, int sum);
	
	//根据订单的id查询orderitems中的子订单总数
	public Order findOrderDetailCount(String orderId);
	
	//根据订单的id查询orderitems中的子订单列表
	public List<Order> findOrderDetailList(String orderId, int index, int sum);
}
