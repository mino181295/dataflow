/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kafka.consumer;

import java.util.Map;
import org.apache.kafka.common.errors.WakeupException;

/**
 *
 * @author Matteo Minardi
 */
public abstract class AbstractKafkaConsumerAsync extends CustomKafkaConsumer implements Runnable {

    public AbstractKafkaConsumerAsync(String topicName, long pollTime) {
        super(topicName, pollTime);
    }

    public abstract void processRecords(Map<Long, String> records);

    @Override
    public void run() {
        while (true) {
            Map<Long, String> records = this.recive();
            processRecords(records);
        }
    }

}
