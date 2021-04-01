package com.senla.courses.model;

import java.io.Serializable;

public class Customer extends AId implements Serializable {
    private String name;
    private String surname;
    private String phoneNumber;

    public Customer(String name, String surname, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public  String toString(){
        return "Покупатель [id = " + getId() + " имя = " + getName() + " фамилия " + getSurname() +
                " номер телефона = " + getPhoneNumber() + "]";
    }
}
