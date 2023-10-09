package xyz.rganvir.f1telemetry.router;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import xyz.rganvir.f1telemetry.router.messages.GameMessage;
import xyz.rganvir.f1telemetry.router.messages.MessageType;

import java.util.Properties;

public class Publisher {
    private static final String TOPIC_PREFIX = "f1telemetry.";
    private final KafkaProducer<String, GameMessage> producer;

    public Publisher() {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:29092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        this.producer = new KafkaProducer<>(properties);
    }

    public void publish(GameMessage message) {
        ProducerRecord<String, GameMessage> record = new ProducerRecord<>(topicOf(message.type()), message);
        producer.send(record);
        // flush data - synchronous
        producer.flush();
    }

    private String topicOf(MessageType type) {
        return TOPIC_PREFIX + type.name().toLowerCase();
    }

    public void stop() {
        producer.close();
    }
}
