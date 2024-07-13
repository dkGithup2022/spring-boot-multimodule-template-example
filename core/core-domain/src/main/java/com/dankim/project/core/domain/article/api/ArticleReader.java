package com.dankim.project.core.domain.article.api;

import com.dankim.project.core.domain.article.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleReader {

    /**
     * article id 를 기준으로 조회합니다.
     *
     * @param articleId
     * @return
     */
    Optional<Article> get(Long articleId);

    /**
     * user 가 작성한 문서를 조회합니다.
     * <p>
     * 문서가 없을경우 List.empty() 를 반환합니다.
     * @param userId
     * @return
     */
    List<Article> getArticlesByUser(Long userId);
}
