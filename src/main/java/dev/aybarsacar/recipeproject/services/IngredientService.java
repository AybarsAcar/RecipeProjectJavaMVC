package dev.aybarsacar.recipeproject.services;

import dev.aybarsacar.recipeproject.commands.IngredientCommand;

public interface IngredientService
{
  IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

  IngredientCommand saveIngredientCommand(IngredientCommand command);
}
