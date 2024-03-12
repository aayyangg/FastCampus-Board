package com.example.fastcampusboard.domain;

import java.time.LocalDateTime;

public class Article {
    private Long id;
    private String title;
    private String hashtag;

    //메타 데이터
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;

    // 이 작업은 데이터 설계를 한 것임.
}
