package com.nthn.springbootthymeleaf.service.impl;

import com.nthn.springbootthymeleaf.dto.SearchTourDTO;
import com.nthn.springbootthymeleaf.entity.DepartureDate;
import com.nthn.springbootthymeleaf.entity.Tour;
import com.nthn.springbootthymeleaf.entity.TourSchedule;
import com.nthn.springbootthymeleaf.entity.TourTicket;
import com.nthn.springbootthymeleaf.repository.TourGroupRepository;
import com.nthn.springbootthymeleaf.repository.TourRepository;
import com.nthn.springbootthymeleaf.repository.TourScheduleRepository;
import com.nthn.springbootthymeleaf.repository.TourTicketRepository;
import com.nthn.springbootthymeleaf.service.DepartureDateService;
import com.nthn.springbootthymeleaf.service.TourScheduleService;
import com.nthn.springbootthymeleaf.service.TourService;
import com.nthn.springbootthymeleaf.service.TourTicketService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    
    @Autowired
    private TourScheduleRepository tourScheduleRepository;
    
    @Autowired
    private TourScheduleService tourScheduleService;
    
    @Autowired
    private TourTicketService tourTicketService;
    
    @Autowired
    private DepartureDateService departureDateService;
    
    @Override
    public Tour save(Tour tour) {
        return tourRepository.save(tour);
    }
    
    
    @Override
    public Tour create(@NotNull Tour tour) {
        // Save tour_info
        Tour finalTour = tourRepository.save(tour);
        
        // Save tour_ticket
        TourTicket.getNames().forEach(group -> {
            TourTicket tourTicket = new TourTicket(group.getContent(), finalTour, 0);
            tourTicketRepository.save(tourTicket);
        });
        
        
        // Save tour_schedule
        int ordinal = Integer.parseInt(Arrays.stream(tour.getDuration().split(" ")).toList().get(0));
        for (int i = 0; i < ordinal; i++) {
            TourSchedule tourSchedule = new TourSchedule(i + 1, "null", "null", finalTour);
            tourScheduleRepository.save(tourSchedule);
        }
        
        // Save departure_date
        List<DepartureDate> departureDates = tour.getDepartureDates();
        
        departureDates.forEach(departureDate -> {
            if (!departureDate.getValueDate().isEmpty()) {
                String valueDate = departureDate.getValueDate();
                departureDateService.saveDepartureDate(new DepartureDate(valueDate, finalTour));
            }
        });
        
        return finalTour;
    }
    
    
    @Override
    public void delete(Integer id) {
        tourRepository.deleteById(id);
    }
    
    @Override
    public void update(Integer id, @NotNull Tour tour) {
        System.out.println("TOUR TICKET");
        Tour tour1 = tourRepository.findById(id).get();
        BeanUtils.copyProperties(tour, tour1);
        tourRepository.save(tour1);
    }
    
    @Override
    public Tour getById(Integer id) {
        return requireOne(id);
    }
    
    @Override
    public List<Tour> getTours() {
        return tourRepository.findAll();
    }
    
    
    @Override
    public Page<Tour> getTourPageBySearchTour(SearchTourDTO searchTourDTO, Pageable pageable) {
        if (searchTourDTO == null) {
            return tourRepository.findAll(pageable);
        }
        
        searchTourDTO.setDeparturePlace("%" + searchTourDTO.getDeparturePlace() + "%");
        searchTourDTO.setDestinationPlace("%" + searchTourDTO.getDestinationPlace() + "%");
        
        boolean hasSearchByDepartureDate = searchTourDTO.getRangeDate() != null && !searchTourDTO.getRangeDate()
                                                                                                 .isEmpty();
        boolean hasSearchByPrice = searchTourDTO.getRangePrice() != null && !searchTourDTO.getRangePrice().isEmpty();
        
        if (hasSearchByPrice) searchTourDTO.convertPrice();
        if (hasSearchByDepartureDate) searchTourDTO.convertDate();
        
        // Search: nơi khởi hành, nơi đến, ngày khởi hành, khoảng giá, số người
        if (hasSearchByDepartureDate && hasSearchByPrice)
            return tourRepository.searchTours(searchTourDTO.getDeparturePlace(), searchTourDTO.getDestinationPlace(),
                                              searchTourDTO.getFromDepartureDate(), searchTourDTO.getToDepartureDate(),
                                              searchTourDTO.getNumberPeople(), searchTourDTO.getFromPrice(),
                                              searchTourDTO.getToPrice(), pageable);
            // Search: nơi khởi hành, nơi đến, khoảng giá, số người
        else if (hasSearchByPrice)
            return tourRepository.searchTours(searchTourDTO.getDeparturePlace(), searchTourDTO.getDestinationPlace(),
                                              searchTourDTO.getNumberPeople(), searchTourDTO.getFromPrice(),
                                              searchTourDTO.getToPrice(), pageable);
            // Search: nơi khởi hành, nơi đến, ngày khởi hành, số người
        else if (hasSearchByDepartureDate)
            return tourRepository.searchTours(searchTourDTO.getDeparturePlace(), searchTourDTO.getDestinationPlace(),
                                              searchTourDTO.getFromDepartureDate(), searchTourDTO.getToDepartureDate(),
                                              searchTourDTO.getNumberPeople(), pageable);
        // Search: nơi khởi hành, nơi đến, số người
        return tourRepository.searchTours(searchTourDTO.getDeparturePlace(), searchTourDTO.getDestinationPlace(),
                                          searchTourDTO.getNumberPeople(), pageable);
    }
    
    
    @Override
    public Page<Tour> getTourPageByTourGroup(Integer id, Pageable pageable) {
        return tourRepository.findByTourGroupId(id, pageable);
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
    
    @Override
    public Tour getByName(String name) {
        return tourRepository.findByName(name);
    }
    
    private Tour requireOne(Integer id) {
        return tourRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
    
    
}
