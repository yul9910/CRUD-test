package com.example.firstproject.entity;

import lombok.*;

import javax.persistence.*;

@Getter // 엔티티에 게터,세터 설정을 해야 JSON으로 넘어옴
@Setter
@NoArgsConstructor // 기본생성자 출력
@AllArgsConstructor
@ToString
@Entity //DB가 해당 객체를 인식 가능하게 하는 어노테이션 (해당 클래스로 테이블을 만든다!)
public class Article {
    @Id // 대표값 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 id를 자동 생성!
    @Getter
    private Long id;
    @Column
    private String title;
    @Column
    private String content;


    public void patch(Article article) {
        if (article.title != null)
            this.title = article.title;
        if (article.content != null)
            this.content = article.content;
    }
}
