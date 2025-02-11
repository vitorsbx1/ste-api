package com.vitorsb.itau.ste_api.service;


import com.vitorsb.itau.ste_api.request.TransactionRequest;
import com.vitorsb.itau.ste_api.response.TransactionResponse;

import java.util.List;

public interface TransactionService {

    public void saveTransaction(TransactionRequest transaction);

    public void deleteTransaction();
}
