package com.example.fastcampusboard.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@RequestMapping("/articles") //루트 페이지
@Controller
public class ArticleController {

    @GetMapping
    public String articles(ModelMap map) { // 핸들러 메소드 오픈/게시판 루트 페이지로 갈 수 있도록 한 메소드.
        map.addAttribute("articles", List.of());
        return "articles/index";
    }
}
