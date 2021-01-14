package com.sungho.tacos.data;

import com.sungho.tacos.Order;

public interface OrderRepository {
	Order save(Order order);
}
