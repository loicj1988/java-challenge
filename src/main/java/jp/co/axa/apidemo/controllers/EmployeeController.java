package jp.co.axa.apidemo.controllers;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.exceptions.EmployeeAlreadyExistsException;
import jp.co.axa.apidemo.exceptions.EmployeeNotFoundException;
import jp.co.axa.apidemo.services.EmployeeService;

/**
 * Controller used to manage employees
 * 
 * Provide a list of end points to perform CRUD on employee entity
 * 
 * @author Loic
 * @version 0.0.1
 *
 */
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  /**
   * Default constructor with all parameters
   * 
   * @param employeeService The employee service
   */
  public void setEmployeeService(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  /**
   * Get the list of the employees saved in the database
   * 
   * @return The list of employees
   */
  @GetMapping("/employees")
  public ResponseEntity<List<Employee>> getEmployees() {

    // Get employee list
    List<Employee> employees = employeeService.retrieveEmployees();

    // Return No content status if there is no data
    if (employees.size() <= 0) {
      return new ResponseEntity<List<Employee>>(employees, HttpStatus.NO_CONTENT);
    }

    // Return response
    return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
  }

  /**
   * Get an employee with his Id
   * 
   * @param employeeId The employee Id
   * @return The response status with the employee details
   */
  @GetMapping("/employees/{employeeId}")
  public ResponseEntity<Employee> getEmployee(@PathVariable(name = "employeeId") Long employeeId) {

    // Get employee with id
    Employee employee = employeeService.getEmployee(employeeId);

    // Exist check
    if (employee == null) {
      throw new EmployeeNotFoundException(employeeId);
    }

    // Return response
    return new ResponseEntity<Employee>(employee, HttpStatus.OK);
  }

  /**
   * Save a new employee in the database
   * 
   * @param employee The employee to save
   * @return The response status with the saved employee details
   */
  @PostMapping("/employees")
  public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee) {

    // Check if employee already exists
    if (employeeService.getEmployee(employee.getId()) != null) {
      throw new EmployeeAlreadyExistsException(employee.getId());
    }

    // Save the employee and get back the entity with the generated ID
    employee = employeeService.saveEmployee(employee);

    // Return response
    return new ResponseEntity<Employee>(employee, HttpStatus.OK);
  }

  /**
   * Delete employee with his Id
   * 
   * @param employeeId The employee id
   * @return The response status
   */
  @DeleteMapping("/employees/{employeeId}")
  public ResponseEntity<Void> deleteEmployee(@PathVariable(name = "employeeId") Long employeeId) {

    // Delete employee
    employeeService.deleteEmployee(employeeId);

    System.out.println("Employee Deleted Successfully");

    // Return response
    return ResponseEntity.noContent().build();
  }

  /**
   * Update an employee with his Id
   * 
   * @param employeeId The employee details
   * @param employeeId The employee Id
   * @return The response status with the updated employee details
   */
  @PutMapping("/employees/{employeeId}")
  public ResponseEntity<Employee> updateEmployee(@Valid @RequestBody Employee employee,
      @PathVariable(name = "employeeId") Long employeeId) {

    // Update employee
    employee = employeeService.updateEmployee(employee, employeeId);

    // Exist check
    if (employee == null) {
      throw new EmployeeNotFoundException(employeeId);
    }

    // Return response
    return new ResponseEntity<Employee>(employee, HttpStatus.OK);
  }

}
