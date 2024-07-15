package com.dankim.project.core.domain.article.api;

import com.dankim.project.core.domain.article.Article;

public interface ArticleWriter {
    /**
     * [ api 설명 예시 입니다. ]
     * article 객체를 update 하고 update 된 article 객체를 리턴합니다.
     * <p>
     * [exception]
     * NotFoundException : 조회되는 객체가 없는 경우, NotFoundException 을 반환합니다.
     * <p>
     * @param article
     */
    Article update(Article article);


    /**
     * [ api 설명 예시 입니다. ]
     * article 객체를 저장합니다.
     * <p>
     * 저장된 article 객체를 리턴합니다. 리턴되는 객체는 id 를 포함하고 있습니다.
     * <p>
     * @param article
     */
    Article newArticle(Article article);
}
