package br.com.fit.contract.exceptions;

public class ContractNotFound extends RuntimeException {
    public ContractNotFound(String message) {
        super(message);
    }
}
