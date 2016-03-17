package net.unverdruss.ShoutcastConnector.support;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import net.unverdruss.ShoutcastConnector.models.ShoutcastStation;

import java.io.IOException;

public class ShoutcastStationDeserializer extends JsonDeserializer<ShoutcastStation>{
    @Override
    public ShoutcastStation deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        ObjectCodec codec = parser.getCodec();
        JsonNode node = codec.readTree(parser);

        final long id = node.get("ID").asLong();
        final String name = node.get("Name").asText();
        final String format = node.get("Format").asText();
        final long bitrate = node.get("Bitrate").asLong();
        final String genre = node.get("Genre").asText();
        final String currentTrack = node.get("CurrentTrack").asText();
        final long listeners = node.get("Listeners").asLong();
        final boolean isRadionomy = node.get("IsRadionomy").asBoolean();
        final String iceUrl = node.get("IceUrl").asText();
        final boolean isPlaying = node.get("IsPlaying").asBoolean();

        return new ShoutcastStation(id, name, format, bitrate, genre, currentTrack, listeners, isRadionomy, iceUrl, isPlaying);
    }
}