package br.com.fit.contract.exceptions;

public class ContractExistsException extends RuntimeException {
    public ContractExistsException(String message) {
        super(message);
    }
}
