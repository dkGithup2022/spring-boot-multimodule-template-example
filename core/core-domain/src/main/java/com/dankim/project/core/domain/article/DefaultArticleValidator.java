package com.dankim.project.core.domain.article;

import com.dankim.project.common.commons.exceptions.common.NotFoundException;
import com.dankim.project.core.domain.article.api.WebApiArticleValidator;
import com.dankim.project.core.infra.rdms.user.api.IUserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultArticleValidator implements WebApiArticleValidator {
    private final IUserEntityRepository userRepository;
    @Override
    public boolean canCreate(Long userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("유저를 찾을 수 없음 : | userId : " + userId));

        return user.getAvailable();
    }

    @Override
    public boolean canUpdate(Article article, Long userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("유저를 찾을 수 없음 : | userId : " + userId));

        if (user.getAvailable())
            return false;

        return article.getAuthorId().equals(userId);
    }
}
