/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kafka.consumer;

import java.util.Map;

/**
 *
 * @author Matteo Minardi
 */
public class ConsoleKafkaConsumerAsync extends AbstractKafkaConsumerAsync {

    public ConsoleKafkaConsumerAsync(String topicName, long pollTime) {
        super(topicName, pollTime);
    }

    @Override
    public void processRecords(Map<Long, String> records) {
        records.forEach((key, value) -> System.out.println("Record [ " + key + " ] value [ " + value + " ]"));
    }
    
}
