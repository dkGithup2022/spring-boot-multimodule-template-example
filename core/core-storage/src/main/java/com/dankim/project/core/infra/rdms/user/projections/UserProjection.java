package com.dankim.project.core.infra.rdms.user.projections;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserProjection {
    private Long userId;
    private Boolean available;
    private String nickname;
}
