/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kafka.consumer;

import java.util.Map;

/**
 * @author Matteo Minardi
 */
public interface CustomConsumer extends AutoCloseable {

    /**
     * @return
     */
    Map<Long, String> recive();

}
