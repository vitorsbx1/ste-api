package com.vitorsb.itau.ste_api.service;


import com.vitorsb.itau.ste_api.response.StatisticResponse;
import com.vitorsb.itau.ste_api.response.TransactionResponse;

import java.util.List;

public interface StatisticService {

    public StatisticResponse calculateStatistic();

    public void saveTransactions(TransactionResponse transactionsResponse);

    public void deleteTransactions();
}
