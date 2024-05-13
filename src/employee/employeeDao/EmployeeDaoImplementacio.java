package employee.employeeDao;

import Album.albumDao.Album;
import Track.trackBasicJDBC.Track;
import genre.Genre;
import mediaType.MediaType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImplementacio implements EmployeeDAO {
    public static Connection con = Connexio.getConnection();

    @Override
    public int create(Employee employee) throws SQLException {
        Statement stmt = null;
        int idEmployeeNou = -1;
        try {
            //Creem la consulta de la PreparedStatement
            String query = "INSERT INTO Employee (LastName,FirstName,Title,ReportsTo,BirthDate,HireDate,Address,City,State,Country,PostalCode,Phone,Fax,Email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,)";
            PreparedStatement ps = con.prepareStatement(query);

            //Modifiquem i executem la PreparedStatement
            ps.setString(1, employee.getLastName());
            ps.setString(2, employee.getFirstName());
            ps.setString(3, employee.getTitle());
            ps.setInt(4, employee.getReportsTo());
            ps.setDate(5, employee.getBirthDate());
            ps.setDate(6, employee.getHireDate());
            ps.setString(7, employee.getAdress());
            ps.setString(8, employee.getCity());
            ps.setString(9, employee.getState());
            ps.setString(10, employee.getCountry());
            ps.setString(11, employee.getPostalCode());
            ps.setString(12, employee.getPhone());
            ps.setString(13, employee.getFax());
            ps.setString(14, employee.getEmail());
            ps.executeUpdate();

            // Obtenim claus autogenerades
            ResultSet rs = ps.getGeneratedKeys();
            rs.next(); // Sabem que només n'hi ha una
            idEmployeeNou = rs.getInt(1);

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
        try {
            con.setAutoCommit(false);
            String query = "DELETE from Employee where EmployeeId = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, employeeId);
            ps.executeUpdate();
            con.commit();
            ps.close();
            System.out.println("Operation done successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    @Override
    public Employee read(int employeeID) throws SQLException {
        Employee employee = null;
        try{
            String query = "SELECT * FROM Employee WHERE EmployeeId = ?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, employeeID);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int employeeId = rs.getInt("EmployeeId");
                String lastName = rs.getString("LastName");
                String firstName = rs.getString("FirstName");
                String title = rs.getString("Title");
                int reportsTo = rs.getInt("ReportsTo");
                Date birthDate = rs.getDate("BirthDate");
                Date hireDate = rs.getDate("HireDate");
                String adress = rs.getString("Adress");
                String city = rs.getString("City");
                String state = rs.getString("State");
                String country = rs.getString("Country");
                String postalCode = rs.getString("PostalCode");
                String phone = rs.getString("Phone");
                String fax = rs.getString("Fax");
                String email = rs.getString("Email");
                employee = new Employee(employeeId,firstName,lastName,title,reportsTo,birthDate,hireDate,adress,city,state,country,postalCode,phone,fax,email);
            }
            rs.close();
            ps.close();
        }catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());

        }
        return employee;
    }

    @Override
    public void update(Employee employee) throws SQLException {
        try{
            con.setAutoCommit(false);
            String query = "UPDATE Employee set EmployeeId = ?, LastName = ?, FirstName = ?, Title = ?, ReportsTo = ?, BirthDate = ?, HireDate = ?, Address = ?, City = ?, State = ?, Country = ?, PostalCode = ?, Phone = ?, Fax = ?, Email = ? WHERE EmployeeId = ?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, employee.getEmployeeID());
            ps.setString(2, employee.getLastName());
            ps.setString(3, employee.getFirstName());
            ps.setString(4,employee.getTitle());
            ps.setInt(5,employee.getReportsTo());
            ps.setString(6, String.valueOf(employee.getBirthDate()));
            ps.setString(7, String.valueOf(employee.getHireDate()));
            ps.setString(8,employee.getAdress());
            ps.setString(9,employee.getCity());
            ps.setString(10,employee.getState());
            ps.setString(11,employee.getCountry());
            ps.setString(12,employee.getPostalCode());
            ps.setString(13,employee.getPhone());
            ps.setString(14,employee.getFax());
            ps.setString(15,employee.getEmail());
            ps.setInt(16,employee.getEmployeeID());
            ps.executeUpdate();
            con.commit();
            ps.close();
        }catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

    }

    @Override
    public List<Employee> getEmployees() throws SQLException {
        String query = "select * from Employee";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<Employee> ls = new ArrayList();

        while (rs.next()) {
            Employee employee = new Employee();
            employee.setEmployeeID(rs.getInt("EmployeeId"));
            employee.setLastName(rs.getString("LastName"));
            employee.setFirstName(rs.getString("FirstName"));
            employee.setTitle(rs.getString("Title"));
            employee.setReportsTo(rs.getInt("ReportsTo"));
            employee.setBirthDate(rs.getDate("BirthDate"));
            employee.setHireDate(rs.getDate("HireDate"));
            employee.setAdress(rs.getString("Address"));
            employee.setCity(rs.getString("City"));
            employee.setState(rs.getString("State"));
            employee.setCountry(rs.getString("Country"));
            employee.setPostalCode(rs.getString("PostalCode"));
            employee.setPhone(rs.getString("Phone"));
            employee.setFax(rs.getString("Fax"));
            employee.setEmail(rs.getString("Email"));
            ls.add(employee);
        }
        return ls;
    }
}
