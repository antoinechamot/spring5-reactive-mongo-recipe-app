package guru.springframework.repositories;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.reactive.RecipeReactiveRepository;

@RunWith(SpringRunner.class)
@DataMongoTest
public class RecipeReactiveRepositoryIT {
	
	@Autowired
	RecipeReactiveRepository recipeReactiveRepository;
	
	public void setUp() throws Exception {
		recipeReactiveRepository.deleteAll().block();
	}
	
	@Test
	public void testRecipeSave() throws Exception {
		Recipe recipe = new Recipe();
		recipe.setDescription("dummy");
		recipeReactiveRepository.save(recipe).block();
		
		Long count = recipeReactiveRepository.count().block();
		
		assertEquals(Long.valueOf(1L), count);
		
	}

}
