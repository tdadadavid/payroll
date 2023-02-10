package com.king.payroll;

import org.springframework.hateoas.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@RestController(value = "/api")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    private final EmployeeModelAssembler employeeModelAssembler;

    EmployeeController(EmployeeRepository employeeRepository, EmployeeModelAssembler assembler){
        this.employeeRepository = employeeRepository;
        this.employeeModelAssembler = assembler;
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee){
        return this.employeeRepository.save(employee);
    }

    @GetMapping("/employees")
    public CollectionModel<EntityModel<Employee>> findEmployees(){
        List<EntityModel<Employee>> employees = this.employeeRepository.findAll()
                .stream()
                .map(this.employeeModelAssembler::toModel)
                .toList();

        return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).findEmployees()).withRel("employees"));
    }

    @GetMapping("/employees/{id}")
    public EntityModel<Employee> findEmployeeById(@PathVariable Long id){
        Employee employee = this.employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        return this.employeeModelAssembler.toModel(employee);
    }

    @PutMapping("/employee/{id}")
    public Employee updateEmployeeDetails(@RequestBody Employee employee, @PathVariable Long id){
        return this.employeeRepository.findById(id)
                .map((Employee foundEmployee) -> {
                    foundEmployee.setName(employee.getName());
                    foundEmployee.setRole(employee.getRole());
                    return this.employeeRepository.save(foundEmployee);
                })
                .orElseGet(() -> {
                    employee.setId(id);
                    return this.employeeRepository.save(employee);
                });
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable Long id){
        this.employeeRepository.deleteById(id);
    }
}
