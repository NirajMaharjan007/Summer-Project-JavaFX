package javafx.project.log;

import io.github.palexdev.materialfx.controls.MFXRadioButton;

import javafx.geometry.Pos;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

public class Employee {
    /*
     * 
     * Only for table creation
     */
    private String name, department;
    private int emp_id;

    private MFXRadioButton present, absent;
    private ToggleGroup group;

    private HBox group_box;

    public Employee(int id, String name, String department) {
        this.emp_id = id;
        this.name = name;
        this.department = department;

        this.group = new ToggleGroup();
        this.present = new MFXRadioButton("Present");
        this.present.setToggleGroup(group);

        this.absent = new MFXRadioButton("Absent");
        this.absent.setToggleGroup(group);

        this.group_box = new HBox(18, present, absent);
        this.group_box.setAlignment(Pos.CENTER);
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

    public HBox getBox() {
        return group_box;
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
