package com.nthn.springbootthymeleaf.service.impl;

import com.nthn.springbootthymeleaf.DTO.BookingStatisticsResult;
import com.nthn.springbootthymeleaf.repository.BookingRepository;
import com.nthn.springbootthymeleaf.service.StatisticService;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {
  private final BookingRepository bookingRepository;

  @Override
  public List<BookingStatisticsResult> getRevenueAnnual(int year) {

    return bookingRepository.getRevenueAnnual(year).stream()
        .map(
            statisticResults ->
                new BookingStatisticsResult(
                    (Integer) statisticResults[0], (BigDecimal) statisticResults[1]))
        .toList();
  }

  @Override
  public BookingStatisticsResult getRevenueMonthly(int month, int year) {
    return bookingRepository.getRevenueMonthly(month, year);
  }

  @Override
  public List<BookingStatisticsResult> getRevenueMonthlyByYear(int year) {

    return IntStream.range(1, 13).mapToObj(month -> getRevenueMonthly(month, year)).toList();
  }
}
