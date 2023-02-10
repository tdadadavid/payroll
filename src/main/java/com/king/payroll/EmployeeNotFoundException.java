package com.king.payroll;

public class EmployeeNotFoundException extends RuntimeException {

    EmployeeNotFoundException(Long id){
        super("The employee with id: " + id + " not found.");
    }
}
