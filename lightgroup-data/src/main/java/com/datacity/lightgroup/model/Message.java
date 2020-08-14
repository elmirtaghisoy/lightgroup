package com.datacity.lightgroup.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "messages")
@Entity
public class Message extends BaseEntity {

    public Message(Long id, boolean active, String name, String surname, String email, String phone, String businessName, int status, BusinessType businessType) {
        super( id, active );
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.businessName = businessName;
        this.status = status;
        this.businessType = businessType;
    }

    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "business_name")
    private String businessName;
    @Column(name = "status")
    private int status;

    @ManyToOne
    @JoinColumn(name = "business_type")
    private BusinessType businessType;

}
