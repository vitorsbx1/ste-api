package com.vitorsb.itau.ste_api.service.impl;

import com.vitorsb.itau.ste_api.exception.UnprocessableEntityException;
import com.vitorsb.itau.ste_api.request.TransactionRequest;
import com.vitorsb.itau.ste_api.response.TransactionResponse;
import com.vitorsb.itau.ste_api.service.StatisticService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Mock
    private StatisticService statisticService;

    @Test
    void shouldSaveTransactionWhenValid() {

        TransactionRequest transaction = new TransactionRequest(new BigDecimal("100.00"), OffsetDateTime.now(ZoneOffset.UTC));

        transactionService.saveTransaction(transaction);

         verify(statisticService).saveTransactions(any(TransactionResponse.class));

    }

    @Test
    void shouldThrowExceptionWhenTransactionIsNull(){

        assertThrows(UnprocessableEntityException.class, () -> transactionService.saveTransaction(null));

    }

    @Test
    void shouldThrowExceptionWhenTransactionValueIsNull(){

        TransactionRequest transaction = new TransactionRequest(null, OffsetDateTime.now(ZoneOffset.UTC));

        assertThrows(UnprocessableEntityException.class, () -> transactionService.saveTransaction(transaction));

    }

    @Test
    void shouldThrowExceptionWhenTransactionDateIsNull(){

        TransactionRequest transaction = new TransactionRequest(new BigDecimal("100.00"), null);

        assertThrows(UnprocessableEntityException.class, () -> transactionService.saveTransaction(transaction));
    }


    @Test
    void shouldThrowExceptionWhenTransactionValueIsNegative(){

        TransactionRequest transaction = new TransactionRequest(new BigDecimal("-100.00"), OffsetDateTime.now(ZoneOffset.UTC));

        assertThrows(UnprocessableEntityException.class, () -> transactionService.saveTransaction(transaction));
    }

    @Test
    void shouldThrowExceptionWhenTransactionDateIsAfterNow(){

        OffsetDateTime date = OffsetDateTime.now(ZoneOffset.UTC).plusDays(1);

        TransactionRequest transaction = new TransactionRequest(new BigDecimal("100.00"), date);

        assertThrows(UnprocessableEntityException.class, () -> transactionService.saveTransaction(transaction));
    }

    @Test
    void shouldDeleteTransaction(){
        transactionService.deleteTransaction();

        verify(statisticService).deleteTransactions();
    }



}