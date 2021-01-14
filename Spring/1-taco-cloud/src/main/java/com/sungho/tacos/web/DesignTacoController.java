package com.sungho.tacos.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sungho.tacos.Ingredient;
import com.sungho.tacos.Ingredient.Type;
import com.sungho.tacos.Taco;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {
	
	@GetMapping
	public String showDesignForm(Model model) {
		List<Ingredient> ingredients = Arrays.asList(
			new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
			new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
			new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
			new Ingredient("CARN", "Carnitas", Type.PROTEIN),
			new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
			new Ingredient("LETC", "Lettuce", Type.VEGGIES),
			new Ingredient("CHED", "Cheddar", Type.CHEESE),
			new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
			new Ingredient("SLSA", "Salsa", Type.SAUCE),
			new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
		);

		Type[] types = Ingredient.Type.values();
		System.out.println(Arrays.toString(types)); // [WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE]
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}

		model.addAttribute("taco", new Taco());
		/* System.out.println(model.toString());
		 * {
		 * wrap=[
		 * 		Ingredient(id=FLTO, name=Flour Tortilla, type=WRAP), 
		 * 		Ingredient(id=COTO, name=Corn Tortilla, type=WRAP)
		 * 	], 
		 * protein=[
		 * 		Ingredient(id=GRBF, name=Ground Beef, type=PROTEIN), 
		 * 		Ingredient(id=CARN, name=Carnitas, type=PROTEIN)
		 * 	], 
		 * 	veggies=[
		 * 		Ingredient(id=TMTO, name=Diced Tomatoes, type=VEGGIES),
		 *  	Ingredient(id=LETC, name=Lettuce, type=VEGGIES)
		 *  ], 
		 *  cheese=[
		 *  	Ingredient(id=CHED, name=Cheddar, type=CHEESE), 
		 *  	Ingredient(id=JACK, name=Monterrey Jack, type=CHEESE)
		 *  ], 
		 *  sauce=[
		 *  	Ingredient(id=SLSA, name=Salsa, type=SAUCE), 
		 *  	Ingredient(id=SRCR, name=Sour Cream, type=SAUCE)
		 *  ], 
		 *  taco=Taco(name=null, ingredients=null)
		 *  }
		 */

		return "design";
	}

	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
	}

	@PostMapping
	public String processDesign(@Valid Taco design, Errors errors) { // view에서 보내는 객체와 자동으로 바인딩
		// 유효성 검사는 post로 값을 받고 검사한다. 그리고 유효성검사를 한다는 의미로 @Valid 어노테이션을 사용한다. 유효성 검사 규칙은 DTO에서 진행
		if (errors.hasErrors()) { // 에러가 발생하면 Errors 객체에 저장되어 전달된다.
			return "design";
		}
		log.info("Processing design: " + design);

		return "redirect:/orders/current"; // 실행이 끝난 후 브라우저가 /orders/current 상대 결로로 재접속
	}

}
