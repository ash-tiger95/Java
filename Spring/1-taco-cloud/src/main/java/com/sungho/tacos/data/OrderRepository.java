package com.sungho.tacos.data;

import org.springframework.data.repository.CrudRepository;

import com.sungho.tacos.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
