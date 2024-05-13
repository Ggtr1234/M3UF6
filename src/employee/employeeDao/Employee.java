package employee.employeeDao;

import java.util.Date;

public class Employee {
    private int employeeID;
    private String firstName;
    private String lastName;
    private String title;
    private int reportsTo;
    private Date birthDate;
    private Date hireDate;
    private String adress;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private String phone;
    private String fax;
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReportsTo() {
        return reportsTo;
    }

    public void setReportsTo(int reportsTo) {
        this.reportsTo = reportsTo;
    }

    public java.sql.Date getBirthDate() {
        return (java.sql.Date) birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public java.sql.Date getHireDate() {
        return (java.sql.Date) hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Employee() {
    }

    public Employee(int employeeID, String firstName, String lastName, String title, int reportsTo, Date birthDate, Date hireDate, String adress, String city, String state, String country, String postalCode, String phone, String fax, String email) {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.reportsTo = reportsTo;
        this.birthDate = birthDate;
        this.hireDate = hireDate;
        this.adress = adress;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
        this.phone = phone;
        this.fax = fax;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeID=" + employeeID +
                ", lastName='" + lastName + '\'' +
                ", title='" + title + '\'' +
                ", reportsTo=" + reportsTo +
                ", birthDate=" + birthDate +
                ", hireDate=" + hireDate +
                ", adress='" + adress + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", phone='" + phone + '\'' +
                ", fax='" + fax + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
