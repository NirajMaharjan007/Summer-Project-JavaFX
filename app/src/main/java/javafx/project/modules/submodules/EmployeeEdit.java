package javafx.project.modules.submodules;

import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import javafx.geometry.*;
import javafx.scene.paint.Color;

import javafx.project.components.*;
import javafx.project.database.*;
import javafx.project.enuma.Elements;

public class EmployeeEdit extends BorderPane {
    MainTextField name;
    MainTextField department;
    MainTextField address;
    MainTextField salary;
    MainTextField gender;
    // private int id;

    String gender_text;

    ToggleGroup genderToggleGroup;

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
        MFXRadioButton male = new MFXRadioButton("Male");
        MFXRadioButton female = new MFXRadioButton("Female");
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
                if (data.setData(name.getText(), department.getText(), address.getText(), salary.getText(),
                        gender_text) > -1) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Succeed");
                    alert.setHeaderText("successfully added employee");
                    alert.setContentText("Required to Refresh");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(AlertType.ERROR);
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

        left_Vbox.setPadding(new Insets(16));
        left_Vbox.setAlignment(Pos.CENTER);
        left_Vbox.getChildren().add(new Label("\t"));

        this.setLeft(left_Vbox);
        this.setCenter(card);
        this.setRight(new VBox(new Label("\t")));
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
            errors.append("- Please select a gender.\n");
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