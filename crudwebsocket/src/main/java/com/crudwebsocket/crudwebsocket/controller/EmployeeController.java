package com.crudwebsocket.crudwebsocket.controller;

import com.crudwebsocket.crudwebsocket.entity.Employee;
import com.crudwebsocket.crudwebsocket.model.UserModel;
import com.crudwebsocket.crudwebsocket.repository.EmployeeRepository;
import com.crudwebsocket.crudwebsocket.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://192.168.0.107:3000")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeService employeeService, EmployeeRepository employeeRepository) {
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
    }

    @MessageMapping("/message")
    @SendTo("/topic/hello")
    public UserModel receiveMessage(@Payload UserModel userModel) {
        return userModel;
    }

    @MessageMapping("/deleted")
    @SendTo("/topic/deleted")
    public String userDeleted(@Payload String id) {
        return id;
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployee() {
        List<Employee> employeeList = employeeService.getAll();
        return employeeList;
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        if (!employeeService.existById(id)) {
            ResponseEntity.badRequest().body("User not found");
        }
        Optional<Employee> employee = employeeService.findById(id);
        return ResponseEntity.ok(employee);
    }

    @PostMapping("/employees")
    public ResponseEntity<?> saveUser(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeRepository.save(employee));
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        if (!employeeService.existById(id)) {
            ResponseEntity.badRequest().body("User not found");
        }
        employeeService.deleteById(id);
        return ResponseEntity.ok("User deleted with id: " + id);
    }

}
