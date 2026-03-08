package br.com.fit.contract.exceptions;

public class ContractAlreadyInactiveException extends RuntimeException {
  public ContractAlreadyInactiveException(String message) {
    super(message);
  }
}
