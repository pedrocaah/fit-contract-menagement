package br.com.fit.contract.mappers;

import br.com.fit.contract.dtos.CreateContractRequest;
import br.com.fit.contract.dtos.CreateContractResponse;
import br.com.fit.contract.entities.ContractEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ContractMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contractCreatedAt", ignore = true)
    @Mapping(target = "contractNumber", expression = "java(new BigDecimal(request.numberCnpj().substring(0, 12)))")
    ContractEntity toContractEntity(CreateContractRequest request);

    @Mapping(target = "numberCnpj", source = "numberCnpj", qualifiedByName = "maskCnpj")
    CreateContractResponse toCreateContractResponse(ContractEntity entity);

    @Named("maskCnpj")
    default String maskCnpj(String numberCnpj) {
        if (numberCnpj == null) return null;
        return numberCnpj.replaceAll("^\\d{3}|\\d{2}$", "***");
    }
}
