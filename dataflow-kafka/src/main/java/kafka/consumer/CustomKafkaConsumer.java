package kafka.consumer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import kafka.utils.KafkaFactory;
import kafka.utils.KafkaUtils;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;

/**
 * @author Matteo Minardi
 */
public class CustomKafkaConsumer implements CustomConsumer {

    private final String consumerId;
    private final String topicName;
    private final long pollTime;
    private final Consumer<Long, String> consumer;

    public CustomKafkaConsumer(String topicName, long pollTime) {
        this.consumerId = UUID.randomUUID().toString();
        this.topicName = topicName;
        
        String randomGroupId = KafkaUtils.getUniqueId();
        this.consumer = KafkaFactory.createConsumer(KafkaUtils.BOOTSTRAP_SERVER, randomGroupId);

        if (pollTime < 0) {
            throw new IllegalArgumentException();
        }
        this.consumer.subscribe(Collections.singletonList(this.topicName));
        this.pollTime = pollTime;
    }

    @Override
    public Map<Long, String> recive() {
        final Map<Long, String> res = new HashMap<>();
        ConsumerRecords<Long, String> records = consumer.poll(this.pollTime);
        records.forEach( r -> {res.put(r.key(), r.value());} );
        return res;
    }
    
    public Consumer<Long, String> getKafkaConsumer() {
        return this.consumer;
    }
    
    @Override
    public void close() {
        this.consumer.close();
    }

}
