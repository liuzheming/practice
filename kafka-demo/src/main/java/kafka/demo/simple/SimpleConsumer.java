package kafka.demo.simple;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Order;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * Create by lzm on 2021/3/16
 */
public class SimpleConsumer {


    public static void main(String[] args) throws Exception {

        // kafka 配置
        Properties props = new Properties();
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("bootstrap.servers", "10.26.37.18:9092");
        // 设置 comsumer group
        props.setProperty("group.id", "group1");

        // 创建kafka consumer 实例
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        // 订阅topic
        consumer.subscribe(Arrays.asList("demo-source"));

        while (true) {

            // 拉取数据
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

            records.forEach(o -> {
                ConsumerRecord<String, String> record = (ConsumerRecord) o;
                try {
                    Order order = new ObjectMapper().readValue(record.value(), Order.class);
                    System.out.println("order : " + order);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        }

    }

}
