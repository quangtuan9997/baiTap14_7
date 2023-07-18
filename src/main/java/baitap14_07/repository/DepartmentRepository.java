package baitap14_07.repository;

import baitap14_07.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long>, JpaSpecificationExecutor<Department> {
    List<Department> findAll();
}
