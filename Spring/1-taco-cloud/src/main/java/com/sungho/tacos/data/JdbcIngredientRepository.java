package com.sungho.tacos.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sungho.tacos.Ingredient;

/*
 * 순서1)
 * @Repository는 스테레오타입 애노테이션
 * 스프링 컴포넌트 검색에서 이 클래스를 자동으로 찾아서 스프링 애플리케이션 컨텍스트의 빈으로 생성해준다.
 * 
 * 순서2)
 * @Autowired를 통해 스프링이 해당 빈을 JdbcTemplate에 주입(연결)한다.
 * 
 * 순서3)
 * 생성자의 인스턴스 변수는 데이터베이스의 데이터를 쿼리하고 추가하기 위해 다른 메서드에서 사용된다.
 * 
 * 순서4)
 * jdbc 메서드 구현
 * 
 * 순서5)
 * DesignTacoController에 JdbcIngredientRepository를 주입(연결)
 */
@Repository
public class JdbcIngredientRepository implements IngredientRepository{
	
	private JdbcTemplate jdbc;
	
	@Autowired
	public JdbcIngredientRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	// query(SQL, 스프링의 RowMapper 인터페이스), findAll(): 리스트 반환
	@Override
	public Iterable<Ingredient> findAll() {
		return jdbc.query("select id, name, type from Ingredient", this::mapRowToIngredient);
	}

	// queryForObject(SQL, 스프링의 RowMapper 인터페이스, 검색할 행의 id), findById(): 객체 하나 반환
	@Override
	public Ingredient findById(String id) {
		return jdbc.queryForObject("select id, name, type from Ingredient where id=?", this::mapRowToIngredient, id);
	}

	// 쿼리롤 생성된 결과 세트(ResultSet)의 행 개수만큼 호출되며, 결과 세트의 모든 행을 각각 객체로 생성하고 List에 저장한 후 반환한다.
	private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException {
		return new Ingredient(rs.getString("id"), rs.getString("name"), Ingredient.Type.valueOf(rs.getString("type")));
	}

	// update(): 데이터베이스에 데이터를 추가하거나 변경하는 쿼리에도 사용될 수 있다.
	@Override
	public Ingredient save(Ingredient ingredient) {
		jdbc.update("insert into Ingredient (id, name, type) values (?, ?, ?)", ingredient.getId(),
				ingredient.getName(), ingredient.getType().toString());
		return ingredient;
	}
}
