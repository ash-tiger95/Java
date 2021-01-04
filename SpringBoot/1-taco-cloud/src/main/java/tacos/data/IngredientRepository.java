package tacos.data;

import org.springframework.data.repository.CrudRepository;

import tacos.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String>{ // CrudRepository<저장되는 개체 타입, ID 속성의 타입>
//	Iterable<Ingredient> findAll();
//	Ingredient findById(String id);
//	Ingredient save(Ingredient ingredient);
}
