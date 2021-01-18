package dev.aybarsacar.recipeproject.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * This class represents the categories
 * has a many to many relationship with the Recipe Entity
 */
@Data
@EqualsAndHashCode(exclude = {"recipes"})   // to avoid circular reference error
@Entity
public class Category
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String description;

  @ManyToMany(mappedBy = "categories")
  private Set<Recipe> recipes;
}
