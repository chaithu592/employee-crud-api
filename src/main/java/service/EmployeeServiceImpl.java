package com.example.employeeapi.service;

import com.example.employeeapi.dto.EmployeeRequestDTO;
import com.example.employeeapi.dto.EmployeeResponseDTO;
import com.example.employeeapi.entity.Employee;
import com.example.employeeapi.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public EmployeeResponseDTO create(EmployeeRequestDTO dto) {

        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setDepartment(dto.getDepartment());

        Employee saved = repository.save(employee);

        return new EmployeeResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getEmail(),
                saved.getDepartment()
        );
    }

    @Override
    public EmployeeResponseDTO getById(Long id) {

        Employee employee = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        return new EmployeeResponseDTO(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getDepartment()
        );
    }

    @Override
    public List<EmployeeResponseDTO> getAll() {

        return repository.findAll()
                .stream()
                .map(emp -> new EmployeeResponseDTO(
                        emp.getId(),
                        emp.getName(),
                        emp.getEmail(),
                        emp.getDepartment()))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeResponseDTO update(Long id, EmployeeRequestDTO dto) {

        Employee employee = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setDepartment(dto.getDepartment());

        Employee updated = repository.save(employee);

        return new EmployeeResponseDTO(
                updated.getId(),
                updated.getName(),
                updated.getEmail(),
                updated.getDepartment()
        );
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}