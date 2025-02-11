package com.vitorsb.itau.ste_api.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record TransactionResponse(BigDecimal valor, OffsetDateTime dataHora) {
}
