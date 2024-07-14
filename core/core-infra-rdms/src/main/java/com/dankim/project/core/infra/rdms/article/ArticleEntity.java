package com.dankim.project.core.infra.rdms.article;

import com.dankim.project.core.infra.rdms.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Table(schema = "article")
@NoArgsConstructor
public class ArticleEntity extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    @Setter
    private String title;
    @Setter
    private String content;

    private ArticleEntity(Long id, Long userId, String title, String content) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
    }

    public static ArticleEntity of(Long userId, String title, String content) {
        return new ArticleEntity(null, userId, title, content);
    }

}
