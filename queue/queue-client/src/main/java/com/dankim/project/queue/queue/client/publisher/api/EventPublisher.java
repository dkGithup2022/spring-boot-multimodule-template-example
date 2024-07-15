package com.dankim.project.queue.queue.client.publisher.api;

import com.dankim.project.queue.queue.client.event.Event;

public interface EventPublisher {
    /**
     * 이벤트정보를 큐에 발행합니다.
     *
     * 이벤트 데이타 타입은 event/Event의 스펙을 확인해주세요
     *
     * @param queueName
     * @param event
     */
    void publish(String queueName, Event event);
}
