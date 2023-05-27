package javafx.project.modules.submodules;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.geometry.*;

import javafx.project.components.*;
import javafx.project.database.*;
import javafx.project.enuma.*;

public class EmployeeUpdate extends VBox {
    MainTextField name;
    MainTextField department;
    MainTextField address;
    MainTextField salary;
    MainTextField gender;
    // private int id;

    String gender_text;

    ToggleGroup genderToggleGroup;

    private EmpDatabase data = new EmpDatabase(AdminDatabase.getInstance().getId());

    private int id;

    public EmployeeUpdate(int id) {
        super(16);
        this.id = id;
        this.init();
    }

    private void init() {
        Card card = new Card();
        VBox box = new VBox(26);
        HBox hbox = new HBox(8);
        VBox gender_box = new VBox(8);

        name = new MainTextField("above");
        name.setFloatingText("Enter a employee name");

        department = new MainTextField("above");
        department.setFloatingText("Enter a department");

        address = new MainTextField("above");
        address.setFloatingText("Enter a address");

        salary = new MainTextField("above");
        salary.setFloatingText("Enter a salary");

        Label gender = new Label("Select a gender");
        RadioButton male = new RadioButton("Male");
        RadioButton female = new RadioButton("Female");
        genderToggleGroup = new ToggleGroup();

        male.setToggleGroup(genderToggleGroup);
        female.setToggleGroup(genderToggleGroup);

        genderToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                RadioButton selectedRadioButton = (RadioButton) newValue;
                gender_text = selectedRadioButton.getText();
                System.out.println("Selected radio button: " + gender_text);
            }
        });
        HBox radioBox = new HBox(male, female);
        radioBox.setSpacing(16);
        radioBox.setAlignment(Pos.BASELINE_CENTER);

        gender_box.setAlignment(Pos.TOP_CENTER);
        gender_box.getChildren().addAll(gender, radioBox);

        MainBtn save = new MainBtn("Save");
        save.setBgColor(Elements.SUCCESS_COLOR.getName());
        save.setTextColor("#FFFFFF");
        save.setRippleColor(Color.web(Elements.SUCCESS_ALT_COLOR.getName()));

        save.setOnAction(event -> {
            if (this.isEmpty()) {
                if (data.updateEmployee(this.id, name.getText(), department.getText(), address.getText(),
                        salary.getText(),
                        gender_text) > -1) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Succeed");
                    alert.setHeaderText("successfully Updated employee");
                    alert.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Failed");
                    alert.setHeaderText("Failed added employee");
                    alert.show();
                }
            }
        });

        hbox.setAlignment(Pos.BASELINE_CENTER);
        hbox.getChildren().add(save);

        box.setPadding(new Insets(25, 16, 12, 16));
        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(name, department, address, salary, gender_box, hbox);

        card.setPadding(new Insets(10));
        card.getChildren().add(box);

        this.getChildren().addAll(card);
    }

    protected boolean isEmpty() {
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
        if (genderToggleGroup.getSelectedToggle() == null) {
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
