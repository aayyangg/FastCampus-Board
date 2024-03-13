package com.example.fastcampusboard.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter // 모든 필드에 접근한다.
@ToString // 쉽게 볼 수 있도록 표현해준다.
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy"),
})
// 테이블에 인덱스를 건다.
// 여러개 써줘야 하니 {} 작성한다.

@Entity // 이 도메인은 엔티티라고 명시한다.
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Auto Increment 설정하는 법.
    // MySql 은 INDENTITY 방식으로 작성하고, 안쓰면 AUTO 가 기본이다.
    private Long id;

    // @Setter : 도메인에서 수정이 가능함.
    // 클래스에 Setter를 걸지 않은 이유는, 일부 필드만 접근한 세팅을 접근하고,
    // 모든 필드를 접근하지 못하게 세팅한다.
    // 예) ID 는 JPA Persistance Context 가 연속화할 때 자동으로 부여해주는 고유 번호이다.
    // 이 필드를 함부로 접근 못하게 하기 위해 특정 필드만 Setter 를 사용한다.
    @Setter @Column(nullable = false) private String title; // 제목 , NOT NULL, nullable은 기본이 true 이다.
    @Setter @Column(nullable = false, length = 10000) private String content; // 본문 , NOT NULL
    @Setter private String hashtag; // 해시태그 , null 가능

    //메타 데이터 , NOT NULl
    // 자동으로 JPA를 만들어 준다. -> JPA Auditing
    @CreatedDate @Column(nullable = false) private LocalDateTime createdAt; //생성 일시
    @CreatedBy @Column(nullable = false, length = 100) private String createdBy; // 생성자
    @LastModifiedDate @Column(nullable = false) private LocalDateTime modifiedAt; // 수정 일시
    @LastModifiedBy @Column(nullable = false, length = 100) private String modifiedBy; // 수정자

    // 이 작업은 데이터 설계를 한 것임.

    // 기본 생성자
    protected Article() {}

    // 메타 데이터, ID 제외 하고 생성자 만든다.
    // 생성자에 모든 필드를 작성하면, 모든 필드를 세팅해줘야 한다.
    // 그래서, 필요한 데이터만 생성자에 작성한다.
    // public -> private 변경 : factory Method를 통해서 이것을 제공할 수 있도록 하자.
    // new로 사용하지 않고 쓸 수 있도록
    private Article(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    // factory Method를 통해서 이것을 제공할 수 있도록 하자.
    // new로 사용하지 않고 쓸 수 있도록
    // 의도 : 도메인 아티클을 생성하고자 할 경우, 어떤 값이 필요로 한다는 것을 가이드 줄 수 있음.
    public static Article of(String title, String content, String hashtag) {
        return new Article(title, content, hashtag);
    }


    // 리스트, 컬렉션을 이용하여 데이터를 넣기.
    // 리스트/컬렉션 내용 안 중복 요소 제거, 정렬 할 경우 비교가 필요하다.
    // -> 동일성, 동등성 검사를 할 수 있는 equals hashcode를 사용한다.
    // 이것은 lombok 을 이용하여 @EqualsAndHashCode 로 이용이 가능하다.
    // ->그러나, 독특한 equals hashcode 만들어 사용해야 한다. ( EqualsAndHashCode 기본은 모든 필드를 비교한다. )

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return id != null && id.equals(article.id);
    }

    @Override
    public int hashCode() { // 동등성 검사 함수
        return Objects.hash(id);
    }
}

