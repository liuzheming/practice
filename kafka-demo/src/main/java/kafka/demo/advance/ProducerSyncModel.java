package kafka.demo.advance;

import kafka.demo.simple.SimpleProducer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * Create by lzm on 2021/3/18
 */
public class ProducerSyncModel {


    public static void main(String[] args) {
        Properties props = SimpleProducer.getKafkaConsumerProps();
        props.put("linger.ms", "1");
        props.put("batch.size", "10240");

        KafkaProducer<String, String> producer =
                new KafkaProducer<>(props);

        ProducerRecord<String, String> record =
                new ProducerRecord<>("demo-source", "key", "value");

        // 异步发送方法1
        producer.send(record, (metadata, exception) -> {
            if (exception == null) System.out.println("record: " + record);
        });

        // 异步发送方法2
        producer.send(record);

    }

}
