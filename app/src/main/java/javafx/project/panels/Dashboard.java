package javafx.project.panels;

import javafx.scene.control.ScrollPane;
import io.github.palexdev.materialfx.css.themes.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.project.components.Card;
import javafx.project.components.ScrollPanel;
import javafx.project.enuma.Elements;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.*;

public class Dashboard extends BorderPane {
    Stage stage;

    public Dashboard(Stage stage) {
        super();
        this.stage = stage;

        super.autosize();
        super.setPadding(new Insets(6, 12, 6, 12));

        Scene scene = new Scene(this, 600, 400);
        MFXThemeManager.addOn(scene, Themes.DEFAULT, Themes.LEGACY);

        init();

        stage.centerOnScreen();
        stage.setResizable(false);
        stage.setTitle("Dashboard");
        stage.setScene(scene);
        stage.show();
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
            super.setPadding(new Insets(8, 12, 4, 32));

            this.init();
        }

        private void init() {
            Card card = new Card();

            Label header = new Label("Header");
            header.setStyle(Elements.HEADER1.getName());
            header.setAlignment(Pos.CENTER);

            VBox container = new VBox();
            container.autosize();

            card.setPrefHeight(stage.getWidth());
            card.getChildren().addAll(header, container);

            this.getChildren().add(card);
        }
    }

    private class SideBar extends VBox {
        public SideBar() {
            super();
            super.autosize();
            super.setPadding(new Insets(6, 12, 6, 12));
            super.setSpacing(10);
            super.setLayoutX(225);
            super.setLayoutY(50);
            super.setPrefWidth(150);

            this.init();
        }

        private void init() {
            this.setPadding(new Insets(18, 8, 12, 8));
            this.setStyle("-fx-background-color: #dedede;" +
                    "-fx-background-insets: -1 -5 -1 -1;" +
                    "-fx-background-radius: 0 20 20 0;" +
                    "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.31), 20, 0.1, 4, 8);");

            Label headerLabel = new Label("Side Bar");
            headerLabel.setStyle(Elements.HEADER1.getName());
            // headerLabel.setGraphicTextGap(4);
            this.getChildren().add(headerLabel);

            Label menuLabel = new Label("Components");
            menuLabel.setStyle(Elements.HEADER2.getName());
            menuLabel.setPadding(new Insets(20, 8, 2, 8));
            this.getChildren().add(menuLabel);

            ScrollPanel scrollPane = new ScrollPanel();
            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

            VBox.setVgrow(scrollPane, Priority.ALWAYS);
            Insets scrollPaneMargin = new Insets(0, 5, 10, 5);
            VBox.setMargin(scrollPane, scrollPaneMargin);
            scrollPane.setContent(new VBox());

            VBox navBar = new VBox();
            navBar.setStyle("-fx-background-color: #dedede;" +
                    "-fx-background-radius: 0;" +
                    "-fx-border-color: transparent;" +
                    "-fx-border-radius: 0;" +
                    "-fx-pref-height: 42;" +
                    "-fx-font-size: 13;" +
                    "-fx-text-fill: black;");
            navBar.setSpacing(5);
            navBar.setMaxWidth(Double.MAX_VALUE);
            navBar.setMaxHeight(Double.MAX_VALUE);

            navBar.getChildren().addAll(
                    new Label("1"),
                    new Label("2"),
                    new Label("3"));

            scrollPane.setContent(navBar);

            this.getChildren().add(scrollPane);
        }

    }
}
