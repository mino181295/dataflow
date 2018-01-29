package kafka.consumer;

import java.util.Collections;
import java.util.UUID;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import kafka.utils.KafkaFactory;


/**
 *
 * @author Matteo Minardi
 */
public class CustomKafkaConsumer implements AutoCloseable, CustomConsumer {

    private final static String BOOTSTRAP_SERVER = "localhost:9092";
    private final String consumerId;
    private final String topicName;
    private final String groupId;
    private final long pollTime;
    private final Consumer<Long, String> consumer;

    public CustomKafkaConsumer(String topicName, String groupId, long pollTime) {
        this.consumerId = UUID.randomUUID().toString();
        this.topicName = topicName;
        this.groupId = groupId;
        this.consumer = KafkaFactory.createConsumer(BOOTSTRAP_SERVER, groupId);

        if (pollTime < 0) {
            throw new IllegalArgumentException();
        }
        this.consumer.subscribe(Collections.singletonList(topicName));
        this.pollTime = pollTime;
    }


    @Override
    public void close() throws Exception {
        this.consumer.close();
    }

    @Override
    public void recive() throws Exception {

        ConsumerRecords<Long, String> records = consumer.poll(this.pollTime);
        records.forEach(record -> {
            System.out.printf("record [ %d ] value : %s \n", record.key(), record.value());
        });
        consumer.commitAsync();
    }

}
