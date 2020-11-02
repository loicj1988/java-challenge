package jp.co.axa.apidemo.services;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceImplTest {

  @Autowired
  private EmployeeService employeeService;

  @MockBean
  private EmployeeRepository employeeRepository;


  @Captor
  ArgumentCaptor<Employee> employeeArgumentCaptor;

  @Test
  public void testRetrieveEmployees() {

    // Prepare data
    List<Employee> employees = new ArrayList<Employee>();

    Employee employee1 = new Employee();
    employee1.setId(1L);
    employee1.setName("emp1");
    employee1.setSalary(10000);
    employee1.setDepartment("DEPT1");
    employees.add(employee1);

    Employee employee2 = new Employee();
    employee2.setId(2L);
    employee2.setName("emp2");
    employee2.setSalary(20000);
    employee2.setDepartment("DEPT2");
    employees.add(employee2);

    // Prepare mock
    Mockito.when(employeeRepository.findAll()).thenReturn(employees);

    // Test
    List<Employee> employeesResult = new ArrayList<Employee>();
    employeesResult = employeeService.retrieveEmployees();

    // Verify
    Mockito.verify(employeeRepository, Mockito.times(1)).findAll();
    assertEquals(employeesResult.size(), 2);
    assertEquals(employeesResult.get(0).getId(), Long.valueOf(1));
    assertEquals(employeesResult.get(1).getId(), Long.valueOf(2));


  }

  @Test
  public void testGetEmployee() {

    // Prepare data
    Employee employee1 = new Employee();
    employee1.setId(1L);
    employee1.setName("emp1");
    employee1.setSalary(10000);
    employee1.setDepartment("DEPT1");
    Optional<Employee> optEmployee1 = Optional.ofNullable(employee1);

    // Prepare mock
    Mockito.when(employeeRepository.findById(1L)).thenReturn(optEmployee1);

    // Test
    Employee employeeResult = employeeService.getEmployee(1L);

    // Verify
    Mockito.verify(employeeRepository, Mockito.times(1)).findById(1L);
    assertEquals(employeeResult.getId(), Long.valueOf(1));
    assertEquals(employeeResult.getName(), "emp1");
    assertEquals(employeeResult.getSalary(), Integer.valueOf(10000));
    assertEquals(employeeResult.getDepartment(), "DEPT1");

  }

  @Test
  public void testSaveEmployee() {

    // Prepare data
    Employee employee1 = new Employee();
    employee1.setId(1L);
    employee1.setName("emp1");
    employee1.setSalary(10000);
    employee1.setDepartment("DEPT1");

    // Prepare mock
    Mockito.when(employeeRepository.save(employee1)).thenReturn(employee1);

    // Test
    Employee employeeResult = employeeService.saveEmployee(employee1);

    // Verify
    Mockito.verify(employeeRepository, Mockito.times(1)).save(employee1);

    assertEquals(employeeResult.getId(), Long.valueOf(1));
    assertEquals(employeeResult.getName(), "emp1");
    assertEquals(employeeResult.getSalary(), Integer.valueOf(10000));
    assertEquals(employeeResult.getDepartment(), "DEPT1");

  }

  @Test
  public void deleteEmployee() {

    // Prepare data
    Employee employee1 = new Employee();
    employee1.setId(1L);
    employee1.setName("emp12");
    employee1.setSalary(10000);
    employee1.setDepartment("DEPT1");

    // Prepare mock
    Mockito.doNothing().when(employeeRepository).deleteById(1L);

    // Test
    employeeService.deleteEmployee(1L);

    // Verify
    Mockito.verify(employeeRepository, Mockito.times(1)).deleteById(1L);

  }

  @Test
  public void updateEmployee() {

    // Prepare data
    Employee employee1 = new Employee();
    employee1.setId(1L);
    employee1.setName("emp1");
    employee1.setSalary(10000);
    employee1.setDepartment("DEPT1");
    Optional<Employee> optEmployee1 = Optional.ofNullable(employee1);

    // Prepare mock
    Mockito.when(employeeRepository.findById(1L)).thenReturn(optEmployee1);
    employee1.setName("emp1b");
    employee1.setSalary(10001);
    employee1.setDepartment("DEPT1B");
    Mockito.when(employeeRepository.save(employee1)).thenReturn(employee1);

    // Test
    Employee employeeResult = employeeService.updateEmployee(employee1, 1L);

    // Verify
    Mockito.verify(employeeRepository, Mockito.times(1)).findById(1L);
    Mockito.verify(employeeRepository, Mockito.times(1)).save(employee1);

    assertEquals(employeeResult.getId(), Long.valueOf(1));
    assertEquals(employeeResult.getName(), "emp1b");
    assertEquals(employeeResult.getSalary(), Integer.valueOf(10001));
    assertEquals(employeeResult.getDepartment(), "DEPT1B");

  }
}
