package adrian.springframework.recipeapp.services;

import adrian.springframework.recipeapp.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> uomCommandsList();
}
