package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;


//CrudRepository<Article,Long> 첫번째 파라미터는 관리하는 객체, 두번째 파라미터는 그 객체의 Id값의 타입
public interface ArticleRepository extends CrudRepository<Article,Long> {
    @Override
    ArrayList<Article> findAll();
}
