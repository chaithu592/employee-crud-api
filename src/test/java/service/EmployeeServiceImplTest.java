package com.example.employeeapi.service;

import com.example.employeeapi.dto.EmployeeRequestDTO;
import com.example.employeeapi.dto.EmployeeResponseDTO;
import com.example.employeeapi.entity.Employee;
import com.example.employeeapi.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeServiceImpl service;

    @Test
    void testCreateEmployee() {

        EmployeeRequestDTO request = new EmployeeRequestDTO();
        request.setName("John");

        Employee employee = new Employee();
        employee.setName("John");

        when(repository.save(any(Employee.class))).thenReturn(employee);

        EmployeeResponseDTO response = service.create(request);

        assertEquals("John", response.getName());
        verify(repository).save(any(Employee.class));
    }

    @Test
    void testGetById() {

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("John");

        when(repository.findById(1L)).thenReturn(Optional.of(employee));

        EmployeeResponseDTO response = service.getById(1L);

        assertEquals("John", response.getName());
        verify(repository).findById(1L);
    }

    @Test
    void testGetAll() {

        Employee e1 = new Employee();
        Employee e2 = new Employee();

        when(repository.findAll()).thenReturn(List.of(e1, e2));

        List<EmployeeResponseDTO> list = service.getAll();

        assertEquals(2, list.size());
        verify(repository).findAll();
    }

    @Test
    void testDelete() {

        service.delete(1L);

        verify(repository).deleteById(1L);
    }
}