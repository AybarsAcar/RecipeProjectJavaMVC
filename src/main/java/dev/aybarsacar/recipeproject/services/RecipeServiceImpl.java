package dev.aybarsacar.recipeproject.services;

import dev.aybarsacar.recipeproject.commands.RecipeCommand;
import dev.aybarsacar.recipeproject.converters.RecipeCommandToRecipe;
import dev.aybarsacar.recipeproject.converters.RecipeToRecipeCommand;
import dev.aybarsacar.recipeproject.domain.Recipe;
import dev.aybarsacar.recipeproject.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService
{
  private final RecipeRepository recipeRepository;
  private final RecipeCommandToRecipe recipeCommandToRecipe;
  private final RecipeToRecipeCommand recipeToRecipeCommand;

  public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand)
  {
    this.recipeRepository = recipeRepository;
    this.recipeCommandToRecipe = recipeCommandToRecipe;
    this.recipeToRecipeCommand = recipeToRecipeCommand;
  }

  @Override
  public Set<Recipe> getRecipes()
  {
    Set<Recipe> recipes = new HashSet<>();

    recipeRepository.findAll().forEach(recipes::add);

    return recipes;
  }

  @Override
  public Recipe findById(Long id)
  {
    Optional<Recipe> recipeOptional = recipeRepository.findById(id);

    if (recipeOptional.isEmpty()) throw new RuntimeException("Recipe Not Found");

    return recipeOptional.get();
  }

  @Override
  @Transactional    // because we are going through the converters outside of the Spring Context
  public RecipeCommand saveRecipeCommand(RecipeCommand command)
  {
//    still not a hibernate object
    Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

    Recipe savedRecipe = recipeRepository.save(detachedRecipe);

    log.debug("Saved Recipe: " + savedRecipe.getId());

    return recipeToRecipeCommand.convert(savedRecipe);
  }

  /**
   * Calls the findById() and to find it in the db
   *
   * @param id
   * @return
   */
  @Override
  @Transactional
  public RecipeCommand findCommandById(Long id)
  {
    return recipeToRecipeCommand.convert(findById(id));
  }

  @Override
  public void deleteById(Long id)
  {
    recipeRepository.deleteById(id);
  }
}
