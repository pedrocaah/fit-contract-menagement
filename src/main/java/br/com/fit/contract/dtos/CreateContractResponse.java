package br.com.fit.contract.dtos;

import java.math.BigDecimal;
import java.time.Instant;

public record CreateContractResponse(String numberCnpj,
                                     String enterpriseName,
                                     String enterpriseAdress,
                                     String clientFullName,
                                     Instant contractCreatedAt,
                                     BigDecimal contractNumber) {
}
