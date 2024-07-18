package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.DTO.BookingStatisticsResult;
import java.util.List;

public interface StatisticService {


    List<BookingStatisticsResult> getRevenueAnnual(int year);

   BookingStatisticsResult getRevenueMonthly(int month, int year);

    List<BookingStatisticsResult> getRevenueMonthlyByYear(int year);
}
