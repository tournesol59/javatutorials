package com.baeldung.model;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.hibernate.annotations.GenericGenerator;

@Component
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name="increment", strategy="increment")    
    private int addressId;

    @Column(name="addr_city")
    private String cityName;

    @Column(name="addr_country")
    private String countryName;

    // the customer has many-to-one relationship with address (after we can code a Set with unique member in Customer

    public Address() {
        super();
    }

    public Address(int id, String city, String country) {
        super();
	this.addressId = id;
	this.cityName = city;
	this.countryName = country;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String city) {
        this.cityName = city;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String country) {
        this.countryName = country;
    }

    public void test() {
        System.out.println(cityName+" "+countryName);
    }

 }

