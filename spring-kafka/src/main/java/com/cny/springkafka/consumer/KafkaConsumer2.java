package com.cny.springkafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @Description: 用于测试不同springbean下，同组的2个消费者消费时使用的线程。
 * @author: geduo
 * @date: 2023年03月23日 10:35
 */
//@Component
public class KafkaConsumer2 {
    // 消费监听
//    @KafkaListener(topics = {"topic1"},groupId = "gp1")
    public void onMessage1(ConsumerRecord<?, ?> record){
        // 消费的哪个topic、partition的消息,打印出消息内容
        System.out.println("c3简单消费："+record.topic()+"-"+record.partition()+"-"+record.value()+",threadName:"+Thread.currentThread().getName());
    }

}
