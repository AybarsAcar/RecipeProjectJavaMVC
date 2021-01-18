package dev.aybarsacar.recipeproject.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest
{
  Category category;

  @BeforeEach
  public void init()
  {
    category = new Category();

    category.setId(4L);
    category.setDescription("Test Description");
  }

  @Test
  void getId()
  {
    Assertions.assertEquals(4L, category.getId());
  }

  @Test
  void getDescription()
  {
    Assertions.assertEquals("Test Description", category.getDescription());
  }

  @Test
  void getRecipes()
  {
  }
}