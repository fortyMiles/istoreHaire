package com.istore.common.core.mng.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.istore.common.core.bean.Order;
import com.istore.common.core.dao.OrderDao;
import com.istore.common.core.mng.OrderMng;

@Service
@Transactional
public class OrderMngImpl implements OrderMng{

	@Autowired
	OrderDao orderDao;

	public Integer queryOrderCount(String shopId) {
		int countNum = 0;
		if(shopId != null && !"".equals(shopId)){
			Order order = orderDao.findOrderCount(shopId);
			if(order != null){
				countNum = order.getCount();
			}
		}
		return countNum;
	}

	public List<Order> queryOrderList(String shopId, int index, int sum) {
		List<Order> list = new ArrayList<Order>();
		if(shopId != null && !"".equals(shopId)){
			list = orderDao.findOrderList(shopId, index, sum);
		}
		return list;
	}

	public Integer queryOrderDetailCount(String orderId) {
		int countNum = 0;
		if(orderId != null && !"".equals(orderId)){
			Order order = orderDao.findOrderDetailCount(orderId);
			if(order != null){
				countNum = order.getCount();
			}
		}
		return countNum;
	}

	public List<Order> queryOrderDetailList(String orderId, int index, int sum) {
		List<Order> list = new ArrayList<Order>();
		if(orderId != null && !"".equals(orderId)){
			list = orderDao.findOrderDetailList(orderId, index, sum);
		}
		return list;
	}



	

}
