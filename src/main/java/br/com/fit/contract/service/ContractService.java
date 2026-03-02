package br.com.fit.contract.service;

import br.com.fit.contract.dtos.CreateContractRequest;
import br.com.fit.contract.dtos.CreateContractResponse;
import br.com.fit.contract.mappers.ContractMapper;
import br.com.fit.contract.repository.ContractRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static br.com.fit.contract.utils.DateUtils.formatBR;

@Service
public class ContractService {


    private static final Logger LOGGER = LoggerFactory.getLogger(ContractService.class);
    private final ContractRepository repository;
    private final ContractMapper mapper;

    public ContractService(ContractRepository repository, ContractMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public CreateContractResponse createContract(CreateContractRequest request) {

        var entity = mapper.toContractEntity(request);
        repository.save(entity);
        var response = mapper.toCreateContractResponse(entity);
        LOGGER.info("Contrato emitido com sucesso! Número do Contrato: {}, pro CNPJ: {}, Emitido em: {}", response.contractNumber(), response.numberCnpj(),
                formatBR(response.contractCreatedAt()));
        return response;
    }
}
