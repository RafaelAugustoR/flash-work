package com.rafaelaugustor.flashwork.rest.controllers;

import com.rafaelaugustor.flashwork.rest.dtos.request.DigitalContractRequestDTO;
import com.rafaelaugustor.flashwork.rest.dtos.request.SignatureRequestDTO;
import com.rafaelaugustor.flashwork.services.DigitalContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.UUID;

import static com.rafaelaugustor.flashwork.utils.Constants.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT + "/documents")
@RequiredArgsConstructor
@CrossOrigin
public class DigitalContractController {

    private final DigitalContractService documentGenerator;

    @PostMapping("/generate")
    public void generateDocument(@RequestBody DigitalContractRequestDTO request) {
        documentGenerator.generateDocument(request);
    }

    @PostMapping("/sign")
    public ResponseEntity<String> addSignatureToContract(@RequestParam UUID clientId,
                                                         @RequestParam UUID freelancerId,
                                                         @RequestBody SignatureRequestDTO signatureRequest) throws FileNotFoundException {
        documentGenerator.addSignatureToContract(clientId, freelancerId, signatureRequest);
        return ResponseEntity.ok("Assinatura adicionada com sucesso!");
    }
}