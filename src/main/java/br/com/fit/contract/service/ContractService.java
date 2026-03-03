package br.com.fit.contract.service;

import br.com.fit.contract.dtos.CreateContractRequest;
import br.com.fit.contract.dtos.CreateContractResponse;
import br.com.fit.contract.entities.ContractEntity;
import br.com.fit.contract.enums.ContractStatusEnum;
import br.com.fit.contract.exceptions.ContractExistsException;
import br.com.fit.contract.mappers.ContractMapper;
import br.com.fit.contract.repository.ContractRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static br.com.fit.contract.utils.DateUtils.formatBR;
import static br.com.fit.contract.utils.StringUtils.maskCnpj;

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
        entity.setStatus(ContractStatusEnum.ATIVO.name());
        validateNoActiveContractExists(entity);
        repository.save(entity);
        var response = mapper.toCreateContractResponse(entity);
        LOGGER.info("Contrato emitido com sucesso! Número do Contrato: {}, pro CNPJ: {}, Emitido em: {}", entity.getContractNumber(), maskCnpj(entity.getNumberCnpj()),
                formatBR(entity.getContractCreatedAt()));
        return response;
    }

    private void validateNoActiveContractExists(ContractEntity entity){
        if (repository.existsByNumberCnpjAndStatus(entity.getNumberCnpj(), entity.getStatus())){
            LOGGER.error("Já existe um contrato ativo para essa empresa, para alterações no contrato realize um aditamento");
            throw new ContractExistsException("Já existe um contrato ativo para essa empresa, para alterações no contrato realize um aditamento");
        }
    }
}
