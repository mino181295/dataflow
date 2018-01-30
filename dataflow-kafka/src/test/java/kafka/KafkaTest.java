/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kafka;

import java.util.Map;
import kafka.consumer.ConsoleKafkaConsumerAsync;
import kafka.consumer.CustomConsumer;
import kafka.consumer.CustomKafkaConsumer;
import kafka.producer.CustomKafkaProducer;
import kafka.producer.CustomProducer;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author Matteo Minardi
 */
public class KafkaTest extends AbstractKafkaTest {

    @Test
    public void producerAndConsumerKafkaTest() throws Exception {
        long maxWait = 3 * 1000;
        boolean hasReceived = false;
        
        try (CustomProducer producer = new CustomKafkaProducer(TOPIC_TEST_NAME)) {
            producer.emit("KAFKA");
        }

        try (CustomConsumer consumer = new CustomKafkaConsumer(TOPIC_TEST_NAME, 100)) {
            long initialTime = System.currentTimeMillis();
            long currentTime;
            while (true) {
                Map<Long, String> res = consumer.recive();
                hasReceived = res.containsValue("KAFKA");

                currentTime = System.currentTimeMillis() - initialTime;
                if (currentTime > maxWait || hasReceived) {
                    break;
                }
            }
        }
        assertTrue(hasReceived);
    }

}
