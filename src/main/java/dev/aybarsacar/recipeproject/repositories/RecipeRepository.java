package dev.aybarsacar.recipeproject.repositories;

import dev.aybarsacar.recipeproject.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long>
{
}
