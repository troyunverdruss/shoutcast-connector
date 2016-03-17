package net.unverdruss.ShoutcastConnector.resources;

import net.unverdruss.ShoutcastConnector.support.ShoutcastConnectorAppRule;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.MediaType;

import static com.jayway.restassured.RestAssured.when;

public class RootResourceTest {
    @ClassRule
    public static final ShoutcastConnectorAppRule app = new ShoutcastConnectorAppRule();

    @Test
    public void testRoot() {
        when().get(app.getBaseUrl()).then()
                .contentType(MediaType.TEXT_HTML)
                .statusCode(200);
    }
}