package com.nthn.springbootthymeleaf.controller;

import com.nthn.springbootthymeleaf.entity.Booking;
import com.nthn.springbootthymeleaf.service.BookingService;
import com.nthn.springbootthymeleaf.service.PaymentService;
import com.nthn.springbootthymeleaf.service.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
public class PaypalController {
    
    public static final String SUCCESS_URL = "pay/success";
    
    public static final String CANCEL_URL = "pay/cancel";
    
    @Autowired
    private PaypalService service;
    
    @Autowired
    private BookingService bookingService;
    
    @Autowired
    private PaymentService paymentService;
    
    @PostMapping("/booking/{id}")
    public String payment(@ModelAttribute("payment") com.nthn.springbootthymeleaf.entity.Payment payment,
                          @PathVariable int id) {
        payment = paymentService.findByMethod(payment.getMethod());
        Booking booking = bookingService.getById(id);
        payment.setTotal(booking.getTotal().doubleValue());
        if (Objects.equals(payment.getMethod(), "CASH")) {
            booking.setPayment(payment);
            bookingService.update(booking.getId(), booking);
            return "redirect:/booking/" + id + "?paysuccess";
        } else {
            try {
                Payment paymentPaypal = service.createPayment(payment.getTotal(), payment.getCurrency(),
                                                              payment.getMethod(), payment.getIntent(),
                                                              "http://localhost:8080/Travel/" + CANCEL_URL,
                                                              "http://localhost:8080/Travel/" + SUCCESS_URL + "?id=" + id);
                for (Links link : paymentPaypal.getLinks()) {
                    if (link.getRel().equals("approval_url")) {
                        return "redirect:" + link.getHref();
                    }
                }
                
            } catch (PayPalRESTException e) {
                e.printStackTrace();
                return "redirect:/booking/{id}?error";
            }
        }
        return "redirect:/booking/{id}?error";
    }
    
    @GetMapping(value = CANCEL_URL)
    public String cancelPay() {
        return "views/booking/paycancel";
    }
    
    @GetMapping(value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId,
                             @RequestParam("id") String id) {
        try {
            Payment payment = service.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                Booking booking;
                booking = bookingService.getById(Integer.valueOf(id));
                System.out.println(booking);
                booking.setPayment(paymentService.findByMethod("PAYPAL"));
                bookingService.update(booking.getId(), booking);
                System.out.println(booking);
                return "redirect:/booking/" + id + "?paysuccess";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/booking/{id}/pay?error1";
    }
    
}