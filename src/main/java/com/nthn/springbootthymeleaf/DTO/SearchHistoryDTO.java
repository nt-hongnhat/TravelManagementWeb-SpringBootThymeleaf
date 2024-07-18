package com.nthn.springbootthymeleaf.dto;

import java.time.LocalDate;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SearchHistoryDTO {
    
    private LocalDate fromDate;
    
    private String statusPayment;
    
    private LocalDate toDate;
    
    @Override
    public String toString() {
        return "SearchHistoryDTO{" + "fromDate=" + fromDate + ", toDate=" + toDate + ", statusPayment='" + statusPayment + '\'' + '}';
    }
}
