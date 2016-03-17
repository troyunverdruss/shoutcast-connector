package net.unverdruss.ShoutcastConnector.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.unverdruss.ShoutcastConnector.support.ShoutcastStationDeserializer;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonDeserialize(using = ShoutcastStationDeserializer.class)
public class ShoutcastStation {
    private long id;
    private String name;
    private String format;
    private long bitrate;
    private String genre;
    private String currentTrack;
    private long listeners;
    private boolean isRadionomy;
    private String iceUrl;
    private boolean isPlaying;
    
    public ShoutcastStation() {}
    
    public ShoutcastStation(
            long id,
            String name,
            String format,
            long bitrate,
            String genre,
            String currentTrack,
            long listeners,
            boolean isRadionomy,
            String iceUrl,
            boolean isPlaying
    ) {
        this.id = id;
        this.name = name;
        this.format = format;
        this.bitrate = bitrate;
        this.genre = genre;
        this.currentTrack = currentTrack;
        this.listeners = listeners;
        this.isRadionomy = isRadionomy;
        this.iceUrl = iceUrl;
        this.isPlaying = isPlaying;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public long getBitrate() {
        return bitrate;
    }

    public void setBitrate(long bitrate) {
        this.bitrate = bitrate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCurrentTrack() {
        return currentTrack;
    }

    public void setCurrentTrack(String currentTrack) {
        this.currentTrack = currentTrack;
    }

    public long getListeners() {
        return listeners;
    }

    public void setListeners(long listeners) {
        this.listeners = listeners;
    }

    public boolean isRadionomy() {
        return isRadionomy;
    }

    public void setRadionomy(boolean radionomy) {
        isRadionomy = radionomy;
    }

    public String getIceUrl() {
        return iceUrl;
    }

    public void setIceUrl(String iceUrl) {
        this.iceUrl = iceUrl;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }
}