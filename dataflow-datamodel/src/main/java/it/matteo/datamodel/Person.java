package it.matteo.datamodel;

import java.util.List;

/**
 * @author matteo.minardi
 */
public class Person {
    
    private String name;
    private String surname;
    private int age;

    private List<BankAccount> banks;
    private List<Item> items;

    public Person(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<BankAccount> getBanks() {
        return banks;
    }

    public void setBanks(List<BankAccount> banks) {
        this.banks = banks;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
    
}
