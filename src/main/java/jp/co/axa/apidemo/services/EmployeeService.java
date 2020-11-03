package jp.co.axa.apidemo.services;

import java.util.List;
import jp.co.axa.apidemo.entities.Employee;

/**
 * Employee service that provide operation over employee entities
 * 
 * It will call employee repository to perform CRUD on employee entities
 * 
 * @author Loic
 * @version 0.0.1
 * 
 */
public interface EmployeeService {

  /**
   * Get all the employees
   * 
   * @return List of employees
   */
  public List<Employee> retrieveEmployees();

  /**
   * Get employee with his Id
   * 
   * @param employeeId The employee id
   * @return The employee entity
   */
  public Employee getEmployee(Long employeeId);

  /**
   * Save a new employee
   * 
   * @param employee The employee to save
   * @return Employee saved
   */
  public Employee saveEmployee(Employee employee);

  /**
   * Delete an employee with his Id
   * 
   * @param employeeId The employee id
   */
  public void deleteEmployee(Long employeeId);

  /**
   * Update an employee with his Id
   * 
   * @param employee The employee details to update
   * @param employeeId The employee Id
   * @return Employee updated
   */
  public Employee updateEmployee(Employee employee, Long employeeId);
}
