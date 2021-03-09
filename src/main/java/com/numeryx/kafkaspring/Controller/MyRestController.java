package com.numeryx.kafkaspring.Controller;

import com.numeryx.kafkaspring.models.PageEvent;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;

@RestController
public class MyRestController  {
    //@Autowired c'est mieux de faire l'injection avec le constructeur que avec l'annotation

    private KafkaTemplate<String,PageEvent > kafkaTemplate;

    public MyRestController(KafkaTemplate<String, PageEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /*@GetMapping("/send/{message}/{topic}")
    public String send(@PathVariable String message,@PathVariable String topic){
        kafkaTemplate.send(topic,"key"+message.length(),message);
        return "Message Sent ... ";

    }*/

    @GetMapping("/send/{page}/{topic}")
    public String send(@PathVariable String page,@PathVariable String topic){
        PageEvent pageEvent = new PageEvent(page, new Date(),new Random().nextInt(1000));
        kafkaTemplate.send(topic,"key"+pageEvent.getPage(),pageEvent);
        return "Message Sent ... ";

    }
}
