package javafx.project.panels;

import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.project.components.*;

public class MainLogin extends VBox {
    MainPasswordField passwordField;
    MainTextField adminField;

    public MainLogin() {
        super();
        super.setPadding(new Insets(8, 16, 8, 16));

        passwordField = new MainPasswordField();
        adminField = new MainTextField();

        init();
    }

    private void init() {
        this.setStyle("-fx-background-color: #eee");

        Grid grid = new Grid();

        HBox hlayout = new HBox();

        hlayout.setPadding(new Insets(10, 4, 8, 4));

        MainBtn btn = new MainBtn("Login");
        btn.setBgColor("#2f921f");
        btn.setTextColor("White");
        btn.setRippleColor(Color.web("#45df15"));

        MainBtn cancle = new MainBtn("Cancel");
        cancle.setBgColor("#ef2000");
        cancle.setTextColor("White");
        cancle.setRippleColor(Color.web("#f05152"));
        HBox.setMargin(cancle, new Insets(0, 0, 0, 8));

        btn.setOnAction(e -> {
            Alert a = new Alert(AlertType.INFORMATION);
            a.setContentText("hello");
            a.setTitle("hello");
            a.show();
        });

        cancle.setOnAction(e -> {
            System.exit(0);
        });

        hlayout.getChildren().add(btn);
        hlayout.getChildren().add(cancle);

        this.getChildren().add(grid);
        this.getChildren().add(hlayout);
    }

    protected class Grid extends GridPane {
        public Grid() {
            super();
            this.init();
        }

        private void init() {
            this.setAlignment(Pos.CENTER);

            this.setHgap(4);
            this.setVgap(10);

            adminField.floatingTextProperty().set("Enter a Admin name");

            passwordField.floatingTextProperty().set("Enter a Password");

            this.add(adminField, 1, 0);
            this.add(passwordField, 1, 1);
        }
    }

}
