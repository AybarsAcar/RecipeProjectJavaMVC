package dev.aybarsacar.recipeproject.repositories;

import dev.aybarsacar.recipeproject.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long>
{
}
