package com.example.bootspring.entity;


import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
@Table(name = "phonebook")
public class Phonebook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Firstname should not be empty")
    private String firstname;
    @NotEmpty(message = "Lastname should not be empty")
    private String lastname;
    @Range(min = 1, max = 100, message = "Age should be between 0 and 100 characters")
    @NotNull(message = "Age should not be empty")
    private Long age;
    @NotEmpty(message = "Phone should not be empty")
    @Size(min = 1, max = 12, message = "Phone should be between 1 and 3 characters")
    private String phone_number;
    @NotEmpty(message = "Address should not be empty")
    private String address;

    public Long getId() {
        return id;
    }

    @Column(name = "firstname")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Column(name = "lastname")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Column(name = "age")
    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    @Column(name = "phone_number")
    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
