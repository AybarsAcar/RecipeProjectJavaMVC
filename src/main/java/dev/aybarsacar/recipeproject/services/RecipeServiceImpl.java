package dev.aybarsacar.recipeproject.services;

import dev.aybarsacar.recipeproject.domain.Recipe;
import dev.aybarsacar.recipeproject.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService
{
  private final RecipeRepository recipeRepository;

  public RecipeServiceImpl(RecipeRepository recipeRepository)
  {
    this.recipeRepository = recipeRepository;
  }

  @Override
  public Set<Recipe> getRecipes()
  {
    Set<Recipe> recipes = new HashSet<>();

    recipeRepository.findAll().forEach(recipes::add);

    return recipes;
  }
}
