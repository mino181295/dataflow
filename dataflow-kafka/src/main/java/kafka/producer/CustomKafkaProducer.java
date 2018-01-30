package kafka.producer;

import java.util.UUID;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import kafka.utils.KafkaFactory;
import kafka.utils.KafkaUtils;

/**
 * @author Matteo Minardi
 */
public class CustomKafkaProducer implements CustomProducer, AutoCloseable {

    private final String producerId;

    private final String topicName;
    private final Producer<Long, String> producer;

    public CustomKafkaProducer(String topicName) {
        this.producerId = KafkaUtils.getUniqueId();
        this.topicName = topicName;
        this.producer = KafkaFactory.createProducer(KafkaUtils.BOOTSTRAP_SERVER, producerId);
    }

    @Override
    public long emit(String message) throws Exception {
        long timestamp = System.currentTimeMillis();
        final ProducerRecord<Long, String> record = new ProducerRecord<>(this.topicName, timestamp, message);
        producer.send(record);
        return timestamp;
    }

    @Override
    public void close() throws Exception {
        this.producer.flush();
        this.producer.close();
    }

}
