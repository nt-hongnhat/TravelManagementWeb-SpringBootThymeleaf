package com.nthn.springbootthymeleaf.service;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaypalService {
	
	private final APIContext apiContext;
	
	public Payment createPayment(Double total, String currency, String method, String intent,
			String cancelUrl, String successUrl) throws PayPalRESTException {
		
		return new Payment().setIntent(intent).setPayer(new Payer().setPaymentMethod(method))
				.setTransactions(List.of((Transaction) new Transaction().setAmount(
						new Amount().setCurrency(currency).setTotal(
								String.format(Locale.forLanguageTag(currency), Format.DOUBLE,
										new BigDecimal(total / Conversion.VND_TO_USD).setScale(2,
														RoundingMode.HALF_UP)
												.doubleValue())))))
				.setRedirectUrls(new RedirectUrls().setCancelUrl(cancelUrl).setReturnUrl(successUrl))
				.create(apiContext);
	}
	
	public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
		return new Payment().setId(paymentId)
				.execute(apiContext, new PaymentExecution().setPayerId(payerId));
	}
}
