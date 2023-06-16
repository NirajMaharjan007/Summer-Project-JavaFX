package javafx.project.modules.submodules;

import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Themes;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.project.components.*;
import javafx.project.enuma.Elements;
import javafx.project.enuma.MainStyle;
import javafx.project.modules.Attendence;
import javafx.project.modules.EmployeeModule;
import javafx.project.modules.MainModule;

public class AdminOption extends Pane {
    private Stage stage;
    private Scene scene;

    static VBox container;

    public AdminOption() {
        super();
        scene = new Scene(this, 800, 600);
        container = new VBox();

        MFXThemeManager.addOn(scene, Themes.DEFAULT, Themes.LEGACY);

        stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.setTitle("Admin's Setting");

        this.init();
    }

    public void show() {
        stage.showAndWait();
    }

    private void init() {
        this.setPadding(new Insets(8));
        this.getChildren().add(new MainPanel());
    }

    private class MainPanel extends BorderPane {
        public MainPanel() {
            super();

            this.atLeft();
            this.atCenter();
        }

        private void atCenter() {
            container.setPadding(new Insets(4, 8, 2, 16));
            container.getChildren().add(new AdminDetail());
            this.setCenter(container);
        }

        private void atLeft() {
            this.setLeft(new SideBar());
        }
    }

    private class AdminDetail extends Card {
        public AdminDetail() {
            super();
            this.setPrefHeight(580);
            this.setPrefWidth(500);
            this.setAlignment(Pos.TOP_CENTER);
            this.init();
        }

        private void init() {
            VBox box = new VBox(16);
            box.setAlignment(Pos.CENTER);

            Label title = new Label("Admin's Details");
            title.setStyle(Elements.HEADER1.getName() + "-fx-text-fill:#484b6a");

            box.getChildren().addAll(title);

            this.getChildren().addAll(new Label("GG"), box);
        }
    }

    private class SideBar extends VBox {
        public SideBar() {
            super();
            super.setSpacing(12);
            super.setPrefHeight(600);
            super.setPrefWidth(250);
            // super.autosize();

            this.init();
        }

        private void init() {
            this.setPadding(new Insets(18, 8, 12, 8));
            this.getStylesheets().add(MainStyle.STYLESHEET.getLocation());
            this.getStyleClass().addAll("sidebar");

            Label menuLabel = new Label("Admin's Sidebar menu");
            menuLabel.setStyle(Elements.HEADER2.getName() + "-fx-text-fill:#484b6a");
            menuLabel.setPadding(new Insets(8, 2, 4, 2));
            this.getChildren().add(menuLabel);

            ScrollPanel scrollPane = new ScrollPanel();
            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

            VBox.setVgrow(scrollPane, Priority.ALWAYS);
            Insets scrollPaneMargin = new Insets(0, 5, 10, 5);
            VBox.setMargin(scrollPane, scrollPaneMargin);

            VBox navBar = new VBox();
            navBar.getStylesheets().add(MainStyle.STYLESHEET.getLocation());
            navBar.getStyleClass().add("navbar");
            navBar.setSpacing(10);
            navBar.setMaxWidth(Double.MAX_VALUE);
            navBar.setMaxHeight(Double.MAX_VALUE);

            MainBtn[] btn = new MainBtn[5];

            for (int i = 0; i < 5; i++) {
                btn[i] = new MainBtn("");
                btn[i].setSize((int) navBar.getMaxWidth(), 120);
                btn[i].setBgColor("#C7E0EA");
                btn[i].setRippleColor(Color.web("#ddecf2"));
                btn[i].setTextColor("#484b6a; -fx-font-weight: bold; -fx-font-size:14px");
                btn[i].buttonTypeProperty().set(io.github.palexdev.materialfx.enums.ButtonType.RAISED);
                btn[i].depthLevelProperty().set(io.github.palexdev.materialfx.effects.DepthLevel.LEVEL1);
            }

            Label emp_icon = new ImgIcon("src/main/resources/img/user.png").getIcon();
            emp_icon.setPadding(new Insets(4, 10, 4, 2));

            Label dash_icon = new ImgIcon("src/main/resources/img/monitor.png").getIcon();
            dash_icon.setPadding(new Insets(4, 8, 4, 2));

            Label attend_icon = new ImgIcon("src/main/resources/img/attendence.png").getIcon();
            attend_icon.setPadding(new Insets(4, 8, 4, 2));

            btn[0].setGraphic(dash_icon);
            btn[0].setText("Admin Detail");

            btn[1].setGraphic(emp_icon);
            btn[1].setText("Admin Update");

            btn[2].setGraphic(attend_icon);
            btn[2].setText("About us");

            navBar.getChildren().addAll(btn[0], btn[1], btn[2]);

            scrollPane.setContent(navBar);

            this.getChildren().add(scrollPane);
        }

    }
}
