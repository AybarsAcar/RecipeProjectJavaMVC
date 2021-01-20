package dev.aybarsacar.recipeproject.services;

import dev.aybarsacar.recipeproject.commands.UnitOfMeasureCommand;
import dev.aybarsacar.recipeproject.converters.UnitOfMeasureToUnitOfMeasureCommand;
import dev.aybarsacar.recipeproject.domain.UnitOfMeasure;
import dev.aybarsacar.recipeproject.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class UnitOfMeasureImplTest
{
  UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();
  UnitOfMeasureService service;

  @Mock
  UnitOfMeasureRepository repository;

  @BeforeEach
  void setUp()
  {
    MockitoAnnotations.openMocks(this);
    service = new UnitOfMeasureImpl(repository, unitOfMeasureToUnitOfMeasureCommand);
  }

  @Test
  void listAllUoms()
  {
//    given
    Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();
    UnitOfMeasure uom1 = new UnitOfMeasure();
    uom1.setId(1L);
    unitOfMeasures.add(uom1);

    UnitOfMeasure uom2 = new UnitOfMeasure();
    uom2.setId(2L);
    unitOfMeasures.add(uom2);

//    when
    Set<UnitOfMeasureCommand> commands = service.listAllUoms();
    verify(repository, times(1)).findAll();
  }
}