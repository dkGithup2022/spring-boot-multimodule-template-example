package com.dankim.project.queue.queue.client.consumer;

import com.dankim.project.queue.queue.client.consumer.api.EventConsumer;
import com.dankim.project.queue.queue.client.event.Event;

import java.util.List;

public class RedisEventConsumer implements EventConsumer {
    @Override
    public List<Event> get(String queueName, int count, int from) {
        /*DO IMPLEMENTATION*/
        return null;
    }

    @Override
    public List<Event> getAndRefresh(String queueName, int count) {
        /*DO IMPLEMENTATION*/
        return null;
    }
}
