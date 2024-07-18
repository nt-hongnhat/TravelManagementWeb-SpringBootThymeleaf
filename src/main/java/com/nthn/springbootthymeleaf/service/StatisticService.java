package com.nthn.springbootthymeleaf.service;

import java.util.List;

public interface StatisticService {


    List<Object[]> getRevenueMonthly(int month, int year);

    List<Object[]> getRevenueAnnual(int year);

    List<Object[]> getRevenueMonthlyByYear(int year);
}
