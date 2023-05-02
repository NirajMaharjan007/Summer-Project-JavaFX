package javafx.project.modules;

import javafx.geometry.Insets;
import javafx.project.enuma.MainStyle;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MainModule extends VBox {
    public MainModule() {
        super();
        super.setSpacing(16);
        super.setPadding(new Insets(2, 6, 4, 8));
        VBox.setMargin(this, new Insets(8));

        this.init();
    }

    private void init() {
        this.getStylesheets().add(MainStyle.ALT_STYLESHEET.getLocation());

        Label header1 = new Label("Dashboard");
        Label header2 = new Label("Details");

        header1.getStyleClass().add("header1");
        header2.getStyleClass().add("header2-underline");

        this.getChildren().addAll(header1, header2);

    }
}
