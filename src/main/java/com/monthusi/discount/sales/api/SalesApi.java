package com.monthusi.discount.sales.api;

import com.monthusi.discount.sales.entity.SalesTransaction;
import com.monthusi.discount.sales.service.ISalesTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/sales")
@RequiredArgsConstructor
public class SalesApi {
    private final ISalesTransactionService iSalesTransactionService;

    @PostMapping(path = "/transaction/create")
    public SalesTransaction createSalesTransaction (@RequestBody SalesTransaction salesTransaction) {
        return iSalesTransactionService.createSalesTransaction(salesTransaction);
    }

    @GetMapping(path = "/transaction/list/all")
    public List<SalesTransaction> getAllSalesTransactions () {
        return iSalesTransactionService.getAllSalesTransactions();
    }
}
