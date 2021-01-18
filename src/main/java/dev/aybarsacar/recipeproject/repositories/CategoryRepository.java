package dev.aybarsacar.recipeproject.repositories;

import dev.aybarsacar.recipeproject.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * this is hte Repository we specify our query methods to interact with the db
 * Repository layer
 */
public interface CategoryRepository extends CrudRepository<Category, Long>
{
  /**
   * using optional return type to avoid errors with null type returns
   *
   * @param description
   * @return
   */
  Optional<Category> findByDescription(String description);
}
