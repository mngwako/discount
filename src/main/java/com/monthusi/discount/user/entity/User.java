package com.monthusi.discount.user.entity;

import com.fasterxml.uuid.Generators;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @Column(length = 128)
    private String id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @OneToOne
    @JoinColumn(name = "user_type", nullable = false)
    private UserType userType;

    @PrePersist
    public void onCreate() {
        setId(Generators.timeBasedGenerator().generate().toString());
    }

//    https://gabrielpulga.medium.com/a-beginners-guide-to-unit-testing-crud-endpoints-of-a-spring-boot-java-web-service-api-8ae342c9cbcd


}
