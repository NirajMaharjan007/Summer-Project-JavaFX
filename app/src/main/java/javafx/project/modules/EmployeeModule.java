package javafx.project.modules;

import java.util.*;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import javafx.project.enuma.*;
import javafx.project.components.*;
import javafx.project.database.*;

public class EmployeeModule extends VBox {
    private EmpDatabase empData = new EmpDatabase(AdminDatabase.getInstance().getId());

    public EmployeeModule() {
        super();
        super.setSpacing(16);
        super.setPadding(new Insets(2, 6, 4, 8));
        VBox.setMargin(this, new Insets(8));

        this.init();
    }

    private void init() {
        this.getStylesheets().add(MainStyle.ALT_STYLESHEET.getLocation());

        Label header1 = new Label("EmployeeModule");
        header1.getStyleClass().add("header1");
        header1.setAlignment(Pos.CENTER);

        Label add_icon = new ImgIcon("src/main/resources/img/add.png").getIcon();
        add_icon.setPadding(new Insets(1, 8, 1, 2));

        MainBtn create = new MainBtn("Add Employee");
        create.setGraphic(add_icon);
        create.setAlignment(Pos.CENTER);
        create.setBgColor(Elements.SUCCESS_COLOR.getName());
        create.setTextColor("#fff");
        create.setRippleColor(Color.web(Elements.SUCCESS_ALT_COLOR.getName()));
        create.setOnAction(event -> {
            new CreateEmployee().show();
        });

        BorderPane box = new BorderPane();

        box.setLeft(header1);
        box.setRight(create);

        EmployeeBox hbox = new EmployeeBox();

        this.getChildren().addAll(box, new Label("Hello."), hbox);
    }

    private class EmployeeBox extends HBox {
        public EmployeeBox() {
            super(16);
            init();
        }

        private void init() {
            List<Card> card_list = new ArrayList<>();

            if (empData.count() >= 1) {
                for (int i = 0; i < empData.count(); i++) {
                    Card card = new Card();
                    card.setMaxWidth(Double.MAX_VALUE);
                    card.setPrefSize(200, 200);
                    card.getChildren().add(new Label("Test"));
                    card_list.add(card);
                }
                this.getChildren().addAll(card_list);
            } else {
                Label label = new Label("No Employee");
                label.setStyle(Elements.HEADER2.getName());
                this.getChildren().add(label);
            }
        }
    }

    private class CreateEmployee extends VBox {
        Stage stage;

        public CreateEmployee() {
            super(16);
            Scene scene = new Scene(this, 512, 400);
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setAlwaysOnTop(true);
            stage.setScene(scene);
            stage.setTitle("Create Employee");
        }

        public void show() {
            stage.show();
        }
    }

}
