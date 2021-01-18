package dev.aybarsacar.recipeproject.domain;

import lombok.*;

import javax.persistence.*;

/**
 * Notes table
 * uses project Lombok Data for its getter setter toString equalsAndHashCode and requiredArgsConstructor
 */
@Data
@EqualsAndHashCode(exclude = {"recipe"})   // to avoid circular reference error
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

}
