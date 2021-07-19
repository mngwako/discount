package com.monthusi.discount.user.entity;

import com.monthusi.discount.sales.entity.DiscountType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "user_type")
@Data
@EqualsAndHashCode(of = "id")
public class UserType {
    @Id
    @Column(length = 128)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "discount_type", nullable = false)
    private DiscountType discountType;
}
