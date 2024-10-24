package com.rafaelaugustor.flashwork.services;

import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.rafaelaugustor.flashwork.domain.entities.Credentials;
import com.rafaelaugustor.flashwork.exception.MercadoPagoException;
import com.rafaelaugustor.flashwork.rest.dtos.request.PixPaymentRequestDTO;
import com.rafaelaugustor.flashwork.rest.dtos.request.WithdrawRequestDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.PixPaymentResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final WalletService walletService;

    public PixPaymentResponseDTO processPixPayment(PixPaymentRequestDTO request, Principal principal) {
        try {
            MercadoPagoConfig.setAccessToken("ACCESS_TOKEN");

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

    public void processWithDraw(WithdrawRequestDTO request, Principal principal) {
        Credentials credentials = new Credentials();


        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("client_id", credentials.getClientId());
        options.put("client_secret", credentials.getClientSecret());
        options.put("certificate", credentials.getCertificate());
        options.put("sandbox", credentials.isSandbox());

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("idEnvio", request.getTransactionId());

        Map<String, Object> body = new HashMap<String, Object>();
        body.put("valor", request.getAmount());
        body.put("pagador", new HashMap<String, Object>().put("chave", "efipay@sejaefi.com.br"));
        body.put("favorecido", new HashMap<String, Object>().put("chave", request.getReceiverKey()));

        try {
            EfiPay efi = new EfiPay(options);

            Map<String, Object> response = efi.call("pixSend", params, body);
            System.out.println(response);
            walletService.deposit(principal, request.getAmount());

        } catch (EfiPayException e) {
            System.out.println(e.getError());
            System.out.println(e.getErrorDescription());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}