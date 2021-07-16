package jpa;


import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
//@IdClass(EmployeeId.class)
public class Employee {
    public enum EmployeeType {FULL_TIME, HALF_TIME}


    @EmbeddedId
    private EmployeeId employeeId;
    @Column(name = "emp_name", length = 200, nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType = EmployeeType.FULL_TIME;
    private LocalDate dateOfBirth;
    @ElementCollection
    @CollectionTable(name = "Nicknames")
    private Set<String> nicknames = new HashSet<>();
    @ElementCollection
    private Set<VacationEntry> vacationBooking;
    // @ElementCollection
    // @CollectionTable(name = "phone_numbers")
    // @MapKeyColumn(name = "phone_type")
    // @Column(name = "phone_number")
    // private Map<String, String> phoneNumbers;
    @OneToOne
    private ParkingPlace parkingPlace;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "employee")
    //@OrderBy("type")
    @OrderColumn(name = "pos")
    private List<PhoneNumber> phoneNumbers;

    @ManyToMany(mappedBy = "employees")
    private Set<Project> projects = new HashSet<>();

    @PostPersist
    public void debugPersist() {
        System.out.printf(name + "" + employeeId.getId());
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

    public Employee(EmployeeId employeeId, String name, EmployeeType employeeType, LocalDate dateOfBirth) {
        this.employeeId = employeeId;
        this.name = name;
        this.employeeType = employeeType;
        this.dateOfBirth = dateOfBirth;
    }


    public void addPhoneNumber(PhoneNumber... phoneNumber) {
        if (phoneNumbers == null) {
            phoneNumbers = new ArrayList<>();
        }
        for (PhoneNumber item : phoneNumber
        ) {
            phoneNumbers.add(item);
            item.setEmployee(this);

        }
    }

}
