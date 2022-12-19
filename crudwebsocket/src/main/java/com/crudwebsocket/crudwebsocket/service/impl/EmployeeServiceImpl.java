package com.crudwebsocket.crudwebsocket.service.impl;

import com.crudwebsocket.crudwebsocket.entity.Employee;
import com.crudwebsocket.crudwebsocket.repository.EmployeeRepository;
import com.crudwebsocket.crudwebsocket.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public boolean existById(String id) {
        return employeeRepository.existsById(id);
    }

    @Override
    public Optional<Employee> findById(String id) {
        return employeeRepository.findById(id);
    }

    @Override
    public void deleteById(String id) {
        employeeRepository.deleteById(id);
    }
}
