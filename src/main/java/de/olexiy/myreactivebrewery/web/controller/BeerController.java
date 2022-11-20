package de.olexiy.myreactivebrewery.web.controller;

import de.olexiy.myreactivebrewery.domain.BeerStyleEnum;
import de.olexiy.myreactivebrewery.services.BeerService;
import de.olexiy.myreactivebrewery.web.model.BeerDto;
import de.olexiy.myreactivebrewery.web.model.BeerPagedList;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@RestController
public class BeerController {

  private static final Integer DEFAULT_PAGE_NUMBER = 0;
  private static final Integer DEFAULT_PAGE_SIZE = 25;

  private final BeerService beerService;

  @GetMapping(produces = { "application/json" }, path = "beer")
  public ResponseEntity<Mono<BeerPagedList>> listBeers(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
      @RequestParam(value = "pageSize", required = false) Integer pageSize,
      @RequestParam(value = "beerName", required = false) String beerName,
      @RequestParam(value = "beerStyle", required = false) BeerStyleEnum beerStyle,
      @RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand){

    if (showInventoryOnHand == null) {
      showInventoryOnHand = false;
    }

    if (pageNumber == null || pageNumber < 0){
      pageNumber = DEFAULT_PAGE_NUMBER;
    }

    if (pageSize == null || pageSize < 1) {
      pageSize = DEFAULT_PAGE_SIZE;
    }

    BeerPagedList beerList = beerService.listBeers(beerName, beerStyle, PageRequest.of(pageNumber, pageSize), showInventoryOnHand);

    return ResponseEntity.ok(Mono.just(beerList));
  }

  @GetMapping("beer/{beerId}")
  public ResponseEntity<Mono<BeerDto>> getBeerById(@PathVariable("beerId") UUID beerId,
      @RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand){
    if (showInventoryOnHand == null) {
      showInventoryOnHand = false;
    }

    return ResponseEntity.ok(Mono.just(beerService.getById(beerId, showInventoryOnHand)));
  }

  @GetMapping("beerUpc/{upc}")
  public ResponseEntity<Mono<BeerDto>> getBeerByUpc(@PathVariable("upc") String upc){
    return ResponseEntity.ok(Mono.just(beerService.getByUpc(upc)));
  }

  @PostMapping(path = "beer")
  public ResponseEntity<Void>  saveNewBeer(@RequestBody @Validated BeerDto beerDto){

    BeerDto savedBeer = beerService.saveNewBeer(beerDto);

    return ResponseEntity
        .created(UriComponentsBuilder
            .fromHttpUrl("http://api.springframework.guru/api/v1/beer/" + savedBeer.getId().toString())
            .build().toUri())
        .build();
  }

  @PutMapping("beer/{beerId}")
  public ResponseEntity<Void>  updateBeerById(@PathVariable("beerId") UUID beerId, @RequestBody @Validated BeerDto beerDto){
    beerService.updateBeer(beerId, beerDto);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("beer/{beerId}")
  public ResponseEntity<Void> deleteBeerById(@PathVariable("beerId") UUID beerId){

    beerService.deleteBeerById(beerId);
    return ResponseEntity.ok().build();
  }

}
