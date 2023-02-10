package com.king.payroll;


import jakarta.persistence.*;
@Entity
public class Employee {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String role;

    public Employee() {}

    public Employee(String name, String role){
        this.role = role;
        this.name = name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setRole(String role){
        this.role = role;
    }

    public String getName(){
        return this.name;
    }

    public String getRole(){
        return this.role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString(){
        return "Employee {  name: " + name + ", role: " + role + " }";
    }
}
