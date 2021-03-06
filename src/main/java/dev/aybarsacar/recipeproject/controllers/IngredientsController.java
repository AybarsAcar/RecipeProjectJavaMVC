package dev.aybarsacar.recipeproject.controllers;

import dev.aybarsacar.recipeproject.commands.IngredientCommand;
import dev.aybarsacar.recipeproject.services.IngredientService;
import dev.aybarsacar.recipeproject.services.RecipeService;
import dev.aybarsacar.recipeproject.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IngredientsController
{
  private final RecipeService recipeService;
  private final IngredientService ingredientService;
  private final UnitOfMeasureService unitOfMeasureService;

  public IngredientsController(RecipeService recipeService, IngredientService ingredientService,
                               UnitOfMeasureService unitOfMeasureService)
  {
    this.recipeService = recipeService;
    this.ingredientService = ingredientService;
    this.unitOfMeasureService = unitOfMeasureService;
  }

  @GetMapping
  @RequestMapping("/recipe/{recipeId}/ingredients")
  public String listIngredients(@PathVariable String recipeId, Model model)
  {
    log.debug("Getting ingredients of the recipe: " + recipeId);

//    use command object to avoid lazy laod errors in Thymeleaf
    model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));

    return "recipe/ingredient/list";
  }

  @GetMapping
  @RequestMapping("/recipe/{recipeId}/ingredients/{id}/show")
  public String showRecipeIngredient(@PathVariable String recipeId, @PathVariable String id, Model model)
  {
    model.addAttribute("ingredient",
        ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));

    return "recipe/ingredient/show";
  }

  @GetMapping
  @RequestMapping("recipe/{recipeId}/ingredient/{id}/update")
  public String updateRecipeIngredient(@PathVariable String recipeId, @PathVariable String id, Model model)
  {
    model.addAttribute("ingredient",
        ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));

    model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
    return "recipe/ingredient/ingredientform";
  }

  @GetMapping
  @RequestMapping("recipe/{recipeId}/ingredient")
  public String saveOrUpdate(@ModelAttribute IngredientCommand command)
  {
    IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

    return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
  }
}
