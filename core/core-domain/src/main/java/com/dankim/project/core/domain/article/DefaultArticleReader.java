package com.dankim.project.core.domain.article;

import com.dankim.project.common.commons.exceptions.common.NotFoundException;
import com.dankim.project.common.commons.values.Content;
import com.dankim.project.common.commons.values.Title;
import com.dankim.project.core.domain.article.api.ArticleReader;
import com.dankim.project.core.infra.rdms.article.ArticleEntity;
import com.dankim.project.core.infra.rdms.article.api.IArticleEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DefaultArticleReader implements ArticleReader {
    private final IArticleEntityRepository articleEntityRepository;

    @Override
    public Optional<Article> get(Long articleId) {
        var optional = articleEntityRepository.findById(articleId);

        if (optional.isEmpty())
            return Optional.empty();

        var entity = optional.get();
        return Optional.of(Article.of(
                        entity.getUserId(),
                        Content.of(entity.getContent()),
                        Title.of(entity.getTitle()))
                .addArticleId(entity.getId()));
    }

    @Override
    public List<Article> getArticlesByUser(Long userId) {
        List<ArticleEntity> list = articleEntityRepository.getAllByUserId(userId);
        return list.stream()
                .map(
                        item ->
                                Article.of(item.getUserId(), Content.of(item.getContent()), Title.of(item.getTitle()))
                                        .addArticleId(item.getId()))
                .toList();
    }
}
