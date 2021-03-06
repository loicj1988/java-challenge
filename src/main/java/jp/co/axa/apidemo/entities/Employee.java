package jp.co.axa.apidemo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Employee entity class
 * 
 * @author Loic
 * @version 0.0.1
 */
@Entity
@Table(name = "EMPLOYEE")
public class Employee {

  /**
   * The employee id
   */
  @Getter
  @Setter
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * The employee name
   */
  @Getter
  @Setter
  @Column(name = "EMPLOYEE_NAME", nullable = false)
  @NotNull(message = "Employee name is required")
  @Size(min = 1, max = 30, message = "Employee name length must be between 1 and 30")
  private String name;

  /**
   * The employee salary
   */
  @Getter
  @Setter
  @Column(name = "EMPLOYEE_SALARY", nullable = false)
  @NotNull(message = "Salary is required")
  @Positive(message = "Salary must be more than 0")
  private Integer salary;

  /**
   * The employee department
   */
  @Getter
  @Setter
  @Column(name = "DEPARTMENT", nullable = false)
  @NotNull(message = "Department is required")
  @Size(min = 1, max = 30, message = "Department name length must be between 1 and 30")
  private String department;

}
