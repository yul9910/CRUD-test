package com.example.firstproject.repository;
import com.example.firstproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 특정 게시글의 모든 댓글 조회
    @Query(value =
            "SELECT * " + "FROM comment " +
                    "WHERE article_id = :articleId",
            nativeQuery = true)
    List<Comment> findByArticleId(@Param("articleId") Long articleId);

    // 특정 닉네임의 모든 댓글 조회
    List<Comment> findByNickname(String nickname);

    // 특정 단어 포함 닉네임의 모든 댓글 조회
    // '%m%'이 아닌 word 변수를 받는 법이었으면 좋겠으나, 찾지 못함ㅠ
    @Query(value = "SELECT * FROM comment WHERE nickName like '%m%'",
            nativeQuery = true)
    List<Comment> findByContainNickName(String nickname, String word);
}