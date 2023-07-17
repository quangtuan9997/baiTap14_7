package baitap14_07.service.mapper.impl;

import baitap14_07.domain.Department;
import baitap14_07.service.dto.DepartmentDTO;
import baitap14_07.service.mapper.DepartmentMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DepartmentMapperIMPL implements DepartmentMapper {
    @Override
    public Department toEntity(DepartmentDTO dto) {
        if (dto == null) {
            return null;
        }
        Department department = new Department();
        department.setId(dto.getId());
        department.setName(dto.getName());
        department.setDescription(dto.getDescription());
        return department;
    }

    @Override
    public DepartmentDTO toDto(Department entỉy) {
        if (entỉy == null) {
            return null;
        }
        DepartmentDTO departmentDto = new DepartmentDTO();
        departmentDto.setId(entỉy.getId());
        departmentDto.setName(entỉy.getName());
        departmentDto.setDescription(entỉy.getDescription());
        return departmentDto;
    }

    @Override
    public List<Department> toEntity(List<DepartmentDTO> dtoList) {
        if (dtoList == null) {
            return null;
        }
        List<Department> departments = new ArrayList<>();
        for (DepartmentDTO d : dtoList) {
            departments.add(toEntity(d));
        }
        return departments;
    }

    @Override
    public List<DepartmentDTO> toDto(List<Department> entityList) {
        if (entityList == null) {
            return null;
        }
        List<DepartmentDTO> departmentDtos = new ArrayList<>();
        for (Department d : entityList) {
            departmentDtos.add(toDto(d));
        }
        return departmentDtos;
    }
}
