package com.senla.courses;

public class Customer implements Identified<Integer> {
    private Integer id = null;
    private String name;
    private String surname;
    private String phoneNumber;

    public Customer(String name, String surname, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }

    public Customer(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
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
        return "Покупатель [id = " + getId() + ", имя " + getName() + ", фамилия " + getSurname() +
                ", номер телефона " + getPhoneNumber() + "]";
    }
}
