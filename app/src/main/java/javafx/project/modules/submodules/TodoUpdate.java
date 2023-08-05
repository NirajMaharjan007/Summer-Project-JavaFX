package javafx.project.modules.submodules;

import java.sql.ResultSet;

import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Themes;

import javafx.project.components.*;
import javafx.project.database.AdminDatabase;
import javafx.project.enuma.Elements;
import javafx.project.modules.TodoModule;

import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.stage.*;

public class TodoUpdate extends VBox {
    private final Stage stage;
    private final Scene scene;

    private MainComboBox<String> combo_box;
    private TextField field;
    private MainBtn add_btn;
    private VBox box;
    private TodoModule.Diary pane;
    private AdminDatabase admin;

    public TodoUpdate(TodoModule.Diary pane) {
        super();
        this.pane = pane;

        admin = AdminDatabase.getInstance();

        scene = new Scene(this, 400, 460);
        MFXThemeManager.addOn(scene, Themes.DEFAULT, Themes.LEGACY);

        stage = new Stage();
        stage.setTitle("Options");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.requestFocus();
        stage.setResizable(false);
        stage.setAlwaysOnTop(false);
        stage.setScene(scene);

        this.initialize();
    }

    public void show() {
        stage.show();
    }

    private int update(String title, String description) {
        return -1;
    }

    private void initialize() {
        try (ResultSet rs = admin.getTodos()) {
            box = new VBox(8);
            box.setAlignment(Pos.TOP_CENTER);
            box.setPadding(new Insets(16, 8, 4, 8));

            combo_box = new MainComboBox<>();
            combo_box.setAlignment(Pos.TOP_LEFT);
            combo_box.setMinWidth(160);
            combo_box.setFloatingText("List");
            combo_box.getItems().addAll("Very Important", "Less Important", "Not Important");
            combo_box.getSelectionModel().selectFirst();

            Label add_icon = new ImgIcon("src/main/resources/img/add.png").getIcon();

            add_btn = new MainBtn("Add");
            add_btn.setAlignment(Pos.CENTER);
            add_btn.setGraphic(add_icon);
            add_btn.setBgColor(Elements.SUCCESS_COLOR.getName());
            add_btn.setTextColor("#fff");
            add_btn.setRippleColor(Color.web(Elements.SUCCESS_ALT_COLOR.getName()));
            add_btn.setOnAction(e -> showAlert());

            MainBtn cancel = new MainBtn("Cancel");
            cancel.setBgColor(Elements.DANGER_COLOR.getName());
            cancel.setRippleColor(Color.web(Elements.DANGER_ALT_COLOR.getName()));
            cancel.setTextColor("White");
            cancel.setOnAction(event -> stage.close());

            field = new TextField();
            field.setAlignment(Pos.TOP_LEFT);
            field.setPromptText("Enter text");
            field.setMinSize(320, 320);

            FlowPane flow_pane = new FlowPane();
            flow_pane.setAlignment(Pos.CENTER_LEFT);
            flow_pane.setPadding(new Insets(8));
            flow_pane.getChildren().add(combo_box);

            HBox hbox = new HBox(8);
            hbox.setAlignment(Pos.BASELINE_LEFT);
            hbox.setPadding(new Insets(8));
            hbox.getChildren().addAll(add_btn, cancel);

            String title = "", description = "";
            int id = 0;
            while (rs.next()) {
                title = rs.getString("title");
                description = rs.getString("description");
                id = rs.getInt("id");
            }

            for (String str : combo_box.getItems()) {
                if (str.equalsIgnoreCase(title))
                    combo_box.setValue(str);
            }
            field.setText(description);

            box.getChildren().addAll(flow_pane, field, hbox);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            this.setPadding(new Insets(16));
            this.setAlignment(Pos.CENTER);
            this.getChildren().addAll(box);
        }

    }

    private void showAlert() {
        Alert alert = new Alert(null);
        if (!field.getText().isEmpty()) {
            int i = this.update(combo_box.getSelectedItem().toString(), field.getText());
            if (i > -1) {
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Todo Added");
                alert.setContentText("Todo Update Successfully");
                alert.show();
                field.clear();
                pane.refresh();
            } else {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Failed");
                alert.setHeaderText("Failed");
                alert.setContentText("Failed to Update");
                alert.show();
                pane.refresh();
            }
        } else {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Failed");
            alert.setContentText("Text field is empty");
            alert.show();
        }
    }
}
