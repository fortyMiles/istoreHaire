package com.istore.common.core.mng;

import java.util.List;

import com.istore.common.core.bean.Order;




public interface OrderMng {
	
	//查询订单总数
	public Integer queryOrderCount(String shopId);

	//查询订单列表
	public List<Order> queryOrderList(String shopId, int index, int sum);
	
	//根据订单的id查询orderitems中的子订单总数
	public Integer queryOrderDetailCount(String orderId);
	
	//根据订单的id查询orderitems中的子订单列表
	public List<Order> queryOrderDetailList(String orderId, int index, int sum);
}
