package org.codefarmer.wiremock;

import org.junit.ClassRule;
import org.junit.Test;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

public class Test1
{

  @ClassRule
  public static WireMockClassRule wireMockRule = new WireMockClassRule(0);

  public String stub500() {

    String path = "/" + getClass().getName();

    stubFor(get(urlEqualTo(path))
        .willReturn(aResponse().withStatus(200)
            .withHeader("Content-Type", "application/json")
		    .withBody("{ \"message\": \"phooey\", \"severity\": 3}")));

    return "http://localhost:" + wireMockRule.port() + path;

  }

  @Test
  public void testThing() throws IOException, InterruptedException {

    try {
      String url = stub500();
      URLConnection conx = new URL(url).openConnection();
      BufferedReader reader = new BufferedReader(new InputStreamReader((conx.getInputStream())));
      reader.readLine();
      reader.close();
    }
    finally {
//	    while(true) {
//	      Thread.sleep(1000);
//    	}
    }

  }

}
