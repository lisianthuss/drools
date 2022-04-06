package com.sample.application;

import org.kie.api.runtime.KieSession;

import com.sample.config.DroolsBeanFactory;
import com.sample.model.Fact;
import com.sample.model.Result;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KeyValueMapper;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.ValueMapper;
import org.apache.kafka.streams.state.KeyValueStore;

import java.util.Arrays;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

public class Kafka_stream_processing {
    public static void main(String[] args) {
        Result result = new Kafka_stream_processing().run();
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
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-wordcount");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        final StreamsBuilder builder = new StreamsBuilder();
        builder.<String, String>stream("joy01")
                .flatMapValues(new ValueMapper<String, Iterable<String>>() {
                    @Override
                    public Iterable<String> apply(String value) {
                        return Arrays.asList(value.toLowerCase(Locale.getDefault()).split("\\W+"));
                    }
                })
                .groupBy(new KeyValueMapper<String, String, String>() {
                    @Override
                    public String apply(String key, String value) {
                        return value;
                    }
                })
                .count(Materialized.<String, Long, KeyValueStore<Bytes, byte[]>>as("counts-store"))
                .toStream()
                .to("joy02", Produced.with(Serdes.String(), Serdes.Long()));

        final Topology topology = builder.build();
        System.out.println(topology.describe());

        final KafkaStreams streams = new KafkaStreams(topology, props);
        final CountDownLatch latch = new CountDownLatch(1);

        Runtime.getRuntime().addShutdownHook(new Thread("streams-shutdown-hook") {
            @Override
            public void run() {
                streams.close();
                System.out.println("topology started");
                latch.countDown();
                System.out.println("topology terminated");
            }
        });

        try {
            streams.start();
            latch.await();
            latch.await();
        } catch (Throwable e) {
            System.exit(1);
        }

        System.exit(0);

        return result;
    }
}
