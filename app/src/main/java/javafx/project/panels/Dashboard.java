package javafx.project.panels;

import io.github.palexdev.materialfx.css.themes.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.*;

public class Dashboard extends BorderPane {
    Stage stage;

    public Dashboard(Stage stage) {
        super();
        super.autosize();
        super.setPadding(new Insets(6, 12, 6, 12));

        Scene scene = new Scene(this);
        MFXThemeManager.addOn(scene, Themes.DEFAULT, Themes.LEGACY);
        init();
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.setTitle("Dashboard");
        stage.setScene(scene);
        stage.show();
    }

    private void init() {
        this.setRight(new MainDash());
    }

    private class MainDash extends VBox {
        public MainDash() {
            super();
            super.autosize();
            super.setPadding(new Insets(6, 12, 6, 12));
            super.setSpacing(10);

            init();
        }

        private void init() {
            this.getChildren().add(new Label("Dashboard"));
        }
    }
}
