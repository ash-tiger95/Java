package com.inflearn.jpabook.repo;

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
			em.merge(item);
		}
	}
	
	
}
