package xyz.rganvir.f1telemetry.router;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import xyz.rganvir.f1telemetry.router.messages.GameMessage;
import xyz.rganvir.f1telemetry.router.messages.MessageType;

import java.util.Properties;
import java.util.concurrent.Future;

public class Publisher {
    private static final String TOPIC_PREFIX = "f1telemetry.";
    private final KafkaProducer<String, GameMessage> producer;

    public Publisher(String bootstrapServer) {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, GameMessageSerializer.class.getName());
        properties.setProperty(ProducerConfig.METADATA_MAX_IDLE_CONFIG, "10000");
        properties.setProperty(ProducerConfig.METADATA_MAX_AGE_CONFIG, "10000");
        properties.setProperty(ProducerConfig.MAX_BLOCK_MS_CONFIG, "10000");
        this.producer = new KafkaProducer<>(properties);
    }

    public Future<RecordMetadata> publish(GameMessage message) {
        String topic = topicOf(message.type());
        ProducerRecord<String, GameMessage> record = new ProducerRecord<>(topic, message);
        return producer.send(record, (metadata, exception) -> {
            if (exception == null) {
                System.out.printf("Published to " + "topic" + " %s%n", metadata.topic());
            } else {
                System.out.println("Failed to send message. " + exception.getMessage());
            }
        });
    }

    private String topicOf(MessageType type) {
        return TOPIC_PREFIX + type.name().toLowerCase();
    }

    public void stop() {
        producer.flush();
        producer.close();
    }
}
