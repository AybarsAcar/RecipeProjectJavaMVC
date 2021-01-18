package dev.aybarsacar.recipeproject.services;

import dev.aybarsacar.recipeproject.domain.Recipe;
import dev.aybarsacar.recipeproject.repositories.RecipeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

class RecipeServiceImplTest
{
  RecipeServiceImpl recipeService;

  @Mock
  RecipeRepository recipeRepository;

  @BeforeEach
  void setUp()
  {
    MockitoAnnotations.openMocks(this);
    recipeService = new RecipeServiceImpl(recipeRepository);
  }

  @Test
  void getRecipes()
  {
    Recipe recipe = new Recipe();
    HashSet<Recipe> recipesData = new HashSet<>();
    recipesData.add(recipe);

//    inject the Service with the Hashset we created as a Mock
    when(recipeRepository.findAll()).thenReturn(recipesData);

    Set<Recipe> recipes = recipeService.getRecipes();

    Assertions.assertEquals(1, recipes.size());

//    Make sure the findAll() method on the Repository called only once
    verify(recipeRepository, times(1)).findAll();
  }
}