package com.nthn.springbootthymeleaf.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SearchHistoryDTO {
    
    private LocalDate fromDate;
    
    private LocalDate toDate;
    
    private String statusPayment;
    
    @Override
    public String toString() {
        return "SearchHistoryDTO{" + "fromDate=" + fromDate + ", toDate=" + toDate + ", statusPayment='" + statusPayment + '\'' + '}';
    }
}
