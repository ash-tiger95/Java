package com.inflearn.jpa.repo;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.inflearn.jpa.entity.Team;

@Repository
public class TeamJpaRepo {

	@PersistenceContext
	private EntityManager em;

	public Team save(Team team) {
		em.persist(team);
		return team;
	}

	public void delete(Team team) {
		em.remove(team);
	}

	public List<Team> findAll() {
		return em.createQuery("select m from Team t", Team.class).getResultList();
	}

	public Optional<Team> findById(Long id) {
		Team team = em.find(Team.class, id);
		return Optional.ofNullable(team); // ofNullable(): member가 null일 수 있고 아닐 수 있다라는 의미
	}

	public Long count() {
		return em.createQuery("select count(t) from Team t", Long.class).getSingleResult();
	}
}
