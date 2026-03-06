package br.com.fit.contract.controller;

import br.com.fit.contract.dtos.*;
import br.com.fit.contract.service.ContractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/contracts")
public class ContractController {


    private static final Logger LOGGER = LoggerFactory.getLogger(ContractController.class);
    private final ContractService service;

    public ContractController(ContractService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CreateContractResponse> createContract(@RequestBody CreateContractRequest request) {
        LOGGER.info("Recebendo requisição de criação de contrato para empresa: {}", request.enterpriseName());
        var response = service.createContract(request);
        return ResponseEntity.created(URI.create(String.format("/contract/%s", response.contractNumber()))).body(response);
    }

    @PutMapping("/{contractNumber}")
    public ResponseEntity<AmendmentContractResponse> amendmentContract(@PathVariable String contractNumber,
                                                                       @RequestBody AmendmentContractRequest request) {
        LOGGER.info("Recebendo requição para aditamento do contrato: {}", contractNumber);
        return ResponseEntity.ok().body(service.amendmentContract(contractNumber, request));
    }

    @GetMapping("/{contractNumber}")
    public ResponseEntity<GetContractResponse> getContractByContractNumber(@PathVariable String contractNumber) {
        LOGGER.info("Recebendo requisição para buscar contrato com número: {}", contractNumber);
        return ResponseEntity.ok(service.getContractByContractNumber(contractNumber));
    }

    @PatchMapping("/{contractNumber}")
    public ResponseEntity<Void> deleteContract(@PathVariable String contractNumber) {
        LOGGER.info("Recebendo requisição para deletar contrato com número: {}", contractNumber);
        service.deleteContract(contractNumber);
        return ResponseEntity.noContent().build();
    }
}
