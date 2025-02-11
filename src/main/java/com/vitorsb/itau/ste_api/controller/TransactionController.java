package com.vitorsb.itau.ste_api.controller;

import com.vitorsb.itau.ste_api.request.TransactionRequest;
import com.vitorsb.itau.ste_api.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacao")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<?> processTransaction(@RequestBody TransactionRequest transaction){
        transactionService.saveTransaction(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
