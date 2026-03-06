package br.com.fit.contract.service;

import br.com.fit.contract.dtos.*;
import br.com.fit.contract.entities.ContractEntity;
import br.com.fit.contract.enums.ContractStatusEnum;
import br.com.fit.contract.exceptions.ContractExistsException;
import br.com.fit.contract.exceptions.ContractNotFound;
import br.com.fit.contract.mappers.ContractMapper;
import br.com.fit.contract.repository.ContractRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
    public AmendmentContractResponse amendmentContract(String contractNumber, AmendmentContractRequest request) {

        ContractEntity entity = getContractEntity(contractNumber);

        mapper.updateEntity(request, entity);
        LOGGER.info("Contrato: {} aditado com sucesso!", contractNumber);
        return mapper.toAmendmentContractResponse(entity);
    }

    public GetContractResponse getContractByContractNumber(String contractNumber) {
        ContractEntity entity = getContractEntity(contractNumber);
        var response = mapper.toGetContractResponse(entity);
        LOGGER.debug("Contrato: [{}] encontrado!", entity);
        return response;
    }

    @Transactional
    public void deleteContract(String contractNumber) {
        ContractEntity entity = getContractEntity(contractNumber);
        entity.setStatus("INATIVO");
        LOGGER.info("Contrato: {} passa a ter status inativo!", contractNumber);
    }

    private void validateNoActiveContractExists(ContractEntity entity) {
        if (repository.existsByNumberCnpjAndStatus(entity.getNumberCnpj(), entity.getStatus())) {
            LOGGER.error("Já existe um contrato ativo para essa empresa, para alterações no contrato realize um aditamento");
            throw new ContractExistsException("Já existe um contrato ativo para essa empresa, para alterações no contrato realize um aditamento");
        }
    }

    private ContractEntity getContractEntity(String contractNumber) {
        return repository.findByContractNumber(contractNumber)
                .orElseThrow(() -> new ContractNotFound(
                        String.format("Nenhum contrato encontrado com número %s", contractNumber)
                ));
    }
}
