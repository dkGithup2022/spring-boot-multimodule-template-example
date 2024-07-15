package com.dankim.project.queue.queue.client.publisher.api;

public interface EventCountWriter {

    /**
     * mapName 과 key 를 넣어서 키에 해당하는 count 를 1 증가 시킵니다.
     * keyName 에 해당하는 key 가 없는 경우, map.put(keyName,1) 을 수행합니다.
     *
     * [exception]
     * NotFoundException : mapName 에 해당하는 map 이 없는 경우, not found exception 을 반환합니다.
     *
     *
     * @param mapName : count 증가를 위한 redis map |  ex) TECH_ARTICLE_COUNT_MAP
     * @param keyName : count 증가를 위한 각각의 키 값들 | ex) 아티클 식별자 TECH_ARTICLE_100 ,  {ArticleType::name}_{Article::getId()}
     */
    void publishCountUpEvent(String mapName, String keyName);
}

