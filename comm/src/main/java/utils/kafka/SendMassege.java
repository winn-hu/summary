package utils.kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

public class SendMassege {
	
	public static void send(String topic,String jsonStr){
		Properties props = SecurityUtils.getSecurityProperties();
		KafkaProducer<String, String> producer = new KafkaProducer<>(props);
		producer.send(new ProducerRecord<>(topic, jsonStr),new Callback() {
            public void onCompletion(RecordMetadata metadata, Exception e) {
                if(e != null) {
                   e.printStackTrace();
                } else {
                   System.out.println("The offset of the record we just sent is: " + metadata.offset());
                }
            }
        });
		producer.flush();
		producer.close();
	}
}
