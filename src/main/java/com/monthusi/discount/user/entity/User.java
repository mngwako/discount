package com.monthusi.discount.user.entity;

import com.fasterxml.uuid.Generators;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

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

    @Column(name = "registration_date")
    private Date registrationDate;

    @PrePersist
    public void onCreate() {
        setId(Generators.timeBasedGenerator().generate().toString());
    }
}
