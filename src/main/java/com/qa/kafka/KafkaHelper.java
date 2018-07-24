package com.qa.kafka;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;

/**
 * Created by flyingway on 2018/7/16.
 */
public class KafkaHelper {

    public static void sendMessage(String message,String kafkaBrokers,String topicName){
        Properties props = new Properties();
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        if(kafkaBrokers==null||kafkaBrokers.equals("")){
            props.put("metadata.broker.list", "11.251.209.101:9092,11.251.209.164:9092");
        }else {
            props.put("metadata.broker.list", kafkaBrokers);
        }
        String topic = "";
        if (topicName==null||topicName.equals("")){
            topic = "machineInfo";
        }else {
            topic = topicName;
        }
        Producer<String, String> producer = new Producer<String, String>(new ProducerConfig(props));

        try{
            producer.send(new KeyedMessage<String, String>(topic,message));

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        producer.close();
    }
}
