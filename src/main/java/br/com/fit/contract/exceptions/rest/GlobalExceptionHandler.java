package br.com.fit.contract.exceptions.rest;

import br.com.fit.contract.exceptions.ContractExistsException;
import br.com.fit.contract.exceptions.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ContractExistsException.class)
    public ResponseEntity<ErrorResponse> handleContractExists(ContractExistsException ex) {
        var erro = buildErrorResponse(LocalDateTime.now(), 400, HttpStatus.BAD_REQUEST, ex.getMessage(), "/contracts");
        return ResponseEntity.badRequest().body(erro);
    }

    private ErrorResponse buildErrorResponse(LocalDateTime errorMoment,
                                             Integer errorCode,
                                             HttpStatus errorStatus,
                                             String errorCause,
                                             String uriLocation) {

        return new ErrorResponse(errorMoment, errorCode, errorStatus, errorCause, uriLocation);
    }
}
