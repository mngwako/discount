package com.monthusi.discount.sales.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(of = "id")
public class DiscountType {
    @Id
    @Column(length = 128)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;
}
