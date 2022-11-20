package de.olexiy.myreactivebrewery.web.mapper;

import de.olexiy.myreactivebrewery.domain.Beer;
import de.olexiy.myreactivebrewery.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

  BeerDto beerToBeerDto(Beer beer);

  BeerDto beerToBeerDtoWithInventory(Beer beer);

  Beer beerDtoToBeer(BeerDto dto);
}
