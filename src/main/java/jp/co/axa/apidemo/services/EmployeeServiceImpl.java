package jp.co.axa.apidemo.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  @Autowired
  private EmployeeRepository employeeRepository;

  public void setEmployeeRepository(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Cacheable("retrieveEmployees")
  public List<Employee> retrieveEmployees() {
    List<Employee> employees = employeeRepository.findAll();
    return employees;
  }

  @Cacheable("getEmployee")
  public Employee getEmployee(Long employeeId) {
    Optional<Employee> optEmp = employeeRepository.findById(employeeId);
    return optEmp.isPresent() ? optEmp.get() : null;
  }

  @Caching(evict = {@CacheEvict(value = "getEmployee", allEntries = true),
      @CacheEvict(value = "retrieveEmployees", allEntries = true)})
  public Employee saveEmployee(Employee employee) {
    employeeRepository.save(employee);
    return employee;
  }

  @Caching(evict = {@CacheEvict(value = "getEmployee", allEntries = true),
      @CacheEvict(value = "retrieveEmployees", allEntries = true)})
  public void deleteEmployee(Long employeeId) {
    employeeRepository.deleteById(employeeId);
  }

  @Transactional
  @Caching(evict = {@CacheEvict(value = "getEmployee", allEntries = true),
      @CacheEvict(value = "retrieveEmployees", allEntries = true)})
  public Employee updateEmployee(Employee employee, Long employeeId) {

    return employeeRepository.findById(employeeId).map(e -> {
      // Update employee data
      e.setName(employee.getName());
      e.setSalary(employee.getSalary());
      e.setDepartment(employee.getDepartment());

      // Save and return employee
      return employeeRepository.save(e);
    }).orElseGet(() -> {

      // No data corresponding to the id
      return null;
    });

  }
}
