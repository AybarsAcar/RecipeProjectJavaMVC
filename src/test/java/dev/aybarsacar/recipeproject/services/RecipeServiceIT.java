package dev.aybarsacar.recipeproject.services;

import dev.aybarsacar.recipeproject.commands.RecipeCommand;
import dev.aybarsacar.recipeproject.converters.RecipeCommandToRecipe;
import dev.aybarsacar.recipeproject.converters.RecipeToRecipeCommand;
import dev.aybarsacar.recipeproject.domain.Recipe;
import dev.aybarsacar.recipeproject.repositories.RecipeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest   // bring in the entire context
public class RecipeServiceIT
{
  public static final String NEW_DESCRIPTION = "New Description";   // value we are testing against

  @Autowired
  RecipeService recipeService;

  @Autowired
  RecipeRepository recipeRepository;

  @Autowired
  RecipeCommandToRecipe recipeCommandToRecipe;

  @Autowired
  RecipeToRecipeCommand recipeToRecipeCommand;

  @Transactional
  @Test
  @DirtiesContext
  public void testSaveOfDescription() throws Exception
  {
    //given
    var recipes = recipeRepository.findAll();
    Recipe testRecipe = recipes.iterator().next();
    RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(testRecipe);

    //when
    assert testRecipeCommand != null;
    testRecipeCommand.setDescription(NEW_DESCRIPTION);
    RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(testRecipeCommand);

    //then
    Assertions.assertEquals(NEW_DESCRIPTION, savedRecipeCommand.getDescription());
    Assertions.assertEquals(testRecipe.getId(), savedRecipeCommand.getId());
    Assertions.assertEquals(testRecipe.getCategories().size(), savedRecipeCommand.getCategories().size());
    Assertions.assertEquals(testRecipe.getIngredients().size(), savedRecipeCommand.getIngredients().size());
  }
}
