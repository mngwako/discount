package com.monthusi.discount.sales.repository;

import com.monthusi.discount.sales.entity.SalesTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISalesTransactionRepository extends JpaRepository<SalesTransaction, String> {
}
