package com.nthn.springbootthymeleaf.VO;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class DestinationUpdateVO extends DestinationVO implements Serializable {
    private static final long serialVersionUID = 1L;

}
