package employee.employeeDao;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO {
    public int create(Employee employee) throws SQLException;
    public void delete(int employeeId) throws SQLException;
    //public void delete(Album album) throws SQLException;
    public Employee read(int employeeID) throws SQLException;
    public void update(Employee employee) throws SQLException;
    public List<Employee> getAlbums() throws SQLException;
}
