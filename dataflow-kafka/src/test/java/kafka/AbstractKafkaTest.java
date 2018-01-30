/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kafka;

import kafka.utils.KafkaUtils;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Matteo Minardi
 */
public class AbstractKafkaTest {
    
    public static final String TOPIC_TEST_NAME = "testtopic";
    
    @BeforeClass
    public static void setupTopics() {
        System.out.println("Topic name : " + TOPIC_TEST_NAME);
        KafkaUtils.createTopic(TOPIC_TEST_NAME, 1, 1);
    }
    
    @Test
    public void topicExists(){
        assertTrue(KafkaUtils.topicExists(TOPIC_TEST_NAME));
    }
    
    @After
    public void disposeTopics() {
        //assertTrue(KafkaUtils.deleteTopic(TOPIC_TEST_NAME));
    }
        
}
