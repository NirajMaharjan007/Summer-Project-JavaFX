package javafx.project.modules.submodules;

import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Themes;

import javafx.geometry.*;
import javafx.stage.*;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import javafx.project.database.AdminDatabase;
import javafx.project.enuma.Elements;
import javafx.project.components.*;
import javafx.project.modules.TodoModule;

public class TodoOption extends VBox {
    private final Stage stage;
    private final Scene scene;

    private MainComboBox<String> combo_box;
    private TextArea field;
    private MainBtn add_btn;
    private VBox box;
    private TodoModule.Diary pane;

    public TodoOption(TodoModule.Diary pane) {
        super();
        this.pane = pane;

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

    private int insert(String title, String description) {
        AdminDatabase admin = AdminDatabase.getInstance();
        return admin.setTodos(title, description);
    }

    private void initialize() {
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
        add_btn.setOnAction(e -> {
            Alert alert = new Alert(null);
            if (!field.getText().isEmpty()) {
                int i = this.insert(combo_box.getSelectedItem().toString(), field.getText());
                if (i > -1) {
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText("Todo Added");
                    alert.setContentText("Todo Added Successfully");
                    alert.show();
                    field.clear();
                    pane.refresh();
                } else {
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Failed");
                    alert.setHeaderText("Failed");
                    alert.setContentText("Failed to insert");
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
        });

        MainBtn cancel = new MainBtn("Cancel");
        cancel.setBgColor(Elements.DANGER_COLOR.getName());
        cancel.setRippleColor(Color.web(Elements.DANGER_ALT_COLOR.getName()));
        cancel.setTextColor("White");
        cancel.setOnAction(event -> stage.close());

        FlowPane pane = new FlowPane();
        pane.setAlignment(Pos.CENTER_LEFT);
        pane.setPadding(new Insets(8));
        pane.getChildren().add(combo_box);

        HBox hbox = new HBox(8);
        hbox.setAlignment(Pos.BASELINE_LEFT);
        hbox.setPadding(new Insets(8));
        hbox.getChildren().addAll(add_btn, cancel);

        field = new TextArea();
        field.setWrapText(true);
        field.setPromptText("Enter text");
        field.setMinSize(320, 320);

        box.getChildren().addAll(pane, field, hbox);

        this.setPadding(new Insets(16));
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(box);
    }
}
