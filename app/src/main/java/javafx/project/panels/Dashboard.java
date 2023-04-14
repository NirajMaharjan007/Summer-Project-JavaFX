package javafx.project.panels;

import javafx.project.components.*;

import io.github.palexdev.materialfx.css.themes.*;
import javafx.scene.control.ScrollPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.project.enuma.Elements;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Dashboard extends BorderPane {
    Stage stage, newStage;

    public Dashboard(Stage stage) {
        super();
        this.stage = stage;

        this.newStage = new Stage();

        super.autosize();
        // super.setPadding(new Insets(6, 12, 6, 12));

        Scene scene = new Scene(this, 600, 400);
        MFXThemeManager.addOn(scene, Themes.DEFAULT, Themes.LEGACY);

        init();

        newStage.centerOnScreen();
        newStage.setResizable(false);
        newStage.setTitle("Dashboard");
        newStage.setScene(scene);
        newStage.show();
    }

    private void init() {
        this.setCenter(new MainDash());
        this.setLeft(new SideBar());
    }

    private class MainDash extends VBox {
        public MainDash() {
            super();
            super.autosize();
            super.setSpacing(10);
            super.setPadding(new Insets(8, 16, 4, 32));

            this.init();
        }

        private void init() {
            Card card = new Card();

            Label header = new Label("Header");
            header.setStyle(Elements.HEADER1.getName());
            header.setAlignment(Pos.CENTER);

            MainBtn logout = new MainBtn("Logout");
            logout.setBgColor(Elements.DANGER_COLOR.getName());
            logout.setTextColor("White");
            logout.setRippleColor(Color.web(Elements.DANGER_ALT_COLOR.getName()));

            logout.setOnAction(event -> {
                stage.setTitle("Login Panel");
                stage.show();
                newStage.close();
            });

            VBox container = new VBox();
            VBox.setMargin(container, new Insets(8));
            container.setPadding(new Insets(4, 8, 2, 8));
            container.autosize();

            container.getChildren().addAll(header, logout);

            card.setPrefHeight(newStage.getWidth());
            card.getChildren().add(container);

            this.getChildren().add(card);
        }
    }

    private class SideBar extends VBox {
        public SideBar() {
            super();
            super.setSpacing(12);
            super.setPrefHeight(newStage.getMaxHeight());
            super.autosize();

            this.init();
        }

        private void init() {
            this.setPadding(new Insets(18, 8, 12, 8));
            this.setStyle("-fx-background-color: #454545;" +
                    "-fx-background-insets: -1 -5 -1 -1;" +
                    "-fx-background-radius: 0 16 8 0;" +
                    "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.3), 10, 0.05, 6, 4);");

            Label headerLabel = new Label("Side Bar");
            headerLabel.setStyle(Elements.HEADER1.getName() + "-fx-text-fill:#FFE6C7");
            headerLabel.setGraphicTextGap(4);
            this.getChildren().add(headerLabel);

            Label menuLabel = new Label("Components");
            menuLabel.setStyle(Elements.HEADER2.getName() + "-fx-text-fill:#FFE6C7");
            menuLabel.setPadding(new Insets(20, 8, 2, 8));
            this.getChildren().add(menuLabel);

            ScrollPanel scrollPane = new ScrollPanel();
            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

            VBox.setVgrow(scrollPane, Priority.ALWAYS);
            Insets scrollPaneMargin = new Insets(0, 5, 10, 5);
            VBox.setMargin(scrollPane, scrollPaneMargin);

            VBox navBar = new VBox();
            navBar.setStyle("-fx-background-color: #454545;" +
                    "-fx-background-radius: 0;" +
                    "-fx-border-color: transparent;" +
                    "-fx-border-radius: 0;" +
                    "-fx-pref-height: 42;" +
                    "-fx-font-size: 12;");
            navBar.setSpacing(10);
            navBar.setMaxWidth(Double.MAX_VALUE);
            navBar.setMaxHeight(Double.MAX_VALUE);

            for (int i = 1; i <= 20; i++) {
                Label l = new Label(i + ". label");
                l.setStyle("-fx-text-fill: #FFE6C7");
                navBar.getChildren().add(l);
            }

            scrollPane.setContent(navBar);

            this.getChildren().add(scrollPane);
        }

    }
}
