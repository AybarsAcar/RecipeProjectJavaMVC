package dev.aybarsacar.recipeproject.services;

import dev.aybarsacar.recipeproject.commands.IngredientCommand;
import dev.aybarsacar.recipeproject.converters.IngredientCommandToIngredient;
import dev.aybarsacar.recipeproject.converters.IngredientToIngredientCommand;
import dev.aybarsacar.recipeproject.domain.Ingredient;
import dev.aybarsacar.recipeproject.domain.Recipe;
import dev.aybarsacar.recipeproject.repositories.RecipeRepository;
import dev.aybarsacar.recipeproject.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService
{
  private final IngredientToIngredientCommand ingredientToIngredientCommand;
  private final IngredientCommandToIngredient ingredientCommandToIngredient;
  private final RecipeRepository recipeRepository;
  private final UnitOfMeasureRepository unitOfMeasureRepository;

  public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, IngredientCommandToIngredient ingredientCommandToIngredient, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository)
  {
    this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    this.ingredientCommandToIngredient = ingredientCommandToIngredient;
    this.recipeRepository = recipeRepository;
    this.unitOfMeasureRepository = unitOfMeasureRepository;
  }

  @Override
  public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId)
  {
    Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
    if (recipeOptional.isEmpty()) throw new RuntimeException("Recipe Not Found");

    Recipe recipe = recipeOptional.get();

    Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
        .filter(ingredient -> ingredient.getId().equals(ingredientId))
        .map(ingredientToIngredientCommand::convert)
        .findFirst();

    if (ingredientCommandOptional.isEmpty()) throw new RuntimeException("Ingredient Not Found");

    return ingredientCommandOptional.get();
  }

  @Override
  @Transactional
  public IngredientCommand saveIngredientCommand(IngredientCommand command)
  {
    Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

    if (recipeOptional.isEmpty())
    {
      log.error("Recipe Not Found: " + command.getRecipeId());
      return new IngredientCommand();
    }
    else
    {
      Recipe recipe = recipeOptional.get();

      Optional<Ingredient> ingredientOptional = recipe
          .getIngredients()
          .stream()
          .filter(ingredient -> ingredient.getId().equals(command.getId()))
          .findFirst();

      if (ingredientOptional.isPresent())
      {
        Ingredient ingredientFound = ingredientOptional.get();
        ingredientFound.setDescription(command.getDescription());
        ingredientFound.setAmount(command.getAmount());
        ingredientFound.setUnitOfMeasure(unitOfMeasureRepository
            .findById(command.getUnitOfMeasure().getId())
            .orElseThrow(() -> new RuntimeException("UoM is Not Found")));
      }
      else
      {
//        add the new ingredient
        recipe.addIngredient(ingredientCommandToIngredient.convert(command));
      }

      Recipe savedRecipe = recipeRepository.save(recipe);

      return ingredientToIngredientCommand.convert(savedRecipe.getIngredients().stream()
          .filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
          .findFirst()
          .get());
    }
  }
}
