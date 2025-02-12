package com.vitorsb.itau.ste_api.service.impl;

import com.vitorsb.itau.ste_api.exception.UnprocessableEntityException;
import com.vitorsb.itau.ste_api.request.TransactionRequest;
import com.vitorsb.itau.ste_api.response.TransactionResponse;
import com.vitorsb.itau.ste_api.service.StatisticService;
import com.vitorsb.itau.ste_api.service.TransactionService;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {


    private final StatisticService statisticService;

    public TransactionServiceImpl(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    public void saveTransaction(TransactionRequest transaction){
        validateTransaction(transaction);
        TransactionResponse transactionResponse = new TransactionResponse(transaction.valor(), transaction.dataHora());
        statisticService.saveTransactions(transactionResponse);
    }

    public void deleteTransaction(){
        statisticService.deleteTransactions();
    }

    public void validateTransaction(TransactionRequest transaction){
        validateValuesNotNull(transaction);
        validateDateTransaction(transaction);

    }

    public void validateValuesNotNull(TransactionRequest transaction){
        if (transaction == null){
            throw new UnprocessableEntityException("Transaction nao pode ser vazia ou nula.");
        }
        if (transaction.valor() == null){
            throw new UnprocessableEntityException("Valor da transacao nao pode ser vazia ou nula.");

        }else if(transaction.valor().signum() == -1){
            throw new UnprocessableEntityException("Valor da transacao nao pode ser um valor negativo.");
        }
        if(transaction.dataHora() == null){
            throw new UnprocessableEntityException("Data/Hora da transacao nao pode ser vazia ou nula.");
        }
    }

    public void validateDateTransaction(TransactionRequest transaction){
        OffsetDateTime date = OffsetDateTime.now(ZoneOffset.UTC);

        if(transaction.dataHora().isAfter(date)){
            throw new UnprocessableEntityException("Data/Hora da transacao nao deve acontecer no futuro.");
        }
    }
}
