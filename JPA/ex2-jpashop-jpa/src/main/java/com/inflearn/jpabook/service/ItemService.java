package com.inflearn.jpabook.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inflearn.jpabook.domain.item.Item;
import com.inflearn.jpabook.repo.ItemRepo;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

	private final ItemRepo itemRepo;
	
	@Transactional
	public void saveItem(Item item) {
		itemRepo.save(item);
	}
	
	public List<Item> findItems(){
		return itemRepo.findAll();
	}
	
	public Item findOne(Long itemId) {
		return itemRepo.findOne(itemId);
	}
}
