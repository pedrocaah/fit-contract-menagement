package br.com.fit.contract.exceptions.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


public record ErrorResponse(@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime errorMoment,
                            Integer errorCode,
                            HttpStatus errorStatus,
                            String errorCause,
                            String uriLocation) {
}
