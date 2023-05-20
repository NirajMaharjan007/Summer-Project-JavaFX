package javafx.project.modules;

import java.sql.ResultSet;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import javafx.project.enuma.*;
import javafx.project.modules.submodules.EmployeeEdit;
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

        this.getChildren().addAll(box, new Label("Hello."), new EmployeeBox());
    }

    private class EmployeeBox extends HBox {
        public EmployeeBox() {
            super(16);
            init();
        }

        private void init() {
            this.setAlignment(Pos.BASELINE_LEFT);

            // Label label = new Label("");

            if (empData.count() >= 1) {
                this.employeeCards();
            } else {
                Label label = new Label("No Employee");
                label.setStyle(Elements.HEADER2.getName() + "-fx-text-fill:#484b6a");
                this.getChildren().add(label);
            }
        }

        private void employeeCards() {
            List<Card> card_list = new ArrayList<>();
            ResultSet data = empData.getData();
            try {
                while (data.next()) {
                    Card card = new Card();

                    Label name = new Label("Name: " + data.getString(2));
                    Label department = new Label("Department: " + data.getString(3));
                    Label address = new Label("Address: " + data.getString(4));
                    Label salary = new Label("Salary: " + data.getString(5));
                    Label gender = new Label("Gender: " + data.getString(6));

                    HBox box = new HBox(12);
                    box.autosize();

                    MainBtn view = new MainBtn("view datail");
                    view.setBgColor("#17a2b8");
                    view.setTextColor("#FFF");
                    view.setRippleColor(Color.web("#AFD3E2"));
                    view.setOnAction(event -> {
                        System.out.println("EmployeeModule.EmployeeBox.employeeCards() " + event.getSource());
                    });

                    box.getChildren().addAll(view);

                    card.setAlignment(Pos.TOP_LEFT);
                    card.setPadding(new Insets(16));
                    card.setMaxWidth(Double.MAX_VALUE);
                    // card.setPrefSize(200, 200);
                    card.autosize();
                    card.getChildren().addAll(name, department, address, salary, gender, box);
                    card_list.add(card);
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

            this.getChildren().addAll(card_list);
        }
    }

    private class CreateEmployee extends VBox {
        Stage stage;

        public CreateEmployee() {
            super(16);
            this.init();
            Scene scene = new Scene(this, 512, 400);
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setAlwaysOnTop(true);
            stage.setScene(scene);
            stage.setTitle("Create Employee");
        }

        private void init() {
            this.setAlignment(Pos.CENTER);
            this.getChildren().add(new EmployeeEdit());
        }

        public void show() {
            stage.show();
        }
    }

}
