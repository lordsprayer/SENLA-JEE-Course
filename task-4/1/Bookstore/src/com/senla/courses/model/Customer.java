package com.senla.courses.model;

public class Customer extends AId{
    String name;
    String phoneNumber;

    public Customer(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public  String toString(){
        return "Customer {id = " + getId() + " name = " + getName() + " phone number = " + getPhoneNumber() + "}";
    }
}
