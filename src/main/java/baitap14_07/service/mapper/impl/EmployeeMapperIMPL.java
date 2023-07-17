package baitap14_07.service.mapper.impl;

import baitap14_07.domain.Department;
import baitap14_07.domain.Employee;
import baitap14_07.domain.Role;
import baitap14_07.service.dto.EmployeeDTO;
import baitap14_07.service.mapper.EmployeeMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EmployeeMapperIMPL implements EmployeeMapper {
    @Override
    public Employee toEntity(EmployeeDTO dto) {
        if (dto == null) {
            return null;
        }
        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setEmail(dto.getEmail());
        employee.setName(dto.getName());
        employee.setDepartmentId(dto.getDepartmentId());
        return employee;
    }

    @Override
    public EmployeeDTO toDto(Employee entity) {
        if (entity == null) {
            return null;
        }
        EmployeeDTO employeeDto = new EmployeeDTO();
        employeeDto.setId(entity.getId());
        employeeDto.setName(entity.getName());
        employeeDto.setEmail(entity.getEmail());
        employeeDto.setDepartmentId(entity.getDepartmentId());
        Department department = entity.getDepartment();
        if (department != null) {
            employeeDto.setDepartmentName(department.getName());
        }
        Set<Role> roles = entity.getRoles();
        if (roles != null) {
            employeeDto.setRole(roles.stream().map(Role::getId).collect(Collectors.toSet()));
        }
        return employeeDto;
    }

    @Override
    public List<Employee> toEntity(List<EmployeeDTO> dtoList) {
        if (dtoList == null) {
            return null;
        }
        List<Employee> employees = new ArrayList<>();
        for (EmployeeDTO employeeDto : dtoList) {
            employees.add(toEntity(employeeDto));
        }
        return employees;
    }

    @Override
    public List<EmployeeDTO> toDto(List<Employee> entityList) {
        if (entityList == null) {
            return null;
        }
        List<EmployeeDTO> employeeDtos = new ArrayList<>();
        for (Employee employee : entityList) {
            employeeDtos.add(toDto(employee));
        }
        return employeeDtos;
    }
}
