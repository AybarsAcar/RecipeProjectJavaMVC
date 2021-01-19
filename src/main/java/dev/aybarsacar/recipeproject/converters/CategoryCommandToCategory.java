package dev.aybarsacar.recipeproject.converters;

import com.sun.istack.Nullable;
import dev.aybarsacar.recipeproject.commands.CategoryCommand;
import dev.aybarsacar.recipeproject.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category>
{
  @Synchronized
  @Nullable
  @Override
  public Category convert(CategoryCommand source)
  {
    if (source == null)
    {
      return null;
    }

    final Category category = new Category();
    category.setId(source.getId());
    category.setDescription(source.getDescription());
    return category;
  }
}