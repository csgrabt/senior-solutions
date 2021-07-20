package employees;


import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@AllArgsConstructor
public class EmployeesDao {

    private JdbcTemplate jdbcTemplate;

    public List<Employee> findAll() {
        return jdbcTemplate.query("select id, emp_name from employees",
                new RowMapper<Employee>() {
                    @Override
                    public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
                        Long id = resultSet.getLong("id");
                        String name = resultSet.getString("emp_name");
                        Employee employee = new Employee();
                        employee.setId(id);
                        employee.setName(name);
                        return employee;
                    }
                });
    }


}
