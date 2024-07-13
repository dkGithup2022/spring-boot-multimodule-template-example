# 멀티모듈 템플릿

---

## 소개

이 문서는 이전 프로젝트에서 사용한 Gradle Multi Module 구조를 예시와 함께 설명합니다. 해당 프로젝트는 게시글과 유저에 대한 CUD(Create, Update, Delete) 기능을 포함하고 있습니다.

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


---

## 구조

아래 항목은 패키지/모듈에 대한 최소 구조에 대한 간략한 설명입니다.

- **Api**
    - 배포 되는 어플리케이션
    - 아래 domain 에 대한 api 를 사용하게 됩니다.

- **Domain**
    - 도메인 규칙과 스펙, 무결성을 지키기 위한 api 가 포함됩니다.
    - infra layer 에 대한 api 를 사용하여 도메인 객체 사용에 대한 infra layer 의 사용을 추상화 합니다.

- **Infra**
    - 실제 RDMS 저장되는 Entity 와 쿼리에 대한 선언, 그에 대한 api 의 모음입니다.

- **Common**
    - 공통으로 쓰이는 enum, 값 객체에 대한 스펙의 모음입니다.
    - exception 이 포함됩니다.

## Domain_모듈/ 패키지

- 도메인 객체의 스펙
- 도메인 객체의 무결성을 확인하기 위한 api
- 도메인 객체에 대한 reposiroty

### 도메인_패키지_정형화된_포함요소

##### domain 패키지에서 노출할 요소

1. domain 에 대한 스펙

2. domain 에 대한 정형화된 api
    - 2-1) DomainReader
    - 2-2) DomainWriter
    - 2-3) DomainValidator

##### 도메인 패키지에서 사용할 함수

1. domain 객체에 대한 repository
    - infra layer를 추상화
    - 부분적으로 api 로 노출 


---

