package dev.aybarsacar.recipeproject.domain;

import dev.aybarsacar.recipeproject.domain.enums.Difficulty;

import javax.persistence.*;
import java.util.Set;

/**
 * Domain Entity that represents our Recipe Table
 * Recipe is the owner of Notes, Ingredient
 */
@Entity
public class Recipe
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)   // auto generated value, specific to database
  private Long id;

  private String description;
  private Integer prepTime;
  private Integer cookTime;
  private Integer servings;
  private String source;
  private String url;
  private String directions;

  @Enumerated(value = EnumType.STRING)  // so stored as a string
  private Difficulty difficulty;

  @Lob    // so it creates the field as blob in the db
  private Byte[] image; // not good, better to add a url and an external storage like Amazon S#

  //  set up the relationship
  @OneToOne(cascade = CascadeType.ALL)
  private Notes notes;

  //  it will be stored in a property called recipe
//  recipe is the target property in the Ingredient class
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
  private Set<Ingredient> ingredients;

  @ManyToMany
  @JoinTable(name = "recipe_category",
      joinColumns = @JoinColumn(name = "recipe_id"),
      inverseJoinColumns = @JoinColumn(name = "category_id"))
  private Set<Category> categories;

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public Set<Ingredient> getIngredients()
  {
    return ingredients;
  }

  public void setIngredients(Set<Ingredient> ingredients)
  {
    this.ingredients = ingredients;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public Integer getPrepTime()
  {
    return prepTime;
  }

  public void setPrepTime(Integer prepTime)
  {
    this.prepTime = prepTime;
  }

  public Integer getCookTime()
  {
    return cookTime;
  }

  public void setCookTime(Integer cookTime)
  {
    this.cookTime = cookTime;
  }

  public Integer getServings()
  {
    return servings;
  }

  public void setServings(Integer servings)
  {
    this.servings = servings;
  }

  public String getSource()
  {
    return source;
  }

  public void setSource(String source)
  {
    this.source = source;
  }

  public String getUrl()
  {
    return url;
  }

  public void setUrl(String url)
  {
    this.url = url;
  }

  public String getDirections()
  {
    return directions;
  }

  public void setDirections(String directions)
  {
    this.directions = directions;
  }

  public Byte[] getImage()
  {
    return image;
  }

  public void setImage(Byte[] image)
  {
    this.image = image;
  }

  public Notes getNotes()
  {
    return notes;
  }

  public void setNotes(Notes notes)
  {
    this.notes = notes;
  }

  public Set<Category> getCategories()
  {
    return categories;
  }

  public void setCategories(Set<Category> categories)
  {
    this.categories = categories;
  }

  public Difficulty getDifficulty()
  {
    return difficulty;
  }

  public void setDifficulty(Difficulty difficulty)
  {
    this.difficulty = difficulty;
  }
}