package com.senla.courses.model;

public class Customer extends AId{
    String name;
    String surname;
    String phoneNumber;

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
        return "Покупатель [id = " + getId() + " имя = " + getName() + " фамилия " + getSurname() +  " номер телефона = " + getPhoneNumber() + "]";
    }
}