package com.dankim.project.app.web.api.article;

import com.dankim.project.common.commons.exceptions.clientException.BadRequestException;
import com.dankim.project.common.commons.exceptions.clientException.ForbiddenException;
import com.dankim.project.common.commons.values.Content;
import com.dankim.project.common.commons.values.Title;
import com.dankim.project.core.domain.article.Article;
import com.dankim.project.core.domain.article.api.ArticleReader;
import com.dankim.project.core.domain.article.api.ArticleWriter;
import com.dankim.project.core.domain.article.api.WebApiArticleValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultArticleApiService implements ArticleApiService {
    private final ArticleReader articleReader;
    private final ArticleWriter articleWriter;
    private final WebApiArticleValidator articleValidator;


    @Override
    public Long create(Long userId, String content, String title) {
        if (!articleValidator.canCreate(userId))
            throw new ForbiddenException("글 작성이 허용된 유저가 아닙니다.");

        var created = articleWriter.newArticle(Article.of(userId, Content.of(content), Title.of(title)));
        return created.getArticleId();
    }

    @Override
    public Article update(Long articleId, Long userId, String content, String title) {
        var article = articleReader.get(articleId)
                .orElseThrow(() -> new BadRequestException("id 에 해당하는 article 이 없습니다"));

        if (!articleValidator.canUpdate(article, userId))
            throw new ForbiddenException("글 수정이 허용된 유저가 아닙니다.");

        var toUpdate = newArticleToUpdate(articleId, content, title, article);

        return articleWriter.update(toUpdate);
    }

    @Override
    public Article read(Long articleId) {
        return articleReader.get(articleId)
                .orElseThrow(() -> new BadRequestException("id 에 해당하는 article 이 없습니다"));
    }


    private Article newArticleToUpdate(Long articleId, String content, String title, Article article) {
        return Article
                .of(
                        article.getAuthorId(),
                        Content.of(parseContent(article, content)),
                        Title.of(parseTitle(article, title)))
                .addArticleId(articleId);
    }



    private String parseTitle(Article article, String title) {
        return title == null || title.isEmpty() ? article.getTitle().get() : title;
    }

    private String parseContent(Article article, String content) {
        return content == null || content.isEmpty() ? article.getContent().get() : content;
    }

}
