package org.codefarmer.wiremock;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

import org.junit.Rule;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

public class Test1 {

  @Rule
  public WireMockRule wireMockRule = new WireMockRule(0);

  public String stub200(String extraThing) {

    String path = "/" + getClass().getName() + '/' + extraThing;

    stubFor(get(urlEqualTo(path))
        .willReturn(aResponse().withStatus(200)
                               .withHeader("Content-Type", "application/json")
                               .withBody("{ \"message\": \"phooey\", \"severity\": 3, \"extraThing\": \"" + extraThing +  "\"}")));

    return "http://localhost:" + wireMockRule.port() + path;
  }

  @Test
  public void testThing() throws IOException, InterruptedException {

    try {

      String url1 = stub200("1");
      String url2 = stub200("2");
      String url3 = stub200("3");

      URLConnection conx = new URL(url1).openConnection();
      BufferedReader reader = new BufferedReader(new InputStreamReader((conx.getInputStream())));
      reader.readLine();
      reader.close();
    } finally {
      //	    while(true) {
      //	      Thread.sleep(1000);
      //    	}
    }
  }
}
