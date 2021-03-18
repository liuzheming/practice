package kafka.demo.advance;

import kafka.demo.simple.SimpleProducer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * Create by lzm on 2021/3/18
 */
public class ProduerOrdered {


    public static void main(String[] args) {

        Properties props = SimpleProducer.getKafkaConsumerProps();



        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        ProducerRecord<String, String> record =
                new ProducerRecord<>("demo-source", "key", "value");

        // 同步发送
        producer.send(record);
        producer.flush();

    }

}
