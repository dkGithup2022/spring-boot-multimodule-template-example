package com.dankim.project.core.infra.rdms.user;

import com.dankim.project.core.infra.rdms.FakeSetter;
import com.dankim.project.core.infra.rdms.article.ArticleEntity;
import com.dankim.project.core.infra.rdms.user.api.IUserEntityRepository;
import lombok.Getter;

import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class FakeIUserEntityRepository implements IUserEntityRepository {

    @Getter
    HashMap<Long, UserEntity> db = new HashMap<>();
    AtomicLong idGenerator = new AtomicLong();

    @Override
    public UserEntity save(UserEntity userEntity) {
        var newId = idGenerator.addAndGet(1);
        FakeSetter.setField(userEntity, "id", newId);

        db.put(newId, userEntity);
        return userEntity;
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return db.get(id) == null ? Optional.empty() : Optional.of(db.get(id));
    }
}
