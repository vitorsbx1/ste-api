package com.vitorsb.itau.ste_api.request;


import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record TransactionRequest(BigDecimal valor, OffsetDateTime dataHora) {
}
