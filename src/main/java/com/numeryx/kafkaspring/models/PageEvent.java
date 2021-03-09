package com.numeryx.kafkaspring.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.kafka.clients.consumer.StickyAssignor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageEvent {
    private String page;
    private Date date;
    private int duration;
}
