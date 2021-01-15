package com.sungho.tacos.data;

import org.springframework.data.repository.CrudRepository;

import com.sungho.tacos.Taco;

// JDBC Template
/*
public interface TacoRepository {
	Taco save(Taco design);
}
*/

public interface TacoRepository extends CrudRepository<Taco, Long> {

}