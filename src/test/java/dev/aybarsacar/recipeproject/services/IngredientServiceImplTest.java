package dev.aybarsacar.recipeproject.services;

import dev.aybarsacar.recipeproject.commands.IngredientCommand;
import dev.aybarsacar.recipeproject.converters.IngredientCommandToIngredient;
import dev.aybarsacar.recipeproject.converters.IngredientToIngredientCommand;
import dev.aybarsacar.recipeproject.converters.UnitOfMeasureCommandToUnitOfMeasure;
import dev.aybarsacar.recipeproject.converters.UnitOfMeasureToUnitOfMeasureCommand;
import dev.aybarsacar.recipeproject.domain.Ingredient;
import dev.aybarsacar.recipeproject.domain.Recipe;
import dev.aybarsacar.recipeproject.repositories.RecipeRepository;
import dev.aybarsacar.recipeproject.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class IngredientServiceImplTest
{
  private final IngredientToIngredientCommand ingredientToIngredientCommand;
  private final IngredientCommandToIngredient ingredientCommandToIngredient;

  @Mock
  RecipeRepository recipeRepository;

  @Mock
  UnitOfMeasureRepository unitOfMeasureRepository;

  IngredientService ingredientService;

  //init converters
  public IngredientServiceImplTest()
  {
    this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    this.ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
  }

  @BeforeEach
  void setUp()
  {
    MockitoAnnotations.openMocks(this);

    ingredientService = new IngredientServiceImpl(ingredientToIngredientCommand, ingredientCommandToIngredient, recipeRepository, unitOfMeasureRepository);
  }

  @Test
  void findByRecipeIdAndIngredientId()
  {
    //given
    Recipe recipe = new Recipe();
    recipe.setId(1L);

    Ingredient ingredient1 = new Ingredient();
    ingredient1.setId(1L);

    Ingredient ingredient2 = new Ingredient();
    ingredient2.setId(1L);

    Ingredient ingredient3 = new Ingredient();
    ingredient3.setId(3L);

    recipe.addIngredient(ingredient1);
    recipe.addIngredient(ingredient2);
    recipe.addIngredient(ingredient3);
    Optional<Recipe> recipeOptional = Optional.of(recipe);

    when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

    //then
    IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(1L, 3L);

    //when
    Assertions.assertEquals(Long.valueOf(3L), ingredientCommand.getId());
    Assertions.assertEquals(Long.valueOf(1L), ingredientCommand.getRecipeId());
    verify(recipeRepository, times(1)).findById(anyLong());
  }

  @Test
  void saveIngredientCommand()
  {
    //given
    IngredientCommand command = new IngredientCommand();
    command.setId(3L);
    command.setRecipeId(2L);

    Optional<Recipe> recipeOptional = Optional.of(new Recipe());

    Recipe savedRecipe = new Recipe();
    savedRecipe.addIngredient(new Ingredient());
    savedRecipe.getIngredients().iterator().next().setId(3L);

    when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
    when(recipeRepository.save(any())).thenReturn(savedRecipe);

    //when
    IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

    //then
    Assertions.assertEquals(Long.valueOf(3L), savedCommand.getId());
    verify(recipeRepository, times(1)).findById(anyLong());
    verify(recipeRepository, times(1)).save(any(Recipe.class));
  }
}