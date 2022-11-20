package de.olexiy.myreactivebrewery.services;

import de.olexiy.myreactivebrewery.domain.BeerStyleEnum;
import de.olexiy.myreactivebrewery.web.model.BeerDto;
import de.olexiy.myreactivebrewery.web.model.BeerPagedList;
import java.util.UUID;
import org.springframework.data.domain.PageRequest;

public interface BeerService {

  BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand);
  BeerDto getById(UUID beerId, Boolean showInventoryOnHand);
  BeerDto saveNewBeer(BeerDto beerDto);
  BeerDto updateBeer(UUID beerId, BeerDto beerDto);
  BeerDto getByUpc(String upc);
  void deleteBeerById(UUID beerId);

}
