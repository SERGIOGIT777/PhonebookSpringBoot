package com.example.bootspring.entity;


import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "application")
public class Application {
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
    @NotEmpty(message = "Address should not be empty")
    private String address;
    @NotNull(message = "Status should not be empty")
    @Range(max = 3, message = "Status should be max 2 characters")
    private Long status;

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

    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "status")
    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }
}
