package com.nthn.springbootthymeleaf.VO;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class BookingUpdateVO extends BookingVO implements Serializable {
    private static final long serialVersionUID = 1L;

}
