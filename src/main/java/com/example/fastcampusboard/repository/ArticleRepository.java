package com.example.fastcampusboard.repository;

import com.example.fastcampusboard.domain.Article;
import com.example.fastcampusboard.domain.QArticle;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleRepository
        extends JpaRepository<Article, Long>,
        QuerydslPredicateExecutor<Article>,
        QuerydslBinderCustomizer<QArticle> // QArticle 빨간 줄 표현되면, import QArticle해줘야 함. (alt+enter 활용)
{
    // override 메소드 생성하기.

    @Override // 이 내용에 구현된 로직을 토대로 검색에 대한 세부적인 규칙이 다시 재구성된다.
    default void customize(QuerydslBindings bindings, QArticle root){ // 리포지토리에서 직접 구현하는 것이 아닌, 스프링 데이터 JPA 를 이용해서 간편히 접근하는 중임. 그래서 {} 로 사용한다. (변수로 사용하는 것 아님.)
        // default 넣는 이유 , 이 매개변수들이 인터페이스라 원래 구현할 수 없음. 자바 파일은 가능함.
        // default 메소드로 생성한다.
        // 커스터마이즈 구현함.

        // 1. binding
        // excludeUnlistedProperties : QuerydslPredicateExecutor 에 의해 아티클 엔티티에 있는 모든 필드에 대한 검색을 할 수 있다.
        // 모든 필드 검색하기 싫고, 선택적 검색을 해야 한다면, 리스팅을 하지 않은 프로퍼티는 검색에서 제외를 시키는 것을 true로 변경한다.
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.title, root.content, root.hashtag, root.createdAt, root.createdBy); // 검색할 필드를 써준다. 
//        bindings.bind(root.title).first((path, value) -> path.eq(value)); // 룰 설정, first : 검색 파라미터는 하나만 받는다. 람다식 이용
        // 위 코드는 exact match 방식이고, 경로와 값이 일치하냐는 룰을 설정했다.
        //exact match 방식 -> method Reference 로 변경하기. (리팩토링)
//        bindings.bind(root.title).first(SimpleExpression::eq);
//        bindings.bind(root.title).first(StringExpression::likeIgnoreCase); // 쿼리문이 like '${v}' 이다. % 사용하려면 직접 넣어줘야 함
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase); // 쿼리문이 like '%${v}%' 이다. 자동적으로 % 와일드카드가 자동 생성된다.
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq); // 날짜 타입의 동일 검사하기. 이건 편리한 방법은 아님. 시분초까지 동일한지 검사하기 떄문.. 그럼 편리하려면 어떻게 하나? 날짜 일을 뺴서 검사하기.ㄴ
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
    }
}
