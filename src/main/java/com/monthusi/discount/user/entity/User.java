package com.monthusi.discount.user.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @Column(name = "id", length = 128)
    private String id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @OneToOne
    @JoinColumn(name = "user_type", nullable = false)
    private UserType userType;

//    https://gabrielpulga.medium.com/a-beginners-guide-to-unit-testing-crud-endpoints-of-a-spring-boot-java-web-service-api-8ae342c9cbcd


}
