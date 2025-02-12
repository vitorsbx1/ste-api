package com.vitorsb.itau.ste_api.service.impl;

import com.vitorsb.itau.ste_api.response.StatisticResponse;
import com.vitorsb.itau.ste_api.response.TransactionResponse;
import com.vitorsb.itau.ste_api.service.StatisticService;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {

    private final List<TransactionResponse> transactions = new ArrayList<>();

    public void saveTransactions(TransactionResponse transaction){
       transactions.add(transaction);
    }

    public void deleteTransactions(){
        transactions.removeAll(transactions.stream().toList());
    }


    public StatisticResponse calculateStatistic(){

        OffsetDateTime dateTime = OffsetDateTime.now(ZoneOffset.UTC);
        DoubleSummaryStatistics statisticsCalculate = new DoubleSummaryStatistics();

        for (TransactionResponse transaction : transactions) {
            Duration duration = Duration.between(transaction.dataHora(), dateTime);
            if (duration.toMinutes() <= 1) {
                statisticsCalculate.accept(transaction.valor().doubleValue());
            }
        }

        return new StatisticResponse(
                statisticsCalculate.getCount(),
                statisticsCalculate.getSum(),
                statisticsCalculate.getAverage(),
                statisticsCalculate.getCount() > 0 ? statisticsCalculate.getMin() : 0.00,
                statisticsCalculate.getCount() > 0 ? statisticsCalculate.getMax() : 0.00
                );
    }


}
