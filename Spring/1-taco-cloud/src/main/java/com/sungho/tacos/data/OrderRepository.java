package com.sungho.tacos.data;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sungho.tacos.Order;
import com.sungho.tacos.User;

public interface OrderRepository extends CrudRepository<Order, Long> {
	List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
