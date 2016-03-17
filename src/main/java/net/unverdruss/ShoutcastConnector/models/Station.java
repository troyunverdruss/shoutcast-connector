package net.unverdruss.ShoutcastConnector.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Station {
    private long id;
    private String url;

    public Station() {}

    public Station(long id, String url) {
        this.id = id;
        this.url = url;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
