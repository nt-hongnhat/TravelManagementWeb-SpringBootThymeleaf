package com.nthn.springbootthymeleaf.validator;

import com.nthn.springbootthymeleaf.constants.ValidatorConstants.Field;
import com.nthn.springbootthymeleaf.constants.ValidatorConstants.Table;
import com.nthn.springbootthymeleaf.entity.Tour;
import com.nthn.springbootthymeleaf.service.TourService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class TourValidator implements Validator {

  private final GlobalValidate globalValidate;

  private final TourService tourService;

  @Override
  public boolean supports(@NotNull Class<?> clazz) {
    return clazz == Tour.class;
  }

  @Override
  public void validate(@NotNull Object target, @NotNull Errors errors) {
    final Tour tour = (Tour) target;

    if (Objects.isNull(tour.getId())) {
      if (tour.getName().length() < 6) {
        errors.rejectValue(
            Field.Tour.NAME, "tour_info.name_LENGTH", "Tên tour phải có nhất 6 ký tự.");
      }
      if (tour.getName().length() > 255) {
        errors.rejectValue(Field.Tour.NAME, "tour_info.name_TOO_LONG", "Tên tour quá dài.");
      }

      globalValidate.validateNotNull(
          tour.getUnitPrice(), Table.TOUR, Field.Tour.UNIT_PRICE, errors);
      globalValidate.validateNotNull(tour.getItinerary(), Table.TOUR, Field.Tour.ITINERARY, errors);
      globalValidate.validateNotNull(tour.getDuration(), Table.TOUR, Field.Tour.DURATION, errors);
      globalValidate.validateNotNull(
          tourService.getByName(tour.getName()), Table.TOUR, Field.Tour.NAME, errors);
    }
  }
}
