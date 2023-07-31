package javafx.project.modules.submodules;

import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Themes;

import javafx.geometry.*;
import javafx.stage.*;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import javafx.project.components.MainComboBox;
import javafx.project.enuma.Elements;
import javafx.project.components.*;
import javafx.project.modules.TodoModule;

public class TodoOption extends VBox {
    private final Stage stage;
    private final Scene scene;

    private MainComboBox<String> combo_box;
    private TextField field;
    private MainBtn add_btn;
    private VBox box;

    public TodoOption() {
        super();

        scene = new Scene(this, 400, 400);
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

    private void initialize() {
        box = new VBox(8);
        box.setAlignment(Pos.TOP_CENTER);
        box.setPadding(new Insets(16, 8, 4, 8));

        combo_box = new MainComboBox<>();
        combo_box.setAlignment(Pos.TOP_LEFT);
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

        HBox hbox = new HBox(8);
        hbox.setAlignment(Pos.TOP_LEFT);
        hbox.setPadding(new Insets(2, 4, 1, 4));
        hbox.getChildren().addAll(combo_box, add_btn);

        field = new TextField();
        field.setAlignment(Pos.TOP_LEFT);
        field.setPromptText("Enter text");
        field.setMinSize(320, 320);

        box.getChildren().addAll(hbox, field);

        this.setPadding(new Insets(16));
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(box);
    }
}
