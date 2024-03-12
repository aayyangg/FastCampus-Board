package com.example.fastcampusboard.domain;

import java.time.LocalDateTime;

public class ArticleComment {

    private Long id;
    private Article article;
    private String content;

    //메타 데이터
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;
}
