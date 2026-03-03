package com.example.employeeapi.service;

import com.example.employeeapi.dto.EmployeeRequestDTO;
import com.example.employeeapi.dto.EmployeeResponseDTO;
import com.example.employeeapi.entity.Employee;
import com.example.employeeapi.repository.EmployeeRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger =
            LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    // CREATE
    @Override
    public EmployeeResponseDTO create(EmployeeRequestDTO dto) {

        logger.info("Creating employee: {}", dto.getName());

        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setDepartment(dto.getDepartment());

        Employee saved = repository.save(employee);

        logger.info("Employee created with id: {}", saved.getId());

        return new EmployeeResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getEmail(),
                saved.getDepartment()
        );
    }

    // GET BY ID
    @Override
    public EmployeeResponseDTO getById(Long id) {

        logger.info("Fetching employee with id: {}", id);

        Employee employee = repository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Employee not found with id: {}", id);
                    return new RuntimeException("Employee not found");
                });

        return new EmployeeResponseDTO(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getDepartment()
        );
    }

    // GET ALL
    @Override
    public List<EmployeeResponseDTO> getAll() {

        logger.info("Fetching all employees");

        return repository.findAll()
                .stream()
                .map(emp -> new EmployeeResponseDTO(
                        emp.getId(),
                        emp.getName(),
                        emp.getEmail(),
                        emp.getDepartment()
                ))
                .collect(Collectors.toList());
    }

    // UPDATE
    @Override
    public EmployeeResponseDTO update(Long id, EmployeeRequestDTO dto) {

        logger.info("Updating employee with id: {}", id);

        Employee employee = repository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Employee not found for update: {}", id);
                    return new RuntimeException("Employee not found");
                });

        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setDepartment(dto.getDepartment());

        Employee updated = repository.save(employee);

        logger.info("Employee updated successfully");

        return new EmployeeResponseDTO(
                updated.getId(),
                updated.getName(),
                updated.getEmail(),
                updated.getDepartment()
        );
    }

    // DELETE
    @Override
    public void delete(Long id) {

        logger.info("Deleting employee with id: {}", id);

        if (!repository.existsById(id)) {
            logger.error("Employee not found for delete: {}", id);
            throw new RuntimeException("Employee not found");
        }

        repository.deleteById(id);

        logger.info("Employee deleted successfully");
    }
}