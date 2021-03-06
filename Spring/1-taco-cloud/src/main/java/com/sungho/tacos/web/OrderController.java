package com.sungho.tacos.web;

import java.awt.print.Pageable;

import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.sungho.tacos.Order;
import com.sungho.tacos.User;
import com.sungho.tacos.data.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
	
	private OrderProps props;
	
	private OrderRepository orderRepo;
	
	public OrderController(OrderRepository orderRepo, OrderProps props) {
		this.orderRepo = orderRepo;
		this.props = props;
	}

	@GetMapping("/current")
	  public String orderForm(@AuthenticationPrincipal User user, @ModelAttribute Order order) {
		  if (order.getDeliveryName() == null) {
			  order.setDeliveryName(user.getFullname());
		  }
		  if (order.getDeliveryStreet() == null) {
			  order.setDeliveryStreet(user.getStreet());
		  }
		  if (order.getDeliveryCity() == null) {
			  order.setDeliveryCity(user.getCity());
		  }
		  if (order.getDeliveryState() == null) {
			  order.setDeliveryState(user.getState());
	      }
		  if (order.getDeliveryZip() == null) {
			  order.setDeliveryZip(user.getZip());
		  }

	    return "orderForm";
	  }
	

	@PostMapping
	public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus, @AuthenticationPrincipal User user) { // 제출된 폼 필드와 바인딩된 속성을 갖는 Order 객체가 인자로 전달
		if(errors.hasErrors()) {
			return "orderForm";
		}
		log.info("Order submitted: " + order);
		
		order.setUser(user);
		
		orderRepo.save(order);
		sessionStatus.setComplete();
		return "redirect:/"; // home으로 이동
	}
	
	@GetMapping
	public String ordersForUser(
	    @AuthenticationPrincipal User user, Model model) {

		Pageable pageable = (Pageable) PageRequest.of(0, props.getPageSize());
		
		model.addAttribute("orders",
	      orderRepo.findByUserOrderByPlacedAtDesc(user, pageable));
	  
		return "orderList";
	}

}
