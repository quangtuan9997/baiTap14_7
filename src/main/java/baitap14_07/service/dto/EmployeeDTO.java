package baitap14_07.service.dto;

import java.util.HashSet;
import java.util.Set;

public class EmployeeDTO {
    private Long id;
    private String name;
    private String email;
    private String departmentName;
    private Long departmentId;
    private Set<String> role = new HashSet<>();

    public EmployeeDTO() {
    }

    public EmployeeDTO(Long id, String name, String email, String departmentName, Long departmentId, Set<String> role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.departmentName = departmentName;
        this.departmentId = departmentId;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }
}
