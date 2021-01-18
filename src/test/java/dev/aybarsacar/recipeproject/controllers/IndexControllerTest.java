package dev.aybarsacar.recipeproject.controllers;

import dev.aybarsacar.recipeproject.domain.Recipe;
import dev.aybarsacar.recipeproject.services.RecipeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class IndexControllerTest
{
  IndexController indexController;

  @Mock
  RecipeService recipeService;

  @Mock
  Model model;

  @BeforeEach
  void setUp()
  {
    MockitoAnnotations.openMocks(this);

    indexController = new IndexController(recipeService);
  }

  /**
   * Testing our API endpoints with a mock dispatcher serverlette
   * instead of a whole server
   * still a unit test
   */
  @Test
  void testMocMVC() throws Exception
  {
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

    mockMvc.perform(MockMvcRequestBuilders.get("/"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("index"));
  }

  @Test
  void getIndexPage()
  {
//    given and add 2 recipes
    Set<Recipe> recipes = new HashSet<>();
    recipes.add(new Recipe());

    Recipe recipe =  new Recipe();
    recipe.setId(2L);
    recipes.add(recipe);

//    when
    when(recipeService.getRecipes()).thenReturn(recipes);

    ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

    String viewName = indexController.getIndexPage(model);

//    then
    Assertions.assertEquals("index", viewName);

    verify(recipeService, times(1)).getRecipes();

//    make sure to call eq() which is the mockito equals to do shallow comparison on the String object
    verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());

    Set<Recipe> setInController = argumentCaptor.getValue();

    Assertions.assertEquals(2, setInController.size());
  }
}