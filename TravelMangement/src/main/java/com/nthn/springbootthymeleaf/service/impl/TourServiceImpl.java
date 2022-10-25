package com.nthn.springbootthymeleaf.service.impl;

import com.nthn.springbootthymeleaf.pojo.Tour;
import com.nthn.springbootthymeleaf.pojo.TourGroup;
import com.nthn.springbootthymeleaf.pojo.TourTicket;
import com.nthn.springbootthymeleaf.repository.TourGroupRepository;
import com.nthn.springbootthymeleaf.repository.TourRepository;
import com.nthn.springbootthymeleaf.repository.TourTicketRepository;
import com.nthn.springbootthymeleaf.service.TourService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Transactional
public class TourServiceImpl implements TourService {
    @Autowired
    private TourRepository tourRepository;
    @Autowired
    private TourGroupRepository tourGroupRepository;
    @Autowired
    private TourTicketRepository tourTicketRepository;

    @Override
    public Tour save(Tour tour) {
        return tourRepository.save(tour);
    }

    @Override
    public Tour create(Tour tour) throws ParseException {
        tour.setDuration(tour.getDays() + " ngày " + tour.getNights() + " đêm");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        tour.setDepartureDate(LocalDate.parse(tour.getDate(), formatter));
//        tour.setDepartureDate(LocalDateTime.from(tour.getDepartureDate()));
//        Set<TourTicket> tourTickets = tour.getTourTickets();
//        Tour finalTour = save(tour);
//        tourTickets.forEach(tourTicket -> {
//            tourTicket.setTour(finalTour);
//            tourTicketRepository.save(tourTicket);
//        });
        return save(tour);
    }


    @Override
    public void delete(Integer id) {
        tourRepository.deleteById(id);
    }

    @Override
    public void update(Integer id, Tour tour) {
        Tour bean = requireOne(id);
        BeanUtils.copyProperties(tour, bean);
        tourRepository.save(bean);
    }

    @Override
    public Tour getById(Integer id) {
        return requireOne(id);
    }

    @Override
    public List<Tour> getTours() {
        return tourRepository.findAll();
    }


    /**
     * Lấy trang tour
     *
     * @param pageable : page, size
     * @return
     */
    @Override
    public Page<Tour> getTourPage(Pageable pageable) {
        return this.tourRepository.findAll(pageable);
    }

    /**
     * Lấy trang tour theo nhóm tour
     *
     * @param tourGroupLink
     * @param pageable
     * @return
     */
    @Override
    public Page<Tour> getTourPage(String tourGroupLink, Pageable pageable) {
        return this.tourRepository.findByTourGroup(pageable, tourGroupLink);
    }

    /**
     * Tìm kiếm tour theo nhiều tiêu chí
     *
     * @param departurePlace   : nơi khởi hành
     * @param destinationPlace : nơi đến
     * @param duration         : thời gian đi
     * @param pageable         : page, size
     * @return page
     */
    @Override
    public Page<Tour> getTourPage(String departurePlace, String destinationPlace, String duration, String tourGroupLink, Pageable pageable) {
        return tourRepository.searchTours(departurePlace, destinationPlace, duration, tourGroupLink, pageable);
    }


    /**
     * Lấy danh sách tour theo danh mục tour
     *
     * @param tourGroups
     * @param pageable
     * @return
     */
    @Override
    public Page<Tour> getTourPageByTourGroup(List<TourGroup> tourGroups, Pageable pageable) {
        return tourRepository.getByTourGroupIn(tourGroups, pageable);
    }

    @Override
    public Set<String> getDeparturePlaces() {
        Set<String> result = new LinkedHashSet<>();
        List<Tour> tours = tourRepository.findAll();
        tours.forEach(tour -> {
            result.add(tour.getDeparturePlace());
        });
        return result;
    }


    public Set<String> getDurations() {
        Set<String> result = new LinkedHashSet<>();
        List<Tour> tours = tourRepository.findAll();
        tours.forEach(tour -> {
            result.add(tour.getDuration());
        });
        return result;
    }

    private Tour requireOne(Integer id) {
        return tourRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }


}
