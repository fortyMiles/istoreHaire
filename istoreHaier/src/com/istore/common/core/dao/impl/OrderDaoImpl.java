package com.istore.common.core.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.istore.common.core.bean.Order;
import com.istore.common.core.dao.OrderDao;
import com.istore.common.core.mapper.FindOrderMapper;

@Repository
public class OrderDaoImpl  implements OrderDao {

	
	@Autowired
	private FindOrderMapper findOrderMapper;


	public Order findOrderCount(String shopId) {
		Order order = new Order();
		if(shopId != null && !"".equals(shopId)){
			order = findOrderMapper.getCountOrder(shopId);
		}
		return order;
	} 
	
	
	public List<Order> findOrderList(String shopId, int index, int sum) {
		List<Order> list = new ArrayList<Order>();
		if(shopId != null && !"".equals(shopId)){
			list = findOrderMapper.getOrderList(shopId, index, sum);
		}
		return list;
	}


	public Order findOrderDetailCount(String orderId) {
		Order order = new Order();
		if(orderId != null && !"".equals(orderId)){
			order = findOrderMapper.getCountOrderDetail(orderId);
		}
		return order;
	}


	public List<Order> findOrderDetailList(String orderId, int index, int sum) {
		List<Order> list = new ArrayList<Order>();
		if(orderId != null && !"".equals(orderId)){
			list = findOrderMapper.getOrderDetailList(orderId, index, sum);
		}
		return list;
	}



	

	

}
