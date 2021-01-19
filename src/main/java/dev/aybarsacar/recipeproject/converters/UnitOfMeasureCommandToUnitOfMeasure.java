package dev.aybarsacar.recipeproject.converters;

import dev.aybarsacar.recipeproject.commands.UnitOfMeasureCommand;
import dev.aybarsacar.recipeproject.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure>
{
  @Synchronized
  @Nullable
  @Override
  public UnitOfMeasure convert(UnitOfMeasureCommand source)
  {
    if (source == null)
    {
      return null;
    }

    final UnitOfMeasure uom = new UnitOfMeasure();
    uom.setId(source.getId());
    uom.setDescription(source.getDescription());
    return uom;
  }
}
