package com.dankim.project.queue.queue.client.consumer.api;

import com.dankim.project.queue.queue.client.event.Event;

import java.util.List;

public interface EventConsumer {

    /**
     * queue 의 항목을 from 번부터 count 갯수만큼 읽어옵니다.
     * queue 의 항목을 지우지는 않습니다.
     *
     * @param queueName
     * @param count
     * @param from
     * @return
     */

    List<Event> get(String queueName, int count, int from);

    /**
     * queue 의 항목을 처음부터 count 갯수만큼 읽어오고 읽어온 항목을 지웁니다.
     *
     * redis 를 이용해 개발하는 경우, 동시성 제어를 위해 luascript 를 활용해보세요 .
     *
     * @param queueName
     * @param count
     * @return
     */
    List<Event> getAndRefresh(String queueName, int count);
}
