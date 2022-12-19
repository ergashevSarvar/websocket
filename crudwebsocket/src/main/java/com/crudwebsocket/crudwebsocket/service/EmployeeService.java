package com.crudwebsocket.crudwebsocket.service;

import com.crudwebsocket.crudwebsocket.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> getAll();

    boolean existById(String id);

    Optional<Employee> findById(String id);

    void deleteById(String id);
}
