package javafx.project.modules;

import javafx.geometry.Insets;
import javafx.project.enuma.MainStyle;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class EmployeeModule extends VBox {
    public EmployeeModule() {
        super();
        super.setSpacing(16);
        super.setPadding(new Insets(2, 6, 4, 8));
        VBox.setMargin(this, new Insets(8));

        this.init();
    }

    private void init() {
        Label header1 = new Label("EmployeeModule");
        this.getStylesheets().add(MainStyle.ALT_STYLESHEET.getLocation());

        header1.getStyleClass().add("header1");

        this.getChildren().add(header1);
    }

}
