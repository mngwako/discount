package com.monthusi.discount.sales.entity;

import com.fasterxml.uuid.Generators;
import com.monthusi.discount.user.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sales_transaction")
@Data
@EqualsAndHashCode(of = "id")
public class SalesTransaction {
    @Id
    @Column(length = 128)
    private String id;

    @OneToOne
    @JoinColumn(name = "user", nullable = false)
    private User user;

    @Column(name = "bill", nullable = false)
    private double bill;

    @Column(name = "discount_type", nullable = false)
    private String discountType;

    @Column(name = "discount_amount", nullable = false)
    private double discountAmount;

    @Column(name = "amount_paid", nullable = false)
    private double amountPaid;

    @Column(name = "date")
    private Date date;

    @PrePersist
    public void onCreate() {
        setId(Generators.timeBasedGenerator().generate().toString());
    }
    
}
