package kafkatest;

import java.util.Properties;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.config.SslConfigs;

public class KafkaTest
{
    public static void main(String[] args)
    {
        Properties props = new Properties();
//        props.put("bootstrap.servers", "centos7-1:9092");
        props.put("bootstrap.servers", "centos7-1:9093");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        //configure the following three settings for SSL Encryption
        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SSL");
        props.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, "/home/osboxes/certificates/truststore.jks");
        props.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG,  "Testing1234");

        // configure the following three settings for SSL Authentication
        props.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, "/home/osboxes/certificates/centos7-3.jks");
        props.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, "Testing1234");
        props.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG, "Testing1234");

        Producer<String, String> producer = new KafkaProducer<>(props);
        for (int i = 0; i < 100; i++)
            producer.send(new ProducerRecord<String, String>("grb-test", Integer.toString(i), "SSL " + Integer.toString(i)));

        producer.close();
        
    }
}
