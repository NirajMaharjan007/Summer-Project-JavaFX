package javafx.project.modules.submodules;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.project.components.*;
import javafx.project.database.*;
import javafx.project.enuma.Elements;

public class EmployeeEdit extends BorderPane {
    MainTextField name;
    // MainTextField name;

    MainTextField department;
    MainTextField address;
    MainTextField salary;
    MainTextField gender;
    // private int id;

    private EmpDatabase data = new EmpDatabase(AdminDatabase.getInstance().getId());

    public EmployeeEdit() {
        super();

        this.init();
    }

    // public EmployeeEdit(int id){this.id = id}

    private void init() {
        Card card = new Card();
        VBox box = new VBox(26);
        VBox left_Vbox = new VBox(8);
        HBox hbox = new HBox(8);

        name = new MainTextField("above");
        name.setFloatingText("Enter a employee name");

        department = new MainTextField("above");
        department.setFloatingText("Enter a department");

        address = new MainTextField("above");
        address.setFloatingText("Enter a address");

        salary = new MainTextField("above");
        salary.setFloatingText("Enter a salary");

        gender = new MainTextField("above");
        gender.setFloatingText("Enter a gender");

        MainBtn save = new MainBtn("Save");
        save.setBgColor(Elements.SUCCESS_COLOR.getName());
        save.setTextColor("#FFFFFF");
        save.setRippleColor(Color.web(Elements.SUCCESS_ALT_COLOR.getName()));

        save.setOnAction(event -> {
            this.validate();
        });

        hbox.setAlignment(Pos.BASELINE_CENTER);
        hbox.getChildren().add(save);

        box.setPadding(new Insets(25, 16, 12, 16));
        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(name, department, address, salary, gender, hbox);

        card.setPadding(new Insets(10));
        card.getChildren().add(box);

        left_Vbox.setPadding(new Insets(16));
        left_Vbox.setAlignment(Pos.CENTER);
        left_Vbox.getChildren().addAll(new Label("Hello World"));

        this.setLeft(left_Vbox);
        this.setCenter(card);
        this.setRight(new VBox(new Label("\t")));
    }

    public boolean validate() {
        StringBuilder errors = new StringBuilder();

        // Confirm mandatory fields are filled out
        if (name.getText().trim().isEmpty()) {
            errors.append("- Please enter a name.\n");
        }
        if (department.getText().trim().isEmpty()) {
            errors.append("- Please enter a department.\n");
        }
        if (address.getText().trim().isEmpty()) {
            errors.append("- Please enter a middle initial.\n");
        }
        if (salary.getText().trim().isEmpty()) {
            errors.append("- Please enter a address.\n");
        }
        if (gender.getText().trim().isEmpty()) {
            errors.append("- Please enter a gender.\n");
        }

        // If any missing information is found, show the error messages and return false
        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Required Fields Empty");
            alert.setContentText(errors.toString());

            alert.show();
            return false;
        }

        // No errors
        return true;
    }
}