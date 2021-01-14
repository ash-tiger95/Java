package com.sungho.tacos.data;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sungho.tacos.Order;
import com.sungho.tacos.Taco;

// SimpleJdbc 사용

@Repository
public class JdbcOrderRepository implements OrderRepository{
	
	private SimpleJdbcInsert orderInserter;
	private SimpleJdbcInsert orderTacoInserter;
	private ObjectMapper objectMapper;

	@Autowired
	public JdbcOrderRepository(JdbcTemplate jdbc) { // JdbcTemplate을 사용해서 두 개의 SimpleJdbcInsert 인스턴스 생성
	  this.orderInserter = new SimpleJdbcInsert(jdbc)
	      .withTableName("Taco_Order") // Taco_Order 테이블에 주문 데이터를 추가하기 위함
	      .usingGeneratedKeyColumns("id"); // Order 객체의 id값은 데이터베이스가 생성해주는 것을 사용

	  this.orderTacoInserter = new SimpleJdbcInsert(jdbc)
	      .withTableName("Taco_Order_Tacos");

	  this.objectMapper = new ObjectMapper();
	}

	@Override
	public Order save(Order order) {
	  order.setPlacedAt(new Date());
	  
	  long orderId = saveOrderDetails(order);
	  
	  order.setId(orderId);
	  List<Taco> tacos = order.getTacos();
	  
	  for (Taco taco : tacos) {
	    saveTacoToOrder(taco, orderId);
	  }

	  return order;
	}

	private long saveOrderDetails(Order order) {
	  @SuppressWarnings("unchecked")
	  Map<String, Object> values = objectMapper.convertValue(order, Map.class);
	  values.put("placedAt", order.getPlacedAt());

	  long orderId = orderInserter.executeAndReturnKey(values).longValue();
	  return orderId;
	}

	private void saveTacoToOrder(Taco taco, long orderId) {
	  Map<String, Object> values = new HashMap<>();
	  values.put("tacoOrder", orderId);
	  values.put("taco", taco.getId());
	  orderTacoInserter.execute(values);
	}
}
