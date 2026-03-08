package br.com.fit.contract.mappers;

import br.com.fit.contract.dtos.*;
import br.com.fit.contract.entities.ContractEntity;
import org.mapstruct.*;

@Mapper(
  componentModel = "spring",
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ContractMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "contractCreatedAt", ignore = true)
  ContractEntity toContractEntity(CreateContractRequest request);

  @Mapping(target = "numberCnpj", source = "numberCnpj", qualifiedByName = "maskCnpj")
  CreateContractResponse toCreateContractResponse(ContractEntity entity);

  @Mapping(target = "numberCnpj", source = "numberCnpj", qualifiedByName = "maskCnpj")
  AmendmentContractResponse toAmendmentContractResponse(ContractEntity entity);

  void updateEntity(AmendmentContractRequest request, @MappingTarget ContractEntity entity);

  @Mapping(target = "numberCnpj", source = "numberCnpj", qualifiedByName = "maskCnpj")
  GetContractResponse toGetContractResponse(ContractEntity entity);


  @Named("maskCnpj")
  default String maskCnpj(String numberCnpj) {
    StringBuilder x = new StringBuilder();
    return numberCnpj.replaceAll("^\\d{3}|\\d{2}$", "***");
  }
}
