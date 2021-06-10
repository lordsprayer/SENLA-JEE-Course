package com.senla.courses.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer implements Identified<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column (name = "name")
    private String name;
    @Column (name  = "surname")
    private String surname;
    @Column (name = "phone_number")
    private String phoneNumber;
    @OneToMany(mappedBy = "customer")
    private Set<Order> order;

    public Customer(String name, String surname, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(int id){
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getId(), customer.getId())
                && Objects.equals(getName(), customer.getName())
                && Objects.equals(getSurname(), customer.getSurname())
                && Objects.equals(getPhoneNumber(), customer.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSurname(), getPhoneNumber());
    }

    @Override
    public  String toString(){
        return "Покупатель [id = " + getId() + ", имя " + getName() + ", фамилия " + getSurname() +
                ", номер телефона " + getPhoneNumber() + "]";
    }

}