package com.inflearn.jpabook.repo;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.inflearn.jpabook.domain.item.Item;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ItemRepo {
	
	private final EntityManager em;
	
	public void save(Item item) {
		if(item.getId() == null	) {
			em.persist(item);
		} else {
			em.merge(item); // 일단 update라고 생각하자
		}
	}
	
	public Item findOne(Long id) {
		return em.find(Item.class, id);
	}
	
	public List<Item> findAll(){
		return em.createQuery("select i from Item i", Item.class)
				.getResultList();
	}
	
}
