package jp.co.axa.apidemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import jp.co.axa.apidemo.entities.Employee;

/**
 * The employee repository
 * 
 * @author Loic
 * @version 0.0.1
 *
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
