package com.monthusi.discount.sales.service.implementation;

import com.monthusi.discount.sales.entity.SalesTransaction;
import com.monthusi.discount.sales.models.DiscountPercentageEnum;
import com.monthusi.discount.sales.models.SaleCategoryEnum;
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
        double discountAmount = getDiscount(transaction.getBill(), discountType, transaction.getSaleCategory());
        transaction.setUser(user);
        transaction.setDiscountType(discountType);
        transaction.setDiscountAmount(discountAmount);
        transaction.setAmountPaid(transaction.getBill()-discountAmount);
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

    private double getDiscount(double bill, String discountType, String saleCategory){
        double percentageDiscount;
        double billDiscount;
        double totalDiscount;
        
        switch (discountType){
            case "EMPLOYEE_DISCOUNT":
                percentageDiscount = bill * (DiscountPercentageEnum.EMPLOYEE_DISCOUNT.getDiscount()/100);
                break;
            case "AFFILIATE_DISCOUNT":
                percentageDiscount = bill * (DiscountPercentageEnum.AFFILIATE_DISCOUNT.getDiscount()/100);
                break;
            case "LONG_TERM_CUSTOMER_DISCOUNT":
                percentageDiscount = bill * (DiscountPercentageEnum.LONG_TERM_CUSTOMER_DISCOUNT.getDiscount()/100);
                break;
            default:
            percentageDiscount = 0.0;
            break;
        }

        if(bill >= 100){
            billDiscount = ((int)(bill / 100)) * 5;
        } else {
            billDiscount = 0.0;
        }

        if (saleCategory.equals(SaleCategoryEnum.GROCERY.getName())) {
            totalDiscount = billDiscount;
        } else {
            totalDiscount = billDiscount + percentageDiscount;
        }

        return totalDiscount;
    }
}
