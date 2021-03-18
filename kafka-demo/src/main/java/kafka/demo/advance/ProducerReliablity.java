package kafka.demo.advance;

import kafka.demo.simple.SimpleProducer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaException;

import java.util.Properties;

/**
 * Create by lzm on 2021/3/18
 */
public class ProducerReliablity {

    public static void main(String[] args) {

        Properties props = SimpleProducer.getKafkaConsumerProps();

        props.setProperty("enable.idempotence", "true"); // 此时会默认将ack设置为all
        props.setProperty("transaction.id", "tx0001");  // 思考一下，什么是消息事务？

        KafkaProducer<String, String> producer =
                new KafkaProducer<>(props);
        ProducerRecord<String, String> record =
                new ProducerRecord<>("demo-source", "key", "value");
        try {
            producer.beginTransaction();

            for (int i = 0; i < 100; i++) {
                producer.send(record, (metadata, exception) -> {
                    if (exception != null) {
                        producer.abortTransaction();
                        throw new KafkaException(exception.getMessage() + "record: " + record);
                    }
                });
            }
            producer.commitTransaction();
        } catch (Exception e) {
            producer.abortTransaction();
        }


    }


}
