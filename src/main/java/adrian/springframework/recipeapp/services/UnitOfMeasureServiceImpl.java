package adrian.springframework.recipeapp.services;

import adrian.springframework.recipeapp.commands.UnitOfMeasureCommand;
import adrian.springframework.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import adrian.springframework.recipeapp.repositories.reactive.UnitOfMeasureReactiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Slf4j
@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService{

    private final UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand uomToUomCommand;

    public UnitOfMeasureServiceImpl(UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository, UnitOfMeasureToUnitOfMeasureCommand uomToUomCommand) {
        this.unitOfMeasureReactiveRepository = unitOfMeasureReactiveRepository;
        this.uomToUomCommand = uomToUomCommand;
    }

    @Override
    public Flux<UnitOfMeasureCommand> uomCommandsList() {

        return unitOfMeasureReactiveRepository
                .findAll()
                .map(uomToUomCommand::convert);

       /* Set<UnitOfMeasureCommand> uomList = StreamSupport.stream(unitOfMeasureRepository.findAll()
                .spliterator(),false)
                .map(uomToUomCommand::convert)
                .collect(Collectors.toSet());

        log.debug("UOM SERVICE IMPL: " + uomList.toString());
        return uomList;*/
    }
}
