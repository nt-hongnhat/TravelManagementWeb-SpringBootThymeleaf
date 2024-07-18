package com.nthn.springbootthymeleaf.DTO;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingStatisticsResult {
  private int numberOfItems;
  private BigDecimal total;
}
