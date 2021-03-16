package kafka.demo.simple;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Order;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.math.BigDecimal;
import java.util.Properties;
import java.util.UUID;

/**
 * Create by lzm on 2021/3/16
 */
public class SimpleProducer {

    public static void main(String[] args) {

        // kafka producer 配置
        Properties props = new Properties();
        props.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.setProperty("bootstrap.servers", "10.26.37.18:9092");

        // 创建 kafka producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        for (int i = 0; i < 10; i++) {
            Order data = new Order();
            data.setUsername("liuzheming004" + "_" + i);
            data.setOrderId(UUID.randomUUID().toString());
            // 构造 Record
            try {
                ProducerRecord<String, String> record = new ProducerRecord("demo-source",
                        new ObjectMapper().writeValueAsString(data));
                producer.send(record);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        producer.close();
    }

}
