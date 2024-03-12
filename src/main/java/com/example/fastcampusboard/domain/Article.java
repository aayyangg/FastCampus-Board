package com.example.fastcampusboard.domain;

import java.time.LocalDateTime;

public class Article {
    private Long id;
    private String title; // 제목
    private String hashtag; // 해시태그

    //메타 데이터
    private LocalDateTime createdAt; //생성 일시
    private String createdBy; // 생성자
    private LocalDateTime modifiedAt; // 수정 일시
    private String modifiedBy; // 수정자

    // 이 작업은 데이터 설계를 한 것임.
}
