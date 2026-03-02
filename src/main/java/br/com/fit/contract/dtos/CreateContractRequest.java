package br.com.fit.contract.dtos;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;

public record CreateContractRequest(
        @CNPJ @Length(min = 14, max = 14) @NotNull String numberCnpj,
        @NotNull String enterpriseName,
        @NotNull String enterpriseAdress,
        @NotNull String clientFullName) {
}
