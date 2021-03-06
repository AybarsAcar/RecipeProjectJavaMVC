package dev.aybarsacar.recipeproject.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(exclude = {"recipe"})   // to avoid circular reference error
@Entity
public class Ingredient
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String description;
  private BigDecimal amount;

  //  we are setting the fetch type to EAGER explicitly
//  even though it is the default behaviour
  @OneToOne(fetch = FetchType.EAGER)
  private UnitOfMeasure unitOfMeasure;

  @ManyToOne
  private Recipe recipe;

  public Ingredient()
  {
  }

  public Ingredient(String description, BigDecimal amount, UnitOfMeasure unitOfMeasure)
  {
    this.description = description;
    this.amount = amount;
    this.unitOfMeasure = unitOfMeasure;
  }

  public Ingredient(String description, BigDecimal amount, UnitOfMeasure unitOfMeasure, Recipe recipe)
  {
    this.description = description;
    this.amount = amount;
    this.unitOfMeasure = unitOfMeasure;
    this.recipe = recipe;
  }
}
