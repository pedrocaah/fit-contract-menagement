package br.com.fit.contract.controller;

import br.com.fit.contract.dtos.CreateContractRequest;
import br.com.fit.contract.service.ContractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/contract")
public class ContractController {


    private static final Logger LOGGER = LoggerFactory.getLogger(ContractController.class);
    private final ContractService service;

    public ContractController(ContractService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createContract(@RequestBody CreateContractRequest request) {
        LOGGER.info("Receiving request to create contract to enterprise: {}", request.enterpriseName());
        return ResponseEntity.created(URI.create(String.format("/contract/%s", service.createContract(request).contractNumber()))).build();
    }
}
