package com.cny.springkafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: TODO
 * @author: geduo
 * @date: 2023年03月16日 16:49
 */

@Configuration
public class KafkaInitialConfiguration {
    // 创建一个名为testtopic的Topic并设置分区数为8，分区副本数为2
    @Bean
    public NewTopic initialTopic() {
        return new NewTopic("topic1",3, (short) 2 );
    }

    // 如果要修改分区数，只需修改配置值重启项目即可
    // 修改分区数并不会导致数据的丢失，但是分区数只能增大不能减小
   /* @Bean
    public NewTopic updateTopic() {
        return new NewTopic("testtopic",10, (short) 2 );
    }*/
}

