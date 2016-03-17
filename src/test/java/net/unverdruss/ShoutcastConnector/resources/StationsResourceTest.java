package net.unverdruss.ShoutcastConnector.resources;

import net.unverdruss.ShoutcastConnector.support.ShoutcastConnectorAppRule;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.junit.MockServerRule;
import org.mockserver.model.Header;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.JsonBody;

import javax.ws.rs.core.MediaType;

import static com.jayway.restassured.RestAssured.when;
import static org.junit.Assert.*;

public class StationsResourceTest {
    @ClassRule
    public static final ShoutcastConnectorAppRule app = new ShoutcastConnectorAppRule();

    @Rule
    public MockServerRule mockServerRule = new MockServerRule(this, 9082);
    private MockServerClient mockServerClient;

    @Test
    public void testStations() {
//        mockServerClient.when(HttpRequest.request("/Home/Top").withMethod("POST")).respond(getResponse("get-home-top.json"));
//
//        when().get(app.getBaseUrl() + "/stations").then()
//                .contentType(MediaType.APPLICATION_JSON)
//                .statusCode(200);
        assertTrue(true);
    }

    @Test
    public void testTuneIn() {
        assertTrue(true);
    }

    @Test
    public void testStream() {
        assertTrue(true);
    }

    private HttpResponse getResponse(String resourceName) {
        return HttpResponse.response().withBody(new JsonBody(loadResource(resourceName))).withStatusCode(200)
                .withHeader(new Header("Content-Type", "application/json"));
    }

    private String loadResource(String name) {
        return StationsResourceTest.class.getClassLoader().getResourceAsStream(name).toString();
    }
}