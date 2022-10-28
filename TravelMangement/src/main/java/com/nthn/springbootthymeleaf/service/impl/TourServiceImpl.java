package com.nthn.springbootthymeleaf.service.impl;

import com.nthn.springbootthymeleaf.DTO.SearchTourDTO;
import com.nthn.springbootthymeleaf.pojo.Tour;
import com.nthn.springbootthymeleaf.pojo.TourGroup;
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

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
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

    @Override
    public Page<Tour> getTourPageBySearchTour(SearchTourDTO searchTourDTO, Pageable pageable) {

        if (searchTourDTO == null) {
            return tourRepository.findAll(pageable);
        }
        String departurePlace = searchTourDTO.getDeparturePlace();
        String destinationPlace = searchTourDTO.getDestinationPlace();
        LocalDate fromDate = searchTourDTO.getFromDepartureDate();
        LocalDate toDate = searchTourDTO.getToDepartureDate();

        if (searchTourDTO.getRangePrice() == null) {
            return tourRepository.searchTours(departurePlace, destinationPlace,
                    fromDate, toDate, pageable);
        }

        BigDecimal fromPrice = BigDecimal.valueOf(searchTourDTO.getFromPrice());
        BigDecimal toPrice = BigDecimal.valueOf(searchTourDTO.getToPrice());

        return tourRepository.searchTours(departurePlace, destinationPlace,
                fromDate, toDate, fromPrice, toPrice, pageable);
    }


    @Override
    public Page<Tour> getTourPageByCategory(String categoryLink, Pageable pageable) {
        return tourRepository.findByCategory(categoryLink, pageable);
    }

    @Override
    public Page<Tour> getTourPageByTourGroup(String tourGroupLink, Pageable pageable) {
        return tourRepository.findByTourGroup(tourGroupLink, pageable);
    }


    /**
     * @param departurePlace
     * @param destinationPlace
     * @param duration
     * @param tourGroupLink
     * @param pageable
     * @return
     */
    @Override
    public Page<Tour> getTourPage(String departurePlace, String destinationPlace, String duration, String tourGroupLink, Pageable pageable) {
        return null;
    }

    /**
     * @param params
     * @param pageable
     * @return
     */
    @Override
    public Page<Tour> getTourPage(Map<String, String> params, Pageable pageable) {
        return null;
    }

//    /**
//     * Tìm kiếm tour theo nhiều tiêu chí
//     *
//     * @param departurePlace   : nơi khởi hành
//     * @param destinationPlace : nơi đến
//     * @param duration         : thời gian đi
//     * @param pageable         : page, size
//     * @return page
//     */
//    @Override
//    public Page<Tour> getTourPage(String departurePlace, String destinationPlace, String duration, String tourGroupLink, Pageable pageable) {
//        return tourRepository.searchTours(departurePlace, destinationPlace, duration, tourGroupLink, pageable);
//    }

//    @Override
//    public Page<Tour> getTourPage(Map<String, String> params, Pageable pageable) {
//        int page, size, totalPages, totalItems;
//
//        Page<Tour> tourPage;
//        List<Tour> tours;
//        List<Integer> pageNumbers;
//        String price, fromPrice, toPrice, departurePlace, destinationPlace, duration;
//
//
//        duration = params.get("daterange");
//        String[] date = duration.split("-");
//        System.out.println(date[0].trim());
//        System.out.println(date[1].trim());
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
//        LocalDate fromDate = LocalDate.parse(date[0].trim(), formatter);
//        LocalDate toDate = LocalDate.parse(date[1].trim(), formatter);
//
//
//        page = Integer.parseInt(params.getOrDefault("page", "1"));
//        size = Integer.parseInt(params.getOrDefault("size", "6"));
//
//
//        departurePlace = params.getOrDefault("departure", "");
//        destinationPlace = params.getOrDefault("destination", "");
//        duration = params.getOrDefault("duration", "");
//        Integer numberPeople = Integer.valueOf(params.getOrDefault("numberPeople", "1"));
//        return tourRepository.searchTours(departurePlace, destinationPlace, duration, numberPeople, pageable);
//    }


    /**
     * Lấy danh sách tour theo danh mục tour
     *
     * @param tourGroups
     * @param pageable
     * @return
     */
    @Override
    public Page<Tour> getTourPageByTourGroup(Set<TourGroup> tourGroups, Pageable pageable) {
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
