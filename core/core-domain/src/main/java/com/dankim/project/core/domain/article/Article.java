package com.dankim.project.core.domain.article;

import com.dankim.project.common.commons.exceptions.common.SpecificationException;
import com.dankim.project.common.commons.values.Content;
import com.dankim.project.common.commons.values.Title;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class Article {

    @NonNull
    private final Long authorId;

    private final Long articleId;

    @NonNull
    private final Content content;

    @NonNull
    private final Title title;


    private Article(Long authorId, Long articleId, Content content, Title title) {
        this.authorId = authorId;
        this.articleId = articleId;
        this.content = content;
        this.title = title;
    }

    public static Article of(Long authorId, Content content, Title title) {
        return new Article(authorId, null, content, title);
    }

    public Article addArticleId(Long articleId) {
        if (this.articleId != null)
            throw new SpecificationException("articleId 를 바꿀 수 없다");

        return new Article(authorId, articleId, content, title);
    }


}
