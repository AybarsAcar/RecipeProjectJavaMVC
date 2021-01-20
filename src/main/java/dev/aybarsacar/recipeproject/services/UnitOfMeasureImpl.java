package dev.aybarsacar.recipeproject.services;

import dev.aybarsacar.recipeproject.commands.UnitOfMeasureCommand;
import dev.aybarsacar.recipeproject.converters.UnitOfMeasureToUnitOfMeasureCommand;
import dev.aybarsacar.recipeproject.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UnitOfMeasureImpl implements UnitOfMeasureService
{
  private final UnitOfMeasureRepository unitOfMeasureRepository;
  private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

  public UnitOfMeasureImpl(UnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand)
  {
    this.unitOfMeasureRepository = unitOfMeasureRepository;
    this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
  }

  @Override
  public Set<UnitOfMeasureCommand> listAllUoms()
  {
    return StreamSupport.stream(unitOfMeasureRepository.findAll()
        .spliterator(), false)
        .map(unitOfMeasureToUnitOfMeasureCommand::convert)
        .collect(Collectors.toSet());
  }
}
