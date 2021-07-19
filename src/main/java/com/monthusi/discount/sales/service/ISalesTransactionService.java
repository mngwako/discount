package com.monthusi.discount.sales.service;

import com.monthusi.discount.sales.entity.SalesTransaction;
import java.util.List;

public interface ISalesTransactionService {
    SalesTransaction createSalesTransaction(SalesTransaction transaction);
    List<SalesTransaction> getAllSalesTransactions();
}
