package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId){
        //레파지토리 접근은 엔티티
        // 조회: 댓글 목록
       /* List<Comment> comments = commentRepository.findByArticleId(articleId);

        // 변환: 엔티티 -> DTO
        List<CommentDto> dtos = new ArrayList<CommentDto>();
        for (int i = 0; i < comments.size(); i++) {
            Comment c = comments.get(i);
            CommentDto dto = CommentDto.createCommentDto(c);
            dtos.add(dto);
        }
        */
        //한번에 쓰는 코드
        return commentRepository.findByArticleId(articleId).stream().map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());

        // return commentRepository.findByArticleId(articleId) comment entity의 리스트를 반환
        // .stream 스트림으로 변형 후 하나씩 꺼냄
        // .map 꺼내진 comment들을 commentDto로 변환 CommentDto.createCommentDto를 통해 변환함
        // 반환 된 타입은 Stream <Object> 형이니 이를 리스트로 묶어줌


    }
    public CommentDto create(Long articleId, CommentDto dto) {
        //orElseThrow는 Optional의 인자가 null일 경우 예외처리를 시킨다.
        // 게시글 조회 및 예외 발생
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! 대상 게시글이 없습니다."));

        //댓글 엔티티 생성
        Comment comment = Comment.createComment(dto, article);

        //댓글 엔티티 DB로 저장
        Comment created = commentRepository.save(comment);

        // DTO로 변경하여 반환
        return CommentDto.createCommentDto(created);
    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        // 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패! 대상 댓글이 없습니다."));

        // 댓글 수정
        target.patch(dto);

        // DB 저장
        Comment updated = commentRepository.save(target);

        // 댓글 엔티티를 DTO로 변환 및 반환
        return CommentDto.createCommentDto(updated);
    }

    @Transactional
    public CommentDto delete(Long id) {

        // 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패! 대상이 없습니다."));

        //댓글 삭제
        commentRepository.delete(target);

        //삭제된 댓글을 DTO로 반환
        return CommentDto.createCommentDto(target);
    }
}
