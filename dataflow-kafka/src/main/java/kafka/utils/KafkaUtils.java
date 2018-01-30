/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kafka.utils;

import java.util.Properties;
import java.util.UUID;
import kafka.admin.AdminUtils;
import kafka.admin.RackAwareMode;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;

/**
 * @author Matteo Minardi
 */
public class KafkaUtils {
    
    public final static String BOOTSTRAP_SERVER = "localhost:9092";
    private static ZookeperHelper INSTANCE;
    
    public static ZookeperHelper getZkInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ZookeperHelper();
        }
        return INSTANCE;
    }

    public static boolean createTopic(String topicName, int numPartitions, int replFactor) {
        ZookeperHelper zkHelper = KafkaUtils.getZkInstance();
        if (topicExists(topicName)) {
            return false;
        }
        AdminUtils.createTopic(zkHelper.getZkUtils(), topicName, numPartitions, replFactor, new Properties(), RackAwareMode.Disabled$.MODULE$);
        return true;
    }
    
    public static boolean deleteTopic(String topicName) {
        ZookeperHelper zkHelper = KafkaUtils.getZkInstance();
        if (!topicExists(topicName)) {
            return false;
        }
        AdminUtils.deleteTopic(zkHelper.getZkUtils(), topicName);
        return true;
    }
    
    public static boolean topicExists(String topicName) {
        ZookeperHelper zkHelper = KafkaUtils.getZkInstance();
        return AdminUtils.topicExists(zkHelper.getZkUtils(), topicName);
    }
    
    public static String getUniqueId() {
        return UUID.randomUUID().toString();
    }
    
    public static class ZookeperHelper {

        public static final String ZOOKEEPER_URL = "localhost:2181";

        ZkConnection zkConnection;
        ZkClient zkClient;
        ZkUtils zkUtils;

        public ZookeperHelper() {
            zkClient = new ZkClient(ZOOKEEPER_URL, 10000, 10000, ZKStringSerializer$.MODULE$);
            zkConnection = new ZkConnection(ZOOKEEPER_URL, 100000);
            zkUtils = new ZkUtils(zkClient, zkConnection, false);
        }

        public ZkConnection getZkConnection() {
            return zkConnection;
        }

        public ZkClient getZkClient() {
            return zkClient;
        }

        public ZkUtils getZkUtils() {
            return zkUtils;
        }
        
    }

}
