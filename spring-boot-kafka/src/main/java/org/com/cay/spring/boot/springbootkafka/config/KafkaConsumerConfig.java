package org.com.cay.spring.boot.springbootkafka.config;

import com.google.common.collect.Maps;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.com.cay.spring.boot.springbootkafka.listener.consumer.messagelistener.ConsumerTopicListener1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListenerContainer;

import java.util.Map;

/**
 * Author:       Caychen
 * Class:        org.com.cay.spring.boot.springbootkafka.config.KafkaConsumerConfig
 * Date:         2019/4/26
 * Desc:
 */
@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Bean
    public Map<String, Object> consumerProperites(){
        Map<String, Object> consumerProperties = Maps.newHashMap();

        //brokers集群地址
        //bootstrap.servers
        consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        //是否自动提交(默认true),Consumer 在commit offset时有两种模式：自动提交，手动提交。手动提交在前面已经说过。自动提交：是Kafka Consumer会在后台周期性的去commit。
        //enable.auto.commit
        consumerProperties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);

        //自动提交周期(默认5000ms)
        //auto.commit.interval.ms
        consumerProperties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 100);

        //心跳间隔时间，此值必须小于session.timeout.ms
        //heartbeat.interval.ms
        consumerProperties.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, 10000);

        //Consumer session 过期时间(默认10000ms)，这个值必须设置在broker configuration中的group.min.session.timeout.ms 与 group.max.session.timeout.ms之间。
        //session.timeout.ms
        consumerProperties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 15000);

        //poll消息时的最大间隔时间
        //max.poll.interval.ms
        consumerProperties.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 10000);

        //每次消费的条数，默认500
        //max.poll.records
        consumerProperties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 10);

        //消费者群组ID
        //group.id
        consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "group01");

        //
        //auto.offset.reset
        consumerProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        //key序列化
        //key.deserializer
        consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        //value序列化
        //value.deserializer
        consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return consumerProperties;
    }

    @Bean
    public ConsumerFactory consumerFactory(){
        return new DefaultKafkaConsumerFactory(consumerProperites());
    }

    @Bean(name = "batchContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, String> batchContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, String> containerFactory = new ConcurrentKafkaListenerContainerFactory<String, String>();
        containerFactory.setConsumerFactory(consumerFactory());
        containerFactory.setConcurrency(5);
        containerFactory.setBatchListener(true);//开启批量监听，在消费端必须需要使用List接收
        return containerFactory;
    }

    @Bean(name = "singleContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, String> singleContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, String> containerFactory = new ConcurrentKafkaListenerContainerFactory<String, String>();
        containerFactory.setConsumerFactory(consumerFactory());
        containerFactory.setConcurrency(5);
//        containerFactory.setBatchListener(true);//开启批量监听，在消费端必须需要使用List接收
        return containerFactory;
    }

    //====================以上通过@KafkaListener(s)来监听具体队列================================================

    //====================以下通过实现MessageListener接口来监听，需要手动绑定到容器上==============================
    @Bean
    public ConsumerTopicListener1 consumerTopicListener(){
        return new ConsumerTopicListener1();
    }

    @Bean
    public ContainerProperties containerProperties(){
        ContainerProperties properties = new ContainerProperties("topic-single");
        properties.setMessageListener(consumerTopicListener());
        return properties;
    }

    @Bean
    public MessageListenerContainer kafkaMessageListenerContainer(){
        return new KafkaMessageListenerContainer(consumerFactory(), containerProperties());
    }


    //=========================自定义Topic======================================================
    //默认由kafka自动创建的topic的分区数为1，复制因子为0
    @Bean
    public KafkaAdmin admin(){
        Map<String, Object> props = Maps.newHashMap();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        return new KafkaAdmin(props);
    }

    @Bean
    public NewTopic fooTopic(){
        return new NewTopic("topic-foo", 3, (short)1);
    }

    @Bean
    public NewTopic barTopic(){
        return new NewTopic("topic-bar", 2, (short)1);
    }

}
