package adrian.springframework.recipeapp.services;

import adrian.springframework.recipeapp.commands.UnitOfMeasureCommand;
import reactor.core.publisher.Flux;

public interface UnitOfMeasureService {
    Flux<UnitOfMeasureCommand> uomCommandsList();
}
