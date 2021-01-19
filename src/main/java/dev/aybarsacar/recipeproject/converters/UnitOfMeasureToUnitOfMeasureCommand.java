package dev.aybarsacar.recipeproject.converters;

import dev.aybarsacar.recipeproject.commands.UnitOfMeasureCommand;
import dev.aybarsacar.recipeproject.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand>
{
  @Synchronized
  @Nullable
  @Override
  public UnitOfMeasureCommand convert(UnitOfMeasure unitOfMeasure) {

    if (unitOfMeasure != null) {
      final UnitOfMeasureCommand uomc = new UnitOfMeasureCommand();
      uomc.setId(unitOfMeasure.getId());
      uomc.setDescription(unitOfMeasure.getDescription());
      return uomc;
    }
    return null;
  }
}
