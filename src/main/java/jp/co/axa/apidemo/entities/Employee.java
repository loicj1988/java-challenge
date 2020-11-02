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

@Entity
@Table(name = "EMPLOYEE")
public class Employee {

  @Getter
  @Setter
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Getter
  @Setter
  @Column(name = "EMPLOYEE_NAME", nullable = false)
  @NotNull(message = "Employee name is required")
  @Size(min = 1, max = 30, message = "Employee name length must be between 1 and 30")
  private String name;

  @Getter
  @Setter
  @Column(name = "EMPLOYEE_SALARY", nullable = false)
  @NotNull(message = "Salary is required")
  @Positive(message = "Salary must be more than 0")
  private Integer salary;

  @Getter
  @Setter
  @Column(name = "DEPARTMENT", nullable = false)
  @NotNull(message = "Department is required")
  @Size(min = 1, max = 30, message = "Department name length must be between 1 and 30")
  private String department;

}
