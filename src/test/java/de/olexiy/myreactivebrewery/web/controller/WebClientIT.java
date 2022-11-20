package de.olexiy.myreactivebrewery.web.comtroller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

public class WebClientIT {

  public static final String BASE_URL = "http://localhost:8080";

  WebClient webClient;

  @BeforeEach
  void setUp() {

  }

  @Test
  void testListBeers() throws InterruptedException {


  }
}
