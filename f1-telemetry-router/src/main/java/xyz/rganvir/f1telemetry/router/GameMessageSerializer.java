package xyz.rganvir.f1telemetry.router;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import xyz.rganvir.f1telemetry.router.messages.GameMessage;

public class GameMessageSerializer implements Serializer<GameMessage> {
    private static ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public byte[] serialize(String topic, GameMessage data) {
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (JsonProcessingException e) {
            throw new SerializationException("Error converting Game Message object.");
        }
    }
}
