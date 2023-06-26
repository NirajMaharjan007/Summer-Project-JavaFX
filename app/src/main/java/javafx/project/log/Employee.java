package javafx.project.log;

public class Employee {
    /*
     * 
     * Only for table creation
     */
    private String name, department;
    private int emp_id;

    public Employee() {
        this.name = "";
        this.department = "";
        this.emp_id = 0;
    }

    public Employee(int id, String name, String department) {
        this.emp_id = id;
        this.name = name;
        this.department = department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int emp_id) {
        this.emp_id = emp_id;
    }

    public int getId() {
        return emp_id;
    }

    public String getDepartment() {
        return department;
    }

    public String getName() {
        return name;
    }

    public String getAll() {
        return "Emp_id: " + this.emp_id + " Name: " + this.name +
                " Department: " + this.department;
    }

    public void setAll(int id, String name, String department) {
        this.emp_id = id;
        this.name = name;
        this.department = department;
    }
}
