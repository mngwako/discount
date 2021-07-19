package com.monthusi.discount.sales.service.implementation;

import com.monthusi.discount.sales.entity.SalesTransaction;
import com.monthusi.discount.sales.models.DiscountPercentageEnum;
import com.monthusi.discount.sales.repository.ISalesTransactionRepository;
import com.monthusi.discount.sales.service.ISalesTransactionService;
import com.monthusi.discount.user.entity.User;
import com.monthusi.discount.user.models.UserTypeEnum;
import com.monthusi.discount.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalesTransactionServiceImpl implements ISalesTransactionService {
    private final ISalesTransactionRepository iSalesTransactionRepository;
    private final IUserService iUserService;

    @Override
    public SalesTransaction createSalesTransaction(SalesTransaction transaction){
        User user = iUserService.getUserById(transaction.getUser().getId());

        String discountType = getDiscountTypeByUser(user);
        double discountAmount = getDiscount(transaction.getBill(), discountType);
        transaction.setDiscountType(discountType);
        transaction.setDiscountAmount(discountAmount);
        transaction.setDate(new Date());

        return iSalesTransactionRepository.save(transaction);
    }

    @Override
    public List<SalesTransaction> getAllSalesTransactions(){
        return iSalesTransactionRepository.findAll();
    }
    
    private String getDiscountTypeByUser(User user){
        String discountType = "NONE";
        if (user.getUserType().getName().equals(UserTypeEnum.EMPLOYEE.getName())){
            discountType = DiscountPercentageEnum.EMPLOYEE_DISCOUNT.name();

        } else if (user.getUserType().getName().equals(UserTypeEnum.AFFILIATE.getName())){
            discountType = DiscountPercentageEnum.AFFILIATE_DISCOUNT.name();

        } else if (user.getUserType().getName().equals(UserTypeEnum.CUSTOMER.getName())){
            LocalDate discountDate = LocalDate.now().minusYears(2);
            LocalDate registrationDate = user.getRegistrationDate().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            if(discountDate.isAfter(registrationDate)){
                discountType = DiscountPercentageEnum.LONG_TERM_CUSTOMER_DISCOUNT.name();
            } 
        }

        return discountType;
    }

    private double getDiscount(double bill, String discountType){
        double percentageDiscount = 0.0;
        double billDiscount = 0.0;
        double totalDiscount = 0.0;


        switch (discountType){
            case "Testing 1":
            percentageDiscount = 0.0;
            break;
            case "Testing 2":
            percentageDiscount = 0.0;
            break;
            default:
            percentageDiscount = 0.0;
            break;
        }

        return totalDiscount;
    }
}
