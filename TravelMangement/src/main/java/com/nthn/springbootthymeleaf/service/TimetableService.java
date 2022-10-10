package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.pojo.Timetable;
import com.nthn.springbootthymeleaf.repository.TimetableRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class TimetableService {

    @Autowired
    private TimetableRepository timetableRepository;

    public Integer save(Timetable timetable) {
        Timetable bean = new Timetable();
        BeanUtils.copyProperties(timetable, bean);
        bean = timetableRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        timetableRepository.deleteById(id);
    }

    public void update(Integer id, Timetable timetable) {
        Timetable bean = requireOne(id);
        BeanUtils.copyProperties(timetable, bean);
        timetableRepository.save(bean);
    }

    public Timetable getById(Integer id) {
        return (requireOne(id));
    }

    private Timetable requireOne(Integer id) {
        return timetableRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

//    public Page<TimetableDTO> query(TimetableQueryVO vO) {
//        throw new UnsupportedOperationException();
//    }
//
//    private TimetableDTO toDTO(Timetable original) {
//        TimetableDTO bean = new TimetableDTO();
//        BeanUtils.copyProperties(original, bean);
//        return bean;
//    }
}
