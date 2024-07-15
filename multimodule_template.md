# 멀티모듈 템플릿

---

## 소개

이 문서는 이전 프로젝트에서 사용한 Gradle 멀티모듈 구조를 예시와 함께 설명합니다. 해당 프로젝트는 게시글과 사용자에 대한 생성, 수정, 조회 기능을 포함하고 있습니다.

## 기반이 되는 책

- **도메인 주도 설계 철저 입문**: [Yes24 링크](https://m.yes24.com/Goods/Detail/93384475)
- **디자인 패턴의 아름다움: 객체지향 패러다임부터 설계 원칙**: [교보문고 링크](https://product.kyobobook.co.kr/detail/S000202093794)

위의 두 책에서 많은 영향을 받았습니다.

또한, 제미니 유튜브의 문답 부분도 많은 도움이 되었습니다: [제미니 유튜브](https://www.youtube.com/@geminikims)

---

## 구조

아래 항목은 패키지/모듈에 대한 최소 구조에 대한 간략한 설명입니다.

- **:app:app-web-api**
    - 배포되는 애플리케이션
    - 해당 예시에서는 도메인 객체에 대한 CRUD 연산을 제공합니다.


- **:core:core-domain**
    - 도메인 규칙과 스펙, 무결성을 지키기 위한 API가 포함됩니다.
    - 인프라 계층에 대한 API를 사용하여 인프라 계층의 사용을 추상화합니다.
    - 주로 특정 도메인 객체에 대한 리더(reader), 라이터(writer), 밸리데이터(validator) API를 제공합니다.
    - 복잡한 연산을 별도 클래스로 제공할 경우, ~~Processor 네이밍을 사용하는 것을 추천합니다.


- **:core:core-storage**
    - 실제 RDMS에 저장되는 엔티티와 쿼리에 대한 선언 및 API 모음입니다.
    - 외부 모듈로 노출할 함수의 기능을 별도 


- **:common:common**
    - 공통으로 쓰이는 열거형(enum), 예외(exception), 값 객체(value object)에 대한 스펙 모음입니다.

<br/>

추가적으로 사용할 수 있는 모듈 예시 

- **redis-client**: Redis 클라이언트와 구성, 분산 락과 같은 공통 기능을 모아놓은 라이브러리
- **es-client**: Elasticsearch 클라이언트와 구성, 인덱스 관련 API 모음
- **feign-client**: Spring Boot Feign에 대한 구성 및 공통으로 사용할 수 있는 커스텀 인자 모음 라이브러리
- **batch-app** : 배치 작업에 대한 어플리케이션 입니다. :app 하위에 작성합니다.
---

## 추천하는 컨벤션 

###  추천 컨벤션 1: 모듈에서 공개할 기능은 인터페이스로 명확히 하기

협업을 쉽게하는 팁 중 하나입니다. 

모듈을 제작할 때 모듈의 핵심 기능을 API 하위의 인터페이스에 모아두세요. 해당 모듈을 사용할 때, api/ 하위 경로의 인터페이스의 구현체만 주입 받거나, 테스트를 할 때 해당
인터페이스에 대한 Fake 클래스를 만들어 테스트하시기를 추천합니다. 

아래에 예시를 첨부해두었습니다.

##### 예상되는 장점

1. 리뷰어가 모듈의 의도와 사용법을 더 명확하게 알 수 있습니다.
2. 해당 API를 위주로 테스트하여 테스트 공수를 줄일 수 있습니다.
3. 모듈 개발의 일관성을 확보할 수 있습니다.

#### Queue Client 예시

아래는 Redis를 큐로 사용하는 경우, 큐에 대한 클라이언트를 모듈로 만든 예시입니다.

1-1. 패키지 구조 (queue client)

![convention_02.png](images%2Fconvention_02.png)

1-2. 패키지 구조 (domain api)

![convention_01.png](images%2Fconvention_01.png)



2. 인터페이스 선언

```java
public interface EventConsumer {

    /**
     * 큐의 항목을 from 번부터 count 갯수만큼 읽어옵니다.
     * 큐의 항목을 지우지는 않습니다.
     *
     * @param queueName
     * @param count
     * @param from
     */
    List<Event> get(String queueName, int count, int from);

    /**
     * 큐의 항목을 처음부터 count 갯수만큼 읽어오고 읽어온 항목을 지웁니다.
     * Redis를 이용해 개발하는 경우, 동시성 제어를 위해 LuaScript를 활용해보세요.
     *
     * @param queueName
     * @param count
     */
    List<Event> getAndRefresh(String queueName, int count);
}
```


---


### 추천 컨벤션 2: testFixture 사용과 api 인터페이스의 Fake 객체로 테스트.

순수 자바 기반의 테스트를 유지하면서 테스트에 들어갈 공수를 줄이기 위한 방법입니다. 

해당 레포지토리는 storage 클래스에서만 H2와 @DataJpaTest를 수행하고, 나머지 모듈에서는 각 모듈이 공개한 API 인터페이스에 대한 Fake 객체를 주입받아 테스트를 진행하고 있습니다. 이 부분은 설명이 조금 복잡해서 예시를 같이 첨부합니다.

해당 레포지토리는 아래와 같은 순서로 테스트를 수행합니다:

1. Storage 모듈에서 공개할 API에 대한 테스트 수행
2. API에 대한 Fake 클래스를 testFixture에 작성
3. 다음 모듈은 해당 Fake 클래스를 주입받아 테스트 수행

<br/>

##### 순서 1. storage 모듈에서 공개할 api 에 대한 테스트 수행 

```java
# 1. 모듈에서 외부로 공개할 api
public interface IArticleEntityRepository {

    ArticleEntity save(ArticleEntity article);

    Optional<ArticleEntity> findById(Long id);

    List<ArticleEntity> getAllByUserId(Long userId);
}
```

```java
# 2. h2 와 @DataJpa 로 슬라이싱 테스트 수행
@DataJpaTest
@Import({UserProfileQuery.class, QueryDslConfiguration.class})
class UserProfileQueryTest {

  @Autowired
  private UserProfileEntityRepository userProfileRepository;

  @Autowired
  private UserEntityRepository userRepository;

  @Autowired
  private UserProfileQuery userProfileQuery;


  private static final int ITERATION = 10;
  private static final String NICKNAME = "NICKNAME";

  @Test
  @DisplayName("nickname 인자로 리스트_ 성공")
  void listByUserNickname_success_01() {
    ...
```

##### 순서 2. api 에 대한 fake 클레스를 testFixture 에 작성 


```java
# 3. db 관련 초기화가 없는 java 기반의 repository fake class 를 testFixture 에 생성.
public class FakeIArticleEntityRepository implements IArticleEntityRepository {
    
    @Getter
    HashMap<Long, ArticleEntity> db = new HashMap<>();
    AtomicLong idGenerator = new AtomicLong();

    @Override
    public ArticleEntity save(ArticleEntity article) {
        var newId = idGenerator.addAndGet(1);
        FakeSetter.setField( article, "id", newId);

        db.put(newId,  article);
        return  article;
    }

```

```java
# 4. fake 클래스를 제공하는 매니저 클래스 생성
public class FakeDaoFactory {
  private static final IUserEntityRepository iUserEntityRepository
          = new FakeIUserEntityRepository();

  private static final IUserProfileEntityRepository iUserProfileEntityRepository
          = new FakeIUserProfileEntityRepository();

  private static final IArticleEntityRepository iArticleEntityRepository
          = new FakeIArticleEntityRepository();
  ...
  public static IUserEntityRepository getIUserEntityRepository() {
    return iUserEntityRepository;
  }

  public static IUserProfileEntityRepository getIUserProfileEntityRepository() {
    return iUserProfileEntityRepository;
  }
  ...

```

##### 순석 3. 다음 모듈은 해당 fake 클래스를 주입받아 테스트 수행 

위의 Fake 클래스들을 주입받아 해당 모듈을 사용하는 모듈을 테스트 합니다.

```java
# 5. 테스트 수행할 articleReaeder class
@Component
@RequiredArgsConstructor
public class DefaultArticleReader implements ArticleReader {
  private final IArticleEntityRepository articleEntityRepository;

  @Override
  public Optional<Article> get(Long articleId) {
    ...
  }
  ...
```

```java
# 6. articleReader 를 fake 객체를 주입받아 테스트 해결.
class DefaultArticleReaderTest {
    IArticleEntityRepository repository = FakeDaoFactory.getIArticleEntityRepository();
    ArticleReader articleReader = new DefaultArticleReader(repository);

```




##### 순수 자바로 테스트 하는 이유




1. **주입되는 빈의 커스텀이 가능**

인터페이스 기반의 개발을 하는 이유입니다.

스프링 컨텍스트와 외부 의존성으로 인해 일반적으로 테스트하기 어려운 Feign-client나 DB-client 등을 외부 의존성과 관계없이 테스트할 수 있습니다. 테스트 대상 객체가 인터페이스로 선언되어 있다면, 해당 인터페이스를 테스트 시점에 정의하여 테스트할 수 있습니다.

해당 레포지토리 예시에서는 상위 모듈에서 하위 모듈의 객체를 주입받을 때 전부 API 하위 경로의 인터페이스 타입을 주입받기 때문에, 직접 테스트하기 곤란한 의존성(security, feign, client 등)이 있거나, 아직 구현체가 완성되지 않은 상태에서도 임시로 익명 클래스를 만들어 테스트를 수행할 수 있습니다.

#### 예시 : 외부 api 호출의 동작을 커스텀

아래의 GithubLoginApiService는 GitHub API를 통해 OAuth 인증을 진행하는 코드입니다. GitHub API를 호출하는 코드를 인터페이스로 상속받게 하여 실제 Feign-client 동작에 필요한 의존성이나 외부 환경에 영향을 받지 않는 테스트를 수행할 수 있습니다.


```java
# 인터페이스 타입을 주입받도록 작성한 클래스.
public class GithubLoginApiService implements LoginApiService {

    private final GithubClientApi githubClient;
    private final UserProfileReader userProfileReader;
    private final UserProfileWriter userProfileWriter;
    public Long login(String code) {  
	  
	    var githubInfo = githubClient.getGithubInfo(code);  
	    var userProfile = userProfileReader.getOneByGithubUniqueId(githubInfo.uniqueId())  
	            .orElseThrow(IllegalArgumentException::new);  
	    ;  
	  
	    addSessionAttribute(githubInfo, userProfile);  
	  
	    return userProfile.getUserInfo().userId();  
	}  
  ...
```


```java
# 주입받을 인터페이스 
public interface GithubClientApi {  
    GithubInfoResponse getGithubInfo(String code);  
}
```

```java
# 테스트용 객체를 초기화 할때, 
class GithubJoinApiServiceTest {  
    private static final String GITHUB_CLIENT_FAIL_FLAG = "FAIL";  
    private static final String GITHUB_CLIENT_SUCCESS_FLAG = "SUCCESS";
    ...
    
    private GithubJoinApiService githubJoinApiService =  
            new GithubJoinApiService(  
                    code -> {  
                        if (code.equals(GITHUB_CLIENT_FAIL_FLAG))  
                            throw new RuntimeException("CAN NOT AUTHORIZE THOUGH GITHUB");  
  
                        return new GithubInfoResponse(UNIQUE_ID, NICKNAME, URL);  
                    },  
                    FakeDomainInterfaceFactory.getFakeUserProfileReader(),  
                    FakeDomainInterfaceFactory.getFakeUserProfileWriter()  
            );
```


2. **속도차이** 

아래는 순수 자바로 작성된 Domain 레이어와 H2와 @DataJpaTest를 통해 테스트한 Storage 레이어의 테스트 케이스당 수행 속도를 비교한 결과입니다. 약 40배 정도의 차이가 발생합니다. 

아래 예시에서는 둘 다 1초 미만의 작은 시간을 소모했지만, 전체 애플리케이션을 테스트하거나 github action의 vm 처럼 실행 환경이 좋지 않은 곳에서 테스트를 수행할 때 차이가 발생할 수 있습니다.


위 두 경우의 테스트 갯수 당 속도차이를 첨부합니다. 

1. @DataJpaTest 를 통한 infra layer 테스트
  -  개당 평균 : 21ms
    ![datajpa_infra_layer_test.png](images%2Fdatajpa_infra_layer_test.png)

2. FakeRepo 를 주입받아 테스트한 pure java 로 테스트
  - 개당 평균 : 0.6 ms
    ![domain_layer_pure_java_test.png](images%2Fdomain_layer_pure_java_test.png)

### 



### 추천 컨벤션 3: testFixture 의 관리와 전달 .

1. 