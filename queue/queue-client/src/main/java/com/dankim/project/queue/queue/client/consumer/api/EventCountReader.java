package com.dankim.project.queue.queue.client.consumer.api;

import ch.qos.logback.core.joran.sanity.Pair;

import java.util.List;

public interface EventCountReader {

    /**
     * map 에 있는 "keyName", "count" 페어를 받아옵니다.
     * 조회 후 조회한 이력을 삭제합니다.
     *
     * @param mapName
     * @return List<{ 아티클 식별자:String }, { 카운트:Long }>
     */


    List<Pair<String, Long>> getAndRefresh(String mapName);
}
