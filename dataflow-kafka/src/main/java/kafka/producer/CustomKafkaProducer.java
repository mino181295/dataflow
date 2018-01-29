package kafka.producer;

import java.util.UUID;
import org.apache.kafka.clients.producer.*;
import kafka.utils.KafkaFactory;

/**
 * @author Matteo Minardi
 */
public class CustomKafkaProducer implements CustomProducer, AutoCloseable {

    private final static String BOOTSTRAP_SERVER = "localhost:9092";
    private final String producerId;

    private final String topicName;
    private final Producer<Long, String> producer;

    public CustomKafkaProducer(String topicName) {
        this.producerId = UUID.randomUUID().toString();
        this.topicName = topicName;
        this.producer = KafkaFactory.createProducer(BOOTSTRAP_SERVER, producerId);
    }

    @Override
    public void emit(String message) throws Exception {
        final ProducerRecord<Long, String> record = new ProducerRecord<>(this.topicName, System.currentTimeMillis(), message);
        producer.send(record);
    }

    @Override
    public void close() throws Exception {
        this.producer.flush();
        this.producer.close();
    }
    
}
