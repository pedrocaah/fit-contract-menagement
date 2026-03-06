package br.com.fit.contract.dtos;

public record AmendmentContractRequest(
        String enterpriseName,
        String legalName,
        String enterpriseAdress,
        String nameCEO) {
}
