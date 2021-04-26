package com.inflearn.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inflearn.jpa.entity.Team;

public interface TeamRepo extends JpaRepository<Team, Long>{

}
