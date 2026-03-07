package br.com.fit.contract.dtos;

import java.time.Instant;

public record GetContractResponse(String numberCnpj,
                                  String enterpriseName,
                                  String enterpriseAdress,
                                  String legalName,
                                  String nameCEO,
                                  Instant contractCreatedAt,
                                  String contractNumber,
                                  String status) {
}
