package com.nthn.springbootthymeleaf.service.impl;

import com.nthn.springbootthymeleaf.repository.BookingRepository;
import com.nthn.springbootthymeleaf.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {
    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public List<Object[]> getRevenueMonthly(int month, int year) {
        return bookingRepository.getRevenueMonthly(month, year);
    }


    @Override
    public List<Object[]> getRevenueAnnual(int year) {
        return bookingRepository.getRevenueAnnual(year);
    }

    @Override
    public List<Object[]> getRevenueMonthlyByYear(int year) {
        List<Object[]> objects = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            objects.add(bookingRepository.getRevenueMonthly(i, year).get(0));
        }
        return objects;
    }
}
