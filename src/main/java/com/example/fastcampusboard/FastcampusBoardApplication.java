package com.example.fastcampusboard;

import com.example.fastcampusboard.domain.Article;
import com.example.fastcampusboard.repository.ArticleCommentRepository;
import com.example.fastcampusboard.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class}) //DB 설정 없이 스프링부트 빌드 시, 사용
@EntityScan(basePackages = {"com.example.fastcampusboard.domain"})
@EnableAutoConfiguration(exclude={JpaRepositoriesAutoConfiguration.class})
public class FastcampusBoardApplication {
	//24.03.19
	@Autowired
	private static ArticleRepository articleRepository;
	@Autowired
	private static ArticleCommentRepository articleCommnetRepository;
	public static void main(String[] args) {
		SpringApplication.run(FastcampusBoardApplication.class, args);


		// select

		//insert
//	long previousCount = articleRepository.count();
//	Article article = Article.of("new article","new content","new hashtag");
//	Article savedArtice = articleRepository.save(article);

		//update
//		Article article = articleRepository.findById(1L).orElseThrow();
//		String updatedHashtag ="#springboot";
//
//		article.setHashtag(updatedHashtag);
//
//		// 24.03.20
//		// 영속성 컨텍스트으로부터 artice 값을 가져와서 save 한다.
////		Article savedArtice = articleRepository.save(article);
//		Article savedArtice = articleRepository.saveAndFlush(article);


		//24.03.20
		//delete
		Article article = articleRepository.findById(1L).orElseThrow();
		long previousArticleCount = articleRepository.count(); // 삭제 전, 글 갯수 확인
		long previousArticleCommentCount = articleCommnetRepository.count(); // 글이 삭제 될 시, 댓글 수도 지워지니깐, 지워지기 전 댓글 수도 확인한다.

		// 글이 삭제 될 때, 지워지는 댓글 수를 미리 확인한다.
		// 양방향 바인딩을 통해 글의 댓글을 가져올 수 있다.
		int deletedCommentsSize = article.getArticleComments().size();

		articleRepository.delete(article);

	}






}
