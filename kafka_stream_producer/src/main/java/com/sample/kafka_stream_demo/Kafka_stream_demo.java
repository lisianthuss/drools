package com.sample.kafka_stream_demo;

import org.kie.api.runtime.KieSession;
import com.sample.config.DroolsBeanFactory;
import com.sample.model.Fact;
import com.sample.model.Result;

import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import java.util.Properties;

public class Kafka_stream_demo {
    public static void main(String[] args) {
        Result result = new Kafka_stream_demo().run();
        System.out.println(result.getValue());
        result.getFacts()
            .stream()
            .forEach(System.out::println);
    }

    public Result run() {
        Result result = new Result();
        /*
        KieSession ksession = new DroolsBeanFactory().getKieSession();
        ksession.setGlobal("result", result);
        ksession.insert(new Fact("Joy", " "));

        ksession.fireAllRules();
        */

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);   
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class); 

        Producer<String, String> producer = new KafkaProducer<>(props);
        try {
            producer.send(new ProducerRecord<String, String>("joy01", "Hello Joy"));
            producer.send(new ProducerRecord<String, String>("joy01", "I love you"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }

        return result;
    }
}
