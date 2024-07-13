package com.dankim.project.core.domain.article.api;

import com.dankim.project.core.domain.article.Article;

public interface ArticleWriter {
    /**
     * [ api 설명 예시 입니다. ]
     * article 객체를 update 합니다.
     * <p>
     * title, content 이 다르다면 update 하고 나머지 밸류는 다른 값이 있다면 exception 을 반환합니다.
     *
     * @param article
     */
    Article update(Article article);


    /**
     * [ api 설명 예시 입니다. ]
     * article 객체를 저장합니다.
     * <p>
     * 저장후 id 를 update 해서 리턴합니다.
     *
     * @param article
     */
    Article newArticle(Article article);

}
