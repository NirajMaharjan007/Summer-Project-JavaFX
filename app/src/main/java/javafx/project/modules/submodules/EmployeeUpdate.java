package javafx.project.modules.submodules;

import java.sql.ResultSet;

import io.github.palexdev.materialfx.controls.MFXRadioButton;
import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Themes;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;
import javafx.geometry.*;

import javafx.project.components.*;
import javafx.project.database.*;
import javafx.project.enuma.*;
import javafx.project.modules.EmployeeModule;

public class EmployeeUpdate extends Card {
    MainTextField name;
    MainTextField department;
    MainTextField address;
    MainTextField salary;
    MainTextField gender;
    MainTextField email;
    MainTextField phone;

    String gender_text;

    ToggleGroup genderToggleGroup;

    private Stage stage = new Stage();

    private BorderPane pane = new BorderPane();

    private EmpDatabase empData = new EmpDatabase(AdminDatabase.getInstance().getId());

    private int id;

    public EmployeeUpdate(int id) {
        super.setSpacing(16);

        this.id = id;
        this.init();

        Scene scene = new Scene(this, 600, 450);
        MFXThemeManager.addOn(scene, Themes.DEFAULT, Themes.LEGACY);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setAlwaysOnTop(false);
        stage.setScene(scene);
        stage.setTitle("Update Employee");
    }

    public void show() {
        stage.show();
    }

    private void init() {
        VBox.setMargin(this, new Insets(32));
        VBox.setVgrow(this, Priority.ALWAYS);

        this.getChildren().add(pane);
        this.atLeft();
        this.atRight();
        this.atBottom();
    }

    private void atLeft() {
        VBox mainBox = new VBox(10);
        mainBox.setPadding(new Insets(16));

        VBox box = new VBox(26);
        HBox hbox = new HBox(8);
        VBox gender_box = new VBox(8);

        Label gender = new Label("Select a gender");
        MFXRadioButton male = new MFXRadioButton("Male");
        MFXRadioButton female = new MFXRadioButton("Female");
        genderToggleGroup = new ToggleGroup();

        HBox radioBox = new HBox(male, female);

        name = new MainTextField("above");
        name.setFloatingText("Enter a employee name");

        department = new MainTextField("above");
        department.setFloatingText("Enter a department");

        address = new MainTextField("above");
        address.setFloatingText("Enter a address");

        salary = new MainTextField("above");
        salary.setFloatingText("Enter a salary");

        male.setToggleGroup(genderToggleGroup);
        female.setToggleGroup(genderToggleGroup);

        genderToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                RadioButton selectedRadioButton = (RadioButton) newValue;
                gender_text = selectedRadioButton.getText();
                System.out.println("Selected radio button: " + gender_text);
            }
        });

        try (ResultSet data = empData.getData(this.id)) {
            while (data.next()) {
                name.setText(data.getString("name"));
                department.setText(data.getString("department"));
                address.setText(data.getString("address"));
                salary.setText(data.getString("salary"));

                String text = data.getString("gender");

                if (text.equalsIgnoreCase("male"))
                    male.setSelected(true);
                else
                    female.setSelected(true);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            radioBox.setSpacing(16);
            radioBox.setAlignment(Pos.BASELINE_CENTER);
            gender_box.setAlignment(Pos.TOP_CENTER);
            gender_box.getChildren().addAll(gender, radioBox);

            hbox.setAlignment(Pos.BASELINE_CENTER);
            // hbox.getChildren().add(save);

            box.setPadding(new Insets(25, 8, 12, 16));
            box.setAlignment(Pos.CENTER);
            box.getChildren().addAll(name, department, address, salary, gender_box, hbox);

            mainBox.getChildren().add(box);

            pane.setLeft(mainBox);
        }

    }

    private void atBottom() {
        MainBtn save = new MainBtn("Save");
        save.setBgColor(Elements.SUCCESS_COLOR.getName());
        save.setTextColor("#FFFFFF");
        save.setRippleColor(Color.web(Elements.SUCCESS_ALT_COLOR.getName()));

        save.setOnAction(event -> {
            if (this.isEmpty()) {
                if (empData.updateEmployee(this.id, name.getText(), department.getText(),
                        address.getText(), salary.getText(), gender_text, email.getText(), phone.getText()) > -1) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Succeed");
                    alert.setHeaderText("successfully Updated employee");
                    alert.setContentText("Required to Refresh");
                    alert.showAndWait().ifPresent(action -> {
                        stage.close();
                        EmployeeModule.stage.close();
                    });
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Failed");
                    alert.setHeaderText("Failed update employee");
                    alert.show();
                }
            }
        });

        MainBtn cancel = new MainBtn("Cancel");
        cancel.setBgColor(Elements.DANGER_COLOR.getName());
        cancel.setRippleColor(Color.web(Elements.DANGER_ALT_COLOR.getName()));
        cancel.setTextColor("White");

        cancel.setOnAction(event -> {
            stage.close();
        });

        HBox hbox = new HBox(16, save, cancel);
        hbox.setAlignment(Pos.CENTER);

        pane.setBottom(hbox);
    }

    private void atRight() {
        VBox mainBox = new VBox(10);
        mainBox.setPadding(new Insets(16));

        VBox box = new VBox(26);

        email = new MainTextField("Above");
        email.setFloatingText("Enter a email");

        phone = new MainTextField("Above");
        phone.setFloatingText("Enter a phone number");

        try (ResultSet data = empData.getData(id)) {
            while (data.next()) {
                email.setText(data.getString("email"));
                phone.setText(data.getString("phone"));
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            box.setPadding(new Insets(25, 16, 12, 8));
            box.setAlignment(Pos.CENTER);
            box.getChildren().addAll(email, phone);

            mainBox.getChildren().add(box);

            pane.setRight(mainBox);
        }

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
        if (phone.getText().trim().isEmpty() || phone.getText().trim().equalsIgnoreCase("n/a")) {
            errors.append("- Please enter contact number.\n");
        }
        if (email.getText().trim().isEmpty() || email.getText().trim().equalsIgnoreCase("n/a")) {
            errors.append("- Please enter email address.\n");
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
