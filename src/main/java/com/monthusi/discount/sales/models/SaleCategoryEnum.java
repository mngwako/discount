package com.monthusi.discount.sales.models;

public enum SaleCategoryEnum {
    GROCERY("Grocery"),
    SERVICES("Services"),
    GARDENING("Gardening"),
    FURNITURE("Furniture");

    private String name;

    private SaleCategoryEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
