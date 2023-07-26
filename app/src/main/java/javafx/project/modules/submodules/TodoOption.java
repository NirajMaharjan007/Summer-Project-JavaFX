package javafx.project.modules.submodules;

import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Themes;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.layout.*;

import javafx.project.components.MainComboBox;

public class TodoOption extends Pane {
    private Stage stage;
    private Scene scene;
    private MainComboBox<String> combo_box;
    private VBox box;

    public TodoOption() {
        super();
        this.initialize();

        scene = new Scene(this, 400, 350);
        MFXThemeManager.addOn(scene, Themes.DEFAULT, Themes.LEGACY);

        stage = new Stage();
        stage.setTitle("Options");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setAlwaysOnTop(false);
        stage.setScene(scene);
        stage.requestFocus();
    }

    public void show() {
        stage.show();
    }

    private void initialize() {
        box = new VBox(8);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(16, 8, 4, 8));

        combo_box = new MainComboBox<>();
        combo_box.setAlignment(Pos.BASELINE_LEFT);
        combo_box.setFloatingText("List");
        combo_box.getItems().addAll("Notes", "Todos", "Done");
        combo_box.getSelectionModel().selectFirst();

        box.getChildren().addAll(combo_box);

        this.setPadding(new Insets(16));
        this.getChildren().addAll(box);
    }
}