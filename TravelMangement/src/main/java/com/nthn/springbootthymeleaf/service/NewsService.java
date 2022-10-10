package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.pojo.News;
import com.nthn.springbootthymeleaf.repository.NewsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    public Page<News> getNewsPage(Pageable pageable) {
        return newsRepository.findAll(pageable);
    }

    public List<News> getNews(String keyword) {
        if (keyword == null) {
            return newsRepository.findAll(Sort.by("create_date").descending());
        }
        return newsRepository.findAll();
    }

    public Integer save(News news) {
        News bean = new News();
        BeanUtils.copyProperties(news, bean);
        bean = newsRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        newsRepository.deleteById(id);
    }

    public void update(Integer id, News news) {
        News bean = requireOne(id);
        BeanUtils.copyProperties(news, bean);
        newsRepository.save(bean);
    }

    public News getById(Integer id) {
        return (requireOne(id));
    }

    private News requireOne(Integer id) {
        return newsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

//    public Page<NewsDTO> query(NewsQueryVO vO) {
//        throw new UnsupportedOperationException();
//    }
//
//    private NewsDTO toDTO(News original) {
//        NewsDTO bean = new NewsDTO();
//        BeanUtils.copyProperties(original, bean);
//        return bean;
//    }
}
