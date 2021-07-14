package jpa;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
//@IdClass(EmployeeId.class)
public class Employee {
    public enum EmployeeType {FULL_TIME, HALF_TIME}

    @PostPersist
    public void debugPersist() {
        System.out.printf(name + "" + employeeId.getId());
    }

    @EmbeddedId
    private EmployeeId employeeId;

    @Column(name = "emp_name", length = 200, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType = EmployeeType.FULL_TIME;

    private LocalDate dateOfBirth;

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }


    public Employee(String name, EmployeeType employeeType, LocalDate dateOfBirth) {
        this.name = name;
        this.employeeType = employeeType;
        this.dateOfBirth = dateOfBirth;
    }


    public Employee(EmployeeId employeeId, String name) {
        this.employeeId = employeeId;
        this.name = name;
    }

    public Employee(String name) {
        this.name = name;
    }

}
