package dev.aybarsacar.recipeproject.repositories;

import dev.aybarsacar.recipeproject.domain.UnitOfMeasure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

/**
 * This is an integration test class that's why we put the IT suffix
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
class UnitOfMeasureRepositoryIT
{
  @Autowired
  UnitOfMeasureRepository unitOfMeasureRepository;

  @BeforeEach
  void setUp()
  {
  }

  @Test
  void findByDescription()
  {
    Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

    Assertions.assertEquals("Teaspoon", unitOfMeasureOptional.get().getDescription());
  }

  @Test
  void findByDescriptionCup()
  {
    Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Cup");

    Assertions.assertEquals("Cup", unitOfMeasureOptional.get().getDescription());
  }
}