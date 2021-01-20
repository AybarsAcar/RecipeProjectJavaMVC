package dev.aybarsacar.recipeproject.services;

import dev.aybarsacar.recipeproject.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService
{
  Set<UnitOfMeasureCommand> listAllUoms();
}
