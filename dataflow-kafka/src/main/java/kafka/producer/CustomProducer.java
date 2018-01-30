/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kafka.producer;

/**
 * @author matteo
 */
public interface CustomProducer extends AutoCloseable {
    /**
     * 
     * @param message
     * @throws Exception 
     */
    long emit(String message) throws Exception;
    
}
