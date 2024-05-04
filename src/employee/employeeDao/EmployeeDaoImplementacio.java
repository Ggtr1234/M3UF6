package employee.employeeDao;

import java.sql.*;
import java.util.List;

public class EmployeeDaoImplementacio implements EmployeeDAO{
    public static Connection con = Connexio.getConnection();
    @Override
    public int create(Employee employee) throws SQLException {
        Statement stmt = null;
        int idAlbumNou = -1;
        try {
            //Creem la consulta de la PreparedStatement
            String query = "INSERT INTO Employee (LastName,FirstName,Title,ReportsTo,BirthDate,HireDate,Address,City,State,Country,PostalCode,Phone,Fax,Email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,)";
            PreparedStatement ps = con.prepareStatement(query);

            //Modifiquem i executem la PreparedStatement
//            ps.setString(1, employee.s);
//            ps.setInt(2, idArtista);
            ps.executeUpdate();

            // Obtenim claus autogenerades
            ResultSet rs = ps.getGeneratedKeys();
            rs.next(); // Sabem que només n'hi ha una
            idAlbumNou = rs.getInt(1);

            ps.close();
            System.out.println("Records created successfully");
//            return idAlbumNou;
            return -1;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return 0;
    }

    @Override
    public void delete(int employeeId) throws SQLException {

    }

    @Override
    public Employee read(int employeeID) throws SQLException {
        return null;
    }

    @Override
    public void update(Employee employee) throws SQLException {

    }

    @Override
    public List<Employee> getAlbums() throws SQLException {
        return null;
    }
}
