# 멀티모듈 템플릿

---

## 소개

이 문서는 이전 프로젝트에서 사용한 Gradle Multi Module 구조를 예시와 함께 설명합니다. 해당 프로젝트는 게시글과 유저에 대한 CUD(Create, Update, Delete) 기능을 포함하고
있습니다.

주요 모듈은 다음과 같습니다:

- **web-api**: 배포될 Spring Boot 애플리케이션 모듈 (api server)
- **core-domain**: 도메인 제약사항 및 도메인 스펙을 정의한 모듈. 테이블 구조와 관계없이 도메인 객체에 대한 API를 외부로 노출합니다.
- **core-infra-rdms**: 데이터베이스 테이블 스펙과 데이터베이스 접근을 위한 쿼리 모음

기타 기능 모듈은 다음과 같습니다:

- **queue-client**: 비동기 요청을 수행하기 위한 큐 접근 라이브러리. 해당 레포지토리에서는 인터페이스와 자료형만 정의합니다.
- **project_commons**: 프로젝트 내 공통적으로 사용되는 값 객체, enum, exception 모음

추가적으로 사용할 수 있는 예시 모듈:

- **redis-client**: Redis 클라이언트와 구성, 분산 락과 같은 공통 기능을 모아놓은 라이브러리
- **es-client**: Elasticsearch 클라이언트와 구성, 인덱스 관련 API 모음
- **feign-client**: Spring Boot Feign에 대한 구성 및 공통으로 사용할 수 있는 커스텀 인자 모음 라이브러리

---

## 기반이 되는 책

- **도메인 주도 설계 철저 입문**: [Yes24 링크](https://m.yes24.com/Goods/Detail/93384475)
- **디자인 패턴의 아름다움: 객체지향 패러다임부터 설계 원칙**: [교보문고 링크](https://product.kyobobook.co.kr/detail/S000202093794)

위의 두 책에서 많은 영향을 받았습니다.

또한, 제미니 유튜브의 문답 부분도 많은 도움이 되었습니다: [제미니 유튜브](https://www.youtube.com/@geminikims)


---



## 구조

아래 항목은 패키지/모듈에 대한 최소 구조에 대한 간략한 설명입니다.

- **:app:app-web-api**
    - 배포 되는 어플리케이션
    - 해당 예시에서는 domain 객체에 대한 crud 연산을 제공합니다.


- **:core:core-domain**
    - 도메인 규칙과 스펙, 무결성을 지키기 위한 api 가 포함됩니다.
    - infra layer 에 대한 api 를 사용하여 infra layer 의 사용을 추상화 합니다.
    - 주로 특정 도메인 객체에 대한 reader, writer, validator api 를 제공합니다.
    - 복잡한 연산을 별도 클래스로 빼서 제공하는 경우 ~~~Processor 네이밍을 사용하는 것을 추천합니다.


- **:core:core-storage**
    - 실제 RDMS 저장되는 Entity 와 쿼리에 대한 선언, 그에 대한 api 의 모음입니다.


- **Common**
    - 공통으로 쓰이는 enum, exception, value object에 대한 스펙의 모음입니다.

<br/>

---

## 추천하는 컨벤션

### 모듈에서 공개할 기능은 인터페이스로 명확히 하기.

협업을 쉽게하는 팁 중 하나입니다.

모듈을 제작할 때 모듈의 핵심기능을 api 하위의 인터페이스에 모아두세요.

해당 모듈을 사용할때, api 경로 하위의 인터페이스의 구현체만 주입 받거나, 테스트를 할때 해당 인터페이스에 대한 Fake 클래스를 만들어 테스트 하시기를 추천합니다. 

예시는 아래와 같습니다.

### 예상되는 장점 

리뷰어가 파악하기 쉬워지고, 모듈 개발과정에서 일관성 확보.

1. 협업자가 코드의 모듈의 의도와 사용법을 더 명확하게 알게 됩니다..
2. 해당 Api 를 위주로 테스트를 하는 방향으로, 테스트 공수를 줄일 수 있다.
3. 모듈 개발의 일관성 확보.


##### queue client 

아래는 redis 를 queue 로 쓰는 경우, queue 에 대한 client 를 모듈로 만든 예시입니다.

1. 패키지 구조 

![convention_02.png](images%2Fconvention_02.png)


2. 인터페이스 선언 

```java

public interface EventConsumer {

    /**
     * queue 의 항목을 from 번부터 count 갯수만큼 읽어옵니다.
     * queue 의 항목을 지우지는 않습니다.
     *
     * @param queueName
     * @param count
     * @param from
     */

    List<Event> get(String queueName, int count, int from);

    /**
     * queue 의 항목을 처음부터 count 갯수만큼 읽어오고 읽어온 항목을 지웁니다.
     * redis 를 이용해 개발하는 경우, 동시성 제어를 위해 luascript 를 활용해보세요 .
     *
     * @param queueName
     * @param count
     */
    List<Event> getAndRefresh(String queueName, int count);
}

```
---


## Domain_모듈/ 패키지

### 포함요소

1. 도메인 객체의 스펙
2. 도메인 객체에 대한 read, write 를 추상화.
3. 그외 복잡한 연산은 validator , processor 등의 suffix 를 달아 api 로 제공



##### domain 기능 api

- 모듈 구조 예시

![convention_01.png](images%2Fconvention_01.png)


- 코드 예시

```java

public interface ArticleWriter {

  /**
   * Updates an article and returns the updated article.
   *
   * @param article the article to be updated
   * @return the updated article
   * @throws NotFoundException if the article does not exist
   */
  Article update(Article article);

  /**
   * Saves a new article and returns the saved article with its ID.
   *
   * @param article the article to be saved
   * @return the saved article, including its ID
   */
  Article newArticle(Article article);
}
```

---

