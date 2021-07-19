package com.monthusi.discount.sales.models;

public enum DiscountPercentageEnum {
    EMPLOYEE_DISCOUNT(30),
    AFFILIATE_DISCOUNT(10),
    LONG_TERM_CUSTOMER_DISCOUNT(5);

    private double discount;

    private DiscountPercentageEnum(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

}
