package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.DTO.TimetableDTO;
import com.nthn.springbootthymeleaf.VO.TimetableQueryVO;
import com.nthn.springbootthymeleaf.VO.TimetableUpdateVO;
import com.nthn.springbootthymeleaf.VO.TimetableVO;
import com.nthn.springbootthymeleaf.model.Timetable;
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

    public Integer save(TimetableVO vO) {
        Timetable bean = new Timetable();
        BeanUtils.copyProperties(vO, bean);
        bean = timetableRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        timetableRepository.deleteById(id);
    }

    public void update(Integer id, TimetableUpdateVO vO) {
        Timetable bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        timetableRepository.save(bean);
    }

    public TimetableDTO getById(Integer id) {
        Timetable original = requireOne(id);
        return toDTO(original);
    }

    public Page<TimetableDTO> query(TimetableQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private TimetableDTO toDTO(Timetable original) {
        TimetableDTO bean = new TimetableDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Timetable requireOne(Integer id) {
        return timetableRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
