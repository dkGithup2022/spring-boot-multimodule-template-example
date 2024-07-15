package com.dankim.project.queue.queue.client.event;

import java.time.LocalDateTime;

public record Event(
        EventType type,
        LocalDateTime published,
        EventSender eventSender,
        EventReceiver eventReceiver
) {
}
