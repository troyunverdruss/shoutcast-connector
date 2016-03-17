package net.unverdruss.ShoutcastConnector;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class ShoutcastConnectorConfiguration extends Configuration {

    @NotEmpty
    private String shoutcastServer;

    @NotEmpty
    private String shoutcastServerPort;

    @JsonProperty
    public String getShoutcastServer() {
        return shoutcastServer;
    }

    @JsonProperty
    public void setShoutcastServer(String shoutcastServer) {
        this.shoutcastServer = shoutcastServer;
    }

    @JsonProperty
    public String getShoutcastServerPort() {
        return shoutcastServerPort;
    }

    @JsonProperty
    public void setShoutcastServerPort(String shoutcastServerPort) {
        this.shoutcastServerPort = shoutcastServerPort;
    }

}
