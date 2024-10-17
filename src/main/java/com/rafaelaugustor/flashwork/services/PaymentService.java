package com.rafaelaugustor.flashwork.services;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.rafaelaugustor.flashwork.exception.MercadoPagoException;
import com.rafaelaugustor.flashwork.rest.dtos.request.PixPaymentRequestDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.PixPaymentResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final WalletService walletService;

    public PixPaymentResponseDTO processPixPayment(PixPaymentRequestDTO request, Principal principal) {
        try {
            MercadoPagoConfig.setAccessToken();

            PaymentClient paymentClient = new PaymentClient();

            PaymentCreateRequest paymentCreateRequest =
                    PaymentCreateRequest.builder()
                            .transactionAmount(request.getTransactionAmount())
                            .description(request.getDescription())
                            .paymentMethodId("pix")
                            .payer(
                                    PaymentPayerRequest.builder()
                                            .email(request.getPayer().getEmail())
                                            .firstName(request.getPayer().getFirstName())
                                            .lastName(request.getPayer().getLastName())
                                            .identification(
                                                    IdentificationRequest.builder()
                                                            .type(request.getPayer().getIdentification().getType())
                                                            .number(request.getPayer().getIdentification().getNumber())
                                                            .build())
                                            .build())
                            .build();

            Payment createdPayment = paymentClient.create(paymentCreateRequest);

            if ("pending".equalsIgnoreCase(createdPayment.getStatus())) {

                walletService.deposit(principal, request.getTransactionAmount());
            }

            return new PixPaymentResponseDTO(
                    createdPayment.getId(),
                    String.valueOf(createdPayment.getStatus()),
                    createdPayment.getStatusDetail(),
                    createdPayment.getPointOfInteraction().getTransactionData().getQrCodeBase64(),
                    createdPayment.getPointOfInteraction().getTransactionData().getQrCode());
        } catch (MPApiException apiException) {
            System.out.println(apiException.getApiResponse().getContent());
            throw new MercadoPagoException(apiException.getApiResponse().getContent());
        } catch (MPException exception) {
            System.out.println(exception.getMessage());
            throw new MercadoPagoException(exception.getMessage());
        }
    }
//    public void processCardPayment(CardPaymentRequestDTO request){
//        MercadoPagoConfig.setAccessToken("APP_USR-1644617106519623-100920-d5bf419916c299ae1e401050103c5ba2-2029820000");
//
//        PaymentClient client = new PaymentClient();
//
//        PaymentCreateRequest createRequest =
//                PaymentCreateRequest.builder()
//                        .transactionAmount(request.getTransactionAmount())
//                        .token(request.getToken())
//                        .description("Card payment")
//                        .installments(request.getInstallments())
//                        .paymentMethodId(request.getPaymentMethodId())
//                        .payer(PaymentPayerRequest.builder()
//                                .email(request.getPayer().getEmail())
//                                .build())
//                        .build();
//
//        try {
//            Payment payment = client.create(createRequest);
//            System.out.println(payment);
//        } catch (MPApiException ex) {
//            System.out.printf(
//                    "MercadoPago Error. Status: %s, Content: %s%n",
//                    ex.getApiResponse().getStatusCode(), ex.getApiResponse().getContent());
//        } catch (MPException ex) {
//            ex.printStackTrace();
//        }
//    }
//
}