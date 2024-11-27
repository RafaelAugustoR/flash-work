package com.rafaelaugustor.flashwork.rest.controllers;

import com.rafaelaugustor.flashwork.rest.dtos.request.SignatureRequestDTO;
import com.rafaelaugustor.flashwork.rest.dtos.response.DigitalContractResponseDTO;
import com.rafaelaugustor.flashwork.services.DigitalContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @PostMapping("/{contractId}/sign")
    public ResponseEntity<DigitalContractResponseDTO> addSignatureToContract(@PathVariable UUID contractId, Principal principal, @RequestBody SignatureRequestDTO signatureRequest) throws FileNotFoundException {
        return ResponseEntity.ok(digitalContractService.addSignatureToContract(contractId, principal, signatureRequest));
    }

    @GetMapping("/{contractId}")
    public ResponseEntity<DigitalContractResponseDTO> getContractById(@PathVariable UUID contractId) throws FileNotFoundException {
        DigitalContractResponseDTO contract = digitalContractService.getContractById(contractId);
        return  ResponseEntity.ok(contract);
    }

    @GetMapping("/user}")
    public ResponseEntity<Page<DigitalContractResponseDTO>> getContractsByUser(Principal principal, Pageable pageable) {
        Page<DigitalContractResponseDTO> contracts = digitalContractService.findContractsByUser(principal, pageable);
        return  ResponseEntity.ok(contracts);
    }

}