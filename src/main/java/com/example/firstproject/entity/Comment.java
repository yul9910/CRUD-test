package com.example.firstproject.entity;
import com.example.firstproject.dto.CommentDto;
import lombok.*;

import javax.persistence.*;
@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne // 해당 댓글 엔티티 여러개가, 하나의 Article에 연관된다!
    @JoinColumn(name = "article_id")
    private Article article;
    @Column
    private String nickname;
    @Column
    private String body;

    public static Comment createComment(CommentDto dto, Article article) {
        // 예외 발생
        // IllegalArgumentException 적합하지 않거나, 적절하지 못한 인자
        if(dto.getId()!=null)
            throw new IllegalArgumentException("생성 실패, id값 존재 하지 않아야 함");
        if(dto.getArticleId() != article.getId())
            throw new IllegalArgumentException("생성 실패, 게시글 값 id와 댓글 id가 다릅니다");
        //엔티티 생성 및 반환
        return new Comment(
                dto.getId(),
                article,
                dto.getNickname(),
                dto.getBody()
        );
    }

    public void patch(CommentDto dto) {
        //예외 발생 (잘못된 id 값 입력)
        if(dto.getId() != this.id)
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id가 입력되었습니다.");
        //객체 갱신 (null이 아닌 상태 = 내용이 있을 때)
        if(dto.getNickname() != null)
            this.nickname = dto.getNickname();
        if(dto.getBody() != null)
            this.body= dto.getBody();
    }
}