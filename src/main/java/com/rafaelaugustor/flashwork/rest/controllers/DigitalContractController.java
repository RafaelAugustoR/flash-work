package com.rafaelaugustor.flashwork.rest.controllers;

import com.rafaelaugustor.flashwork.rest.dtos.request.DigitalContractRequestDTO;
import com.rafaelaugustor.flashwork.rest.dtos.request.SignatureRequestDTO;
import com.rafaelaugustor.flashwork.services.DigitalContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.security.Principal;
import java.util.UUID;

import static com.rafaelaugustor.flashwork.utils.Constants.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT + "/contracts")
@RequiredArgsConstructor
@CrossOrigin
public class DigitalContractController {

    private final DigitalContractService digitalContractService;

    @PostMapping("/{serviceId}/generate")
    public void generateDocument(@RequestBody DigitalContractRequestDTO request, @PathVariable UUID serviceId) {
        digitalContractService.generateDocument(request, serviceId);
    }

    @PostMapping("/{contractId}/sign")
    public ResponseEntity<String> addSignatureToContract(@PathVariable UUID contractId, Principal principal, @RequestBody SignatureRequestDTO signatureRequest) throws FileNotFoundException {
        digitalContractService.addSignatureToContract(contractId, principal, signatureRequest);
        return ResponseEntity.ok("Assinatura adicionada com sucesso!");
    }
}