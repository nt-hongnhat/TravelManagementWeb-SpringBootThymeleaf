package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.entity.DepartureDate;

public interface DepartureDateService {
    DepartureDate getDepartureDate(String departureDate);
    DepartureDate saveDepartureDate(DepartureDate departureDate);
    DepartureDate create(DepartureDate departureDate);
    void delete(int departureDateId);
    void update(int id, DepartureDate departureDate);
}
