package javafx.project.panels;

import java.sql.Connection;

import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.project.components.*;
import javafx.project.database.AdminDatabase;
import javafx.project.database.Database;
import javafx.project.enuma.Elements;

public class MainLogin extends VBox {
    MainPasswordField passwordField;
    MainTextField adminField;

    Connection data = Database.getConnection();

    AdminDatabase admin = new AdminDatabase();

    Stage stage;

    public MainLogin(Stage stage) {
        super();
        super.setPadding(new Insets(8, 16, 8, 16));

        this.stage = stage;

        passwordField = new MainPasswordField();
        adminField = new MainTextField();

        if (data != null)
            init();
        else
            failed();
    }

    private void failed() {
        stage.setTitle("Error ");
        stage.toFront();
        stage.setAlwaysOnTop(true);

        MainBtn cancel = new MainBtn("Cancel");
        cancel.setBgColor("#ef2000");
        cancel.setTextColor("White");
        cancel.setRippleColor(Color.web("#f05152"));

        VBox.setMargin(cancel, new Insets(16, 0, 4, 0));

        cancel.setOnAction(e -> {
            System.exit(0);
        });

        Label l = new Label("Error; while connecting to the database");
        l.setStyle(Elements.HEADER2.getName());

        this.getChildren().add(l);
        this.getChildren().add(cancel);
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

        MainBtn cancel = new MainBtn("Cancel");
        cancel.setBgColor("#ef2000");
        cancel.setTextColor("White");
        cancel.setRippleColor(Color.web("#f05152"));
        HBox.setMargin(cancel, new Insets(0, 0, 0, 8));

        KeyEventHandler keyEventHandler = new KeyEventHandler();
        adminField.setOnKeyPressed(keyEventHandler);
        passwordField.setOnKeyPressed(keyEventHandler);

        btn.setOnAction(new ActionEventHandler());

        cancel.setOnAction(e -> {
            System.exit(0);
        });

        hlayout.getChildren().add(btn);
        hlayout.getChildren().add(cancel);

        stage.setTitle("Login Page");

        this.getChildren().add(grid);
        this.getChildren().add(hlayout);
    }

    private class KeyEventHandler implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent e) {
            if (e.getCode() == KeyCode.ENTER) {
                if (admin.setLogin(adminField.getText(), adminField.getText())) {
                    new Dashboard(new Stage());
                    System.out.println("Login");
                    stage.close();
                } else if (data == null) {
                    System.err.println("Can't connect to the database");
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Error!");
                    alert.setHeaderText("Cannot connect to the database");
                    alert.setContentText("Please try in another time");
                    alert.show();
                } else {
                    System.err.println("Not Logged In");
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Invalid Credentials");
                    alert.setContentText("Please try again");
                    alert.show();
                }
            }
        }
    }

    private class ActionEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            if (admin.setLogin(adminField.getText(), adminField.getText())) {
                new Dashboard(new Stage());
                System.out.println("Login");
                stage.close();
            } else if (data == null) {
                System.err.println("Can't connect to the database");
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Error!");
                alert.setHeaderText("Cannot connect to the database");
                alert.setContentText("Please try in another time");
                alert.show();
            } else {
                System.err.println("Not Logged In");
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Credentials");
                alert.setContentText("Please try again");
                alert.show();
            }
        }
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
