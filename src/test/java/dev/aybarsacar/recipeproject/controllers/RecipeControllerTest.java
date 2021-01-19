package dev.aybarsacar.recipeproject.controllers;

import dev.aybarsacar.recipeproject.domain.Recipe;
import dev.aybarsacar.recipeproject.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class RecipeControllerTest
{
  @Mock
  RecipeService recipeService;

  RecipeController controller;

  @BeforeEach
  void setUp()
  {
    MockitoAnnotations.openMocks(this);

    controller = new RecipeController(recipeService);
  }

  @Test
  void showById() throws Exception
  {
    Recipe recipe = new Recipe();
    recipe.setId(1L);

    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    when(recipeService.findById(anyLong())).thenReturn(recipe);

    mockMvc.perform(MockMvcRequestBuilders.get("/recipe/show/1"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("recipe/show"));
  }
}