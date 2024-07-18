package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.entity.Booking;
import com.nthn.springbootthymeleaf.service.BookingService;
import com.nthn.springbootthymeleaf.service.PaymentService;
import com.nthn.springbootthymeleaf.service.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PaypalController {

  public static final String CANCEL_URL = "pay/cancel";
  public static final String SUCCESS_URL = "pay/success";
  private final BookingService bookingService;
  private final PaymentService paymentService;
  private final PaypalService service;

  @GetMapping(value = CANCEL_URL)
  public String cancelPay() {
    return "views/booking/paycancel";
  }

  @PostMapping("/booking/{id}")
  public String payment(
      @ModelAttribute com.nthn.springbootthymeleaf.entity.Payment payment, @PathVariable int id) {
    payment = paymentService.findByMethod(payment.getMethod());
    final Booking booking = bookingService.getById(id);

    // Set payment details to booking object and update the booking in the database
    payment.setTotal(booking.getTotal().doubleValue());

    // Check if payment method is CASH
    if (Objects.equals(payment.getMethod(), "CASH")) {
      bookingService.update(booking.getId(), booking.setPayment(payment));
      return "redirect:/booking/" + id + "?paysuccess";
    } else {
      try {
        final Payment paymentPaypal =
            service.createPayment(
                payment.getTotal(),
                payment.getCurrency(),
                payment.getMethod(),
                payment.getIntent(),
                "http://localhost:8080/Travel/" + CANCEL_URL,
                "http://localhost:8080/Travel/" + SUCCESS_URL + "?id=" + id);
        for (Links link : paymentPaypal.getLinks()) {
          if (link.getRel().equals("approval_url")) {
            return "redirect:" + link.getHref();
          }
        }
      } catch (PayPalRESTException e) {
        return "redirect:/booking/{id}?error";
      }
    }
    return "redirect:/booking/{id}?error";
  }

  @GetMapping(value = SUCCESS_URL)
  public String successPay(
      @RequestParam String paymentId,
      @RequestParam("PayerID") String payerId,
      @RequestParam String id) {
    try {
      if (service.executePayment(paymentId, payerId).getState().equals("approved")) {
        final Booking booking = bookingService.getById(Integer.valueOf(id));

        bookingService.update(
            booking.getId(), booking.setPayment(paymentService.findByMethod("PAYPAL")));

        return "redirect:/booking/" + id + "?paysuccess";
      }
    } catch (PayPalRESTException e) {
      log.error("Error updating {}", e.getMessage());
    }
    return "redirect:/booking/{id}/pay?error1";
  }
}
