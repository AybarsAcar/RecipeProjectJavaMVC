package dev.aybarsacar.recipeproject.domain;

import javax.persistence.*;

/**
 * Notes table
 */
@Entity
public class Notes
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  private Recipe recipe;

  @Lob  // so we can store more than 255 chars which is Hibernates default
  private String recipeNotes;

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public Recipe getRecipe()
  {
    return recipe;
  }

  public void setRecipe(Recipe recipe)
  {
    this.recipe = recipe;
  }

  public String getRecipeNotes()
  {
    return recipeNotes;
  }

  public void setRecipeNotes(String recipeNotes)
  {
    this.recipeNotes = recipeNotes;
  }
}
