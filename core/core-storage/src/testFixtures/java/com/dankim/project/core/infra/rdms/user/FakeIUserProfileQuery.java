package com.dankim.project.core.infra.rdms.user;

import com.dankim.project.core.infra.rdms.FakeDaoFactory;
import com.dankim.project.core.infra.rdms.user.api.IUserProfileQuery;
import com.dankim.project.core.infra.rdms.user.projections.UserProjection;

import java.util.ArrayList;
import java.util.List;

public class FakeIUserProfileQuery implements IUserProfileQuery {

    private FakeIUserEntityRepository fakeIUserEntityRepository;
    private FakeIUserProfileEntityRepository fakeIUserProfileEntityRepository;

    public FakeIUserProfileQuery() {
        this.fakeIUserEntityRepository = (FakeIUserEntityRepository) FakeDaoFactory.getIUserEntityRepository();
        this.fakeIUserProfileEntityRepository = (FakeIUserProfileEntityRepository) FakeDaoFactory.getIUserProfileEntityRepository();
    }

    @Override
    public List<UserProjection> listByUserNickname(String nickname) {
        List<UserProfileEntity> profiles = new ArrayList<>();
        for (var key : fakeIUserProfileEntityRepository.getDb().keySet()) {
            var item = fakeIUserProfileEntityRepository.getDb().get(key);
            if (item.getNickname().equals(nickname))
                profiles.add(item);
        }

        List<UserProjection> projections = new ArrayList<>();
        for (var profile : profiles) {
            var user = fakeIUserEntityRepository.findById(profile.getUserId()).get();
            projections.add(
                    new UserProjection(
                            user.getId(),
                            user.getAvailable(),
                            profile.getNickname()
                    )
            );
        }
        return projections;
    }
}
