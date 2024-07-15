package com.dankim.project.core.infra.rdms.user;

import com.dankim.project.core.infra.rdms.FakeSetter;
import com.dankim.project.core.infra.rdms.article.ArticleEntity;
import com.dankim.project.core.infra.rdms.user.api.IUserProfileEntityRepository;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class FakeIUserProfileEntityRepository implements IUserProfileEntityRepository {
    @Getter
    HashMap<Long, UserProfileEntity> db = new HashMap<>();
    AtomicLong idGenerator = new AtomicLong();

    @Override
    public UserProfileEntity save(UserProfileEntity userProfileEntity) {
        var newId = idGenerator.addAndGet(1);
        FakeSetter.setField(userProfileEntity, "id", newId);

        db.put(newId, userProfileEntity);
        return userProfileEntity;
    }

    @Override
    public Optional<UserProfileEntity> findById(Long id) {
        return db.get(id) == null ? Optional.empty() : Optional.of(db.get(id));
    }

    @Override
    public List<UserProfileEntity> findByUserId(Long id) {
        List<UserProfileEntity> list = new ArrayList<>();
        for (var key : db.keySet()) {
            var item = db.get(key);
            if (item.getUserId().equals(id))
                list.add(item);
        }
        return list;
    }
}
