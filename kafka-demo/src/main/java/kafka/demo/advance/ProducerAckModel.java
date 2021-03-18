package kafka.demo.advance;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Create by lzm on 2021/3/18
 */
public class ProducerAckModel {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // kafka producer 配置
        Properties props = new Properties();
        props.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.setProperty("bootstrap.servers", "10.26.37.18:9092");

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);

        ProducerRecord<String, String> record =
                new ProducerRecord<>("demo-source", "key", "value");

        Future<RecordMetadata> future = producer.send(record);
        Object o = future.get();
        producer.flush();

    }


}
