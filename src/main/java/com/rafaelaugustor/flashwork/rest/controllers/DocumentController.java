package com.rafaelaugustor.flashwork.rest.controllers;

import com.rafaelaugustor.flashwork.rest.dtos.request.DigitalContractRequestDTO;
import com.rafaelaugustor.flashwork.services.DigitalContractService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.rafaelaugustor.flashwork.utils.Constants.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT + "/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DigitalContractService documentGenerator;

    @PostMapping("/generate")
    public void generateDocument(HttpServletResponse response, @RequestBody DigitalContractRequestDTO request) {
        documentGenerator.generateDocument(response, request);
    }
}
