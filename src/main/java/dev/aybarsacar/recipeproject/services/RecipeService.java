package dev.aybarsacar.recipeproject.services;

import dev.aybarsacar.recipeproject.commands.RecipeCommand;
import dev.aybarsacar.recipeproject.domain.Recipe;

import java.util.Set;

/**
 * Service layer
 */
public interface RecipeService
{
  Set<Recipe> getRecipes();

  Recipe findById(Long id);

  RecipeCommand saveRecipeCommand(RecipeCommand command);
}
