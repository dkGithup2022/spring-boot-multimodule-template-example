package com.dankim.project.app.web.api.article;

import com.dankim.project.core.domain.article.Article;

public interface ArticleApiService {
    /**
     * article 을 생성합니다.
     *
     * [ exception ]
     *  - ForbiddenException (403)  :  생성 권한이 없는 경우
     *
     * @param userId
     * @param content
     * @param title
     * @return id:Long  | 생성된 articleId 를 리턴합니다.
     */
    Long create(Long userId, String content, String title);

    /**
     * article 을 변경합니다.
     * title, content 를 변경 할 수 있습니다.
     *
     * [ exception ]
     *  - ForbiddenException (403)  :  변경 권한이 없는 경우
     *  - BadRequestException (400) : 문서가 없는 경우.
     *
     * @param userId
     * @param content
     * @param title
     * @return article:Article | 생성된 article 를 리턴합니다.
     */
    Article update(Long articleId, Long userId, String content, String title);


    /**
     * article 을 조회합니다.
     *
     * [ exception ]
     *  - BadRequestException (400) : 문서가 없는 경우.
     *
     * @param articleId
     * @return article:Article | 조회된 article 를 리턴합니다.
     */
    Article read(Long articleId);
}
