package adrian.springframework.recipeapp.services;

import adrian.springframework.recipeapp.commands.UnitOfMeasureCommand;
import adrian.springframework.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import adrian.springframework.recipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService{

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand uomToUomCommand;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasureToUnitOfMeasureCommand uomToUomCommand) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.uomToUomCommand = uomToUomCommand;
    }

    @Override
    public Set<UnitOfMeasureCommand> uomCommandsList() {

        Set<UnitOfMeasureCommand> uomList = StreamSupport.stream(unitOfMeasureRepository.findAll()
                .spliterator(),false)
                .map(uomToUomCommand::convert)
                .collect(Collectors.toSet());

        log.debug("UOM SERVICE IMPL: " + uomList.toString());
        return uomList;
    }
}
