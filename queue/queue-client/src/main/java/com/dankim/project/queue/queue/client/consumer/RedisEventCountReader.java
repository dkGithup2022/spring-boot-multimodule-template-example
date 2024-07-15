package com.dankim.project.queue.queue.client.consumer;

import ch.qos.logback.core.joran.sanity.Pair;
import com.dankim.project.queue.queue.client.consumer.api.EventCountReader;

import java.util.List;

public class RedisEventCountReader implements EventCountReader {
    @Override
    public List<Pair<String, Long>> getAndRefresh(String mapName) {
        /*DO IMPLEMENTATION*/
        return null;
    }
}
