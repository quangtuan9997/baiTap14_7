package baitap14_07.repository;

import baitap14_07.domain.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>, JpaSpecificationExecutor<Employee> {
    Optional<Employee> findOneByEmailIgnoreCase(String email);
    Page<Employee> findAllByNameOrEmailContainingIgnoreCase(String textSheachName, String texSearchEmail, Pageable pageable);
}
