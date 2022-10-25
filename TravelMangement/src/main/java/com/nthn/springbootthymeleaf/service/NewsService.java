package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.pojo.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NewsService {
    Page<News> getNewsPage(Pageable pageable);

    List<News> getNews();

    List<News> getNews(String keyword);


    Integer save(News news);

    void delete(Integer id);

    void update(Integer id, News news);

    News getById(Integer id);
}
