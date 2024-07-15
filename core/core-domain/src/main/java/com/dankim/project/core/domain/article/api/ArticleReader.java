package com.dankim.project.core.domain.article.api;

import com.dankim.project.core.domain.article.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleReader {
    /**
     * Retrieves an article by its ID.
     * Returns Optional.empty() if no article is found.
     *
     * @param articleId the ID of the article to retrieve
     * @return an Optional containing the found article or Optional.empty() if not found
     */
    Optional<Article> get(Long articleId);

    /**
     * Retrieves articles written by a specific user.
     * Returns an empty list if no articles are found.
     *
     * @param userId the ID of the user whose articles are to be retrieved
     * @return a list of articles written by the user, or an empty list if none are found
     */
    List<Article> getArticlesByUser(Long userId);
}
