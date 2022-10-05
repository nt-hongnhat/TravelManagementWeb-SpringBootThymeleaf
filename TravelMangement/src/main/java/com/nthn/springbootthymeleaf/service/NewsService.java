package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.DTO.NewsDTO;
import com.nthn.springbootthymeleaf.VO.NewsQueryVO;
import com.nthn.springbootthymeleaf.VO.NewsUpdateVO;
import com.nthn.springbootthymeleaf.VO.NewsVO;
import com.nthn.springbootthymeleaf.model.News;
import com.nthn.springbootthymeleaf.repository.NewsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    public Integer save(NewsVO vO) {
        News bean = new News();
        BeanUtils.copyProperties(vO, bean);
        bean = newsRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        newsRepository.deleteById(id);
    }

    public void update(Integer id, NewsUpdateVO vO) {
        News bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        newsRepository.save(bean);
    }

    public NewsDTO getById(Integer id) {
        News original = requireOne(id);
        return toDTO(original);
    }

    public Page<NewsDTO> query(NewsQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private NewsDTO toDTO(News original) {
        NewsDTO bean = new NewsDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private News requireOne(Integer id) {
        return newsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
