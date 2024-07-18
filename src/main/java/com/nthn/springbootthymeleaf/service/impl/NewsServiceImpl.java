package com.nthn.springbootthymeleaf.service.impl;

import com.nthn.springbootthymeleaf.entity.News;
import com.nthn.springbootthymeleaf.repository.NewsRepository;
import com.nthn.springbootthymeleaf.service.NewsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Override
    public Page<News> getNewsPage(Pageable pageable) {
        return newsRepository.findAll(pageable);
    }

    @Override
    public List<News> getNews() {
        return newsRepository.findAll();
    }

    @Override
    public List<News> getNews(String keyword) {
        if (keyword == null) {
            return newsRepository.findAll(Sort.by("create_date").descending());
        }
        return newsRepository.findAll();
    }

    //
//    public Paged<News> getPage(int pageNumber, int size) {
//        PageRequest request = PageRequest.of(pageNumber - 1, size, new Sort(Sort.Direction.ASC, "id"));
//        Page<News> postPage = newsRepository.findAll(request);
//        return new Paged<>(postPage, Paging.of(postPage.getTotalPages(), pageNumber, size));
//    }
    @Override
    public News save(News news) {
        News bean = new News();
        BeanUtils.copyProperties(news, bean);
        bean = newsRepository.save(bean);
        return bean;
    }

    @Override
    public void delete(Integer id) {
        newsRepository.deleteById(id);
    }

    @Override
    public void update(Integer id, News news) {
        News bean = requireOne(id);
        bean.setViews(news.getViews() + 1);
        newsRepository.save(bean);
    }

    @Override
    public News getById(Integer id) {
        return (requireOne(id));
    }

    private News requireOne(Integer id) {
        return newsRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
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
