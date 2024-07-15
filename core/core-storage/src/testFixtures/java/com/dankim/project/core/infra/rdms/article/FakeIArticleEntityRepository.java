package com.dankim.project.core.infra.rdms.article;

import com.dankim.project.core.infra.rdms.FakeSetter;
import com.dankim.project.core.infra.rdms.article.api.IArticleEntityRepository;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

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

    @Override
    public Optional<ArticleEntity> findById(Long id) {
        return db.get(id) == null ? Optional.empty() : Optional.of(db.get(id));
    }

    @Override
    public List<ArticleEntity> getAllByUserId(Long userId) {
        List<ArticleEntity> list = new ArrayList<>();
        for(Long id : db.keySet())
            if(db.get(id).getUserId().equals(userId))
                list.add(db.get(id));
        return list;
    }
}
