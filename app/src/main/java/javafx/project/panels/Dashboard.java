package javafx.project.panels;

import io.github.palexdev.materialfx.css.themes.*;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.*;

public class Dashboard extends BorderPane {
    Stage stage;

    public Dashboard(Stage stage) {
        super();

        Scene scene = new Scene(this, 100, 100);
        MFXThemeManager.addOn(scene, Themes.DEFAULT, Themes.LEGACY);

        stage.centerOnScreen();
        stage.setResizable(false);
        stage.setTitle("Dashboard");
        stage.setScene(scene);
        stage.show();
    }
}
