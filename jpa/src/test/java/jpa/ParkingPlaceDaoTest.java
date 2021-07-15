package jpa;

import org.junit.jupiter.api.Test;

import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

class ParkingPlaceDaoTest {
    ParkingPlaceDao parkingPlaceDao = new ParkingPlaceDao(Persistence.createEntityManagerFactory("pu"));
    EmployeeDao employeeDao = new EmployeeDao(Persistence.createEntityManagerFactory("pu"));

    @Test
    void saveParkingPlace() {
        ParkingPlace parkingPlace = new ParkingPlace(100);
        parkingPlaceDao.saveParkingPlace(parkingPlace);
        ParkingPlace anotherParkingPlace = parkingPlaceDao.findParkingPlace(100);
        assertEquals(100, anotherParkingPlace.getNumber());

    }

    @Test
    void testSaveEmployeeWithParkingPlace() {
        ParkingPlace parkingPlace = new ParkingPlace(100);
        parkingPlaceDao.saveParkingPlace(parkingPlace);

        Employee employee = new Employee(new EmployeeId("x", 1L), "John Doe");
        employee.setParkingPlace(parkingPlace);
        employeeDao.saveEmployee(employee);

        Employee anotherEmployee = employeeDao.findEmployeeById(new EmployeeId("x", 1L));

        assertEquals(100, anotherEmployee.getParkingPlace().getNumber());
    }

}