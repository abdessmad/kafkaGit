package com.numeryx.kafkaspring.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.numeryx.kafkaspring.models.PageEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service //c'est une classe qui va instancier au démarrage
public class MessageService {
    //pour faire un subscribe
    @KafkaListener(topics = "test5", groupId = "groupe-ms")
    public void onMessage(ConsumerRecord<String, String> message ) throws Exception {
        System.out.println("************");
                 PageEvent pageEvent = pageEvent(message.value())  ;
            System.out.println("key =>"+message.key());
        System.out.println(pageEvent.getPage()+","+pageEvent.getDate()+","+pageEvent.getDuration());

        System.out.println("**************");
    }
    //comme une autre solutin on va faire le service avec un string au lieu de pageEvent et on créer une méthode qui retourne une page Event

    private PageEvent pageEvent(String jsonPageEvent) throws JsonProcessingException {
        JsonMapper jsonMapper = new JsonMapper();
        PageEvent pageEvent = jsonMapper.readValue(jsonPageEvent,PageEvent.class);
        return pageEvent;
    }
}
