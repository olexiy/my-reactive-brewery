package de.olexiy.myreactivebrewery.web.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import de.olexiy.myreactivebrewery.bootstrap.BeerLoader;
import de.olexiy.myreactivebrewery.services.BeerService;
import de.olexiy.myreactivebrewery.web.model.BeerDto;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(BeerController.class)
class BeerControllerTest {

  @Autowired
  WebTestClient webTestClient;

  @MockBean
  BeerService beerService;

  BeerDto validBeer;

 @BeforeEach
  void setUp(){
   validBeer = BeerDto.builder()
       .beerName("Test beer")
       .beerStyle("PALE_ALE")
       .upc(BeerLoader.BEER_1_UPC)
       .build();

  }

  @Test
  void getBeerById() {
    UUID beerID = UUID.randomUUID();
    given(beerService.getById(any(), any())).willReturn(validBeer);

    webTestClient.get()
        .uri("/api/v1/beer/"+beerID)
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isOk()
        .expectBody(BeerDto.class)
        .value(beerDto -> beerDto.getBeerName(), equalTo(validBeer.getBeerName()));

  }
}
