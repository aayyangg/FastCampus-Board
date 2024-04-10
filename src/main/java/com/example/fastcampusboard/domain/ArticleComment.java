package com.example.fastcampusboard.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter // 모든 필드에 접근한다.
@ToString // 쉽게 볼 수 있도록 표현해준다.
@Table(indexes = {
        @Index(columnList = "content"), // 본문에 인덱스를 건다.
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy"),
})
// 테이블에 인덱스를 건다.
// 여러개 써줘야 하니 {} 작성한다.
@EntityListeners(AuditingEntityListener.class)
@Entity // 이 도메인은 엔티티라고 명시한다.
public class ArticleComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter @ManyToOne(optional = false)
    private Article article; // 게시글 (ID)
    @Setter @Column(nullable = false, length = 500) private String content; // 본문

    //메타 데이터
    @CreatedDate
    @Column(nullable = false) private LocalDateTime createdAt; //생성 일시
    @CreatedBy
    @Column(nullable = false, length = 100) private String createdBy; // 생성자
    @LastModifiedDate
    @Column(nullable = false) private LocalDateTime modifiedAt; // 수정 일시
    @LastModifiedBy
    @Column(nullable = false, length = 100) private String modifiedBy; // 수정자

    // 아무 것도 없는 기본 생성자는 롬복으로 만들 수 있다.
    // @NoArgsConstructor(access = AccessLevel.PROTECTED)
    protected ArticleComment(){}

    private ArticleComment(Article article, String content) {
        this.article = article;
        this.content = content;
    }

    public static ArticleComment of(Article article, String content) {
        return new ArticleComment(article, content);
    }

    // 스켈레톤 코드 만들기를 인텔리제이가 generate -> equals(), hashCode() 로 만들어준다.
    // id 를 가지고 동일성/동등성 검사를 진행한다.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleComment that = (ArticleComment) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
