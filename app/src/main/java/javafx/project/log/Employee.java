package javafx.project.log;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.project.components.MainComboBox;

public class Employee {
    /*
     * 
     * Only for table creation
     */
    private String name, department;
    private int emp_id;
    private MainComboBox<String> box;

    private ObservableList<String> options = FXCollections.observableArrayList(
            "Present", "Absent");

    public Employee(int id, String name, String department) {
        this.emp_id = id;
        this.name = name;
        this.department = department;
        this.box = new MainComboBox<String>(options);
        this.box.setFloatingText("Attendance");
    }

    public void setBox(MainComboBox<String> box) {
        this.box = box;
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

    public MainComboBox<String> getBox() {
        return box;
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
