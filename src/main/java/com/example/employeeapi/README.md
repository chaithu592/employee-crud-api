# Employee CRUD API

A Spring Boot REST API that performs CRUD operations on Employee resource.

## Technologies Used

- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database
- DTO
- Validation

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/employees | Create employee |
| GET | /api/employees | Get all employees |
| GET | /api/employees/{id} | Get employee by ID |
| PUT | /api/employees/{id} | Update employee |
| DELETE | /api/employees/{id} | Delete employee |

## Run Application

1. Run EmployeeapiApplication.java
2. Server starts on http://localhost:8080




## Sample curl Commands

### Create Employee
curl -X POST http://localhost:8080/api/employees \
-H "Content-Type: application/json" \
-d '{"name":"John","email":"john@example.com","department":"IT"}'

### Get All Employees
curl http://localhost:8080/api/employees

### Get Employee By ID
curl http://localhost:8080/api/employees/1

### Update Employee
curl -X PUT http://localhost:8080/api/employees/1 \
-H "Content-Type: application/json" \
-d '{"name":"Updated","email":"updated@example.com","department":"HR"}'

### Delete Employee
curl -X DELETE http://localhost:8080/api/employees/1