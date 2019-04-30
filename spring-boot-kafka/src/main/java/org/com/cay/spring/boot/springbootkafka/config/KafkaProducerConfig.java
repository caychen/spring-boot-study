package org.com.cay.spring.boot.springbootkafka.config;

import com.google.common.collect.Maps;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.com.cay.spring.boot.springbootkafka.listener.KafkaMessageProducerListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.ProducerListener;

import java.util.Map;

/**
 * Author:       Caychen
 * Class:        org.com.cay.spring.boot.springbootkafka.config.KafkaProducerConfig
 * Date:         2019/4/26
 * Desc:
 */
@Configuration
@EnableKafka
public class KafkaProducerConfig {

    @Bean
    public Map<String, Object> producerProperites(){
        Map<String, Object> producerProperties = Maps.newHashMap();

        //brokers集群
        //bootstrap.servers
        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        //发送失败重试次数
        //retries
        producerProperties.put(ProducerConfig.RETRIES_CONFIG, 0);

        //所有副本都同步到数据时send方法才返回, 以此来完全判断数据是否发送成功
        //acks
        producerProperties.put(ProducerConfig.ACKS_CONFIG, "all");

        //批处理发送数据量
        //batch.size
        producerProperties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16348);

        //批处理延迟时间上限
        //linger.ms
        producerProperties.put(ProducerConfig.LINGER_MS_CONFIG, 1);

        //批处理缓冲区
        //buffer.memory
        producerProperties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);

        //key序列化
        //key.serializer
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        //value序列化
        //value.serializer
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return producerProperties;
    }

    @Bean
    public ProducerFactory producerFactory(){
        return new DefaultKafkaProducerFactory(producerProperites());
    }

    @Bean
    public ProducerListener kafkaMessageProducerListener(){
        return new KafkaMessageProducerListener();
    }

    @Bean
    public KafkaTemplate kafkaTemplate(){
        KafkaTemplate kafkaTemplate = new KafkaTemplate(producerFactory(), true);
        kafkaTemplate.setProducerListener(kafkaMessageProducerListener());
        return kafkaTemplate;
    }
}
