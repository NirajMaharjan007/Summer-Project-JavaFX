package javafx.project.modules;

import java.sql.ResultSet;
import java.util.*;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

        ScrollPanel scrollPanel = new ScrollPanel();
        VBox.setVgrow(scrollPanel, Priority.ALWAYS);
        scrollPanel.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPanel.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPanel.setFitToHeight(true);
        scrollPanel.setFitToWidth(true);
        scrollPanel.setMinViewportHeight(360);
        scrollPanel.setMinViewportWidth(360);
        scrollPanel.setContent(new EmployeeBox());

        this.getChildren().addAll(box, new Label("Hello."), scrollPanel);
    }

    private class EmployeeBox extends GridPane {
        public EmployeeBox() {
            super();
            GridPane.setVgrow(this, Priority.ALWAYS);
            GridPane.setHgrow(this, Priority.ALWAYS);
            GridPane.setFillWidth(this, true);
            GridPane.setFillHeight(this, true);
            GridPane.setMargin(this, new Insets(32));
            super.setPadding(new Insets(8, 32, 16, 32));
            super.setMinWidth(125);
            super.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

            this.init();
        }

        private void init() {
            this.setAlignment(Pos.TOP_CENTER);
            this.setHgap(32);
            this.setVgap(16);

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
            int row = 0, col = 0;
            try {
                while (data.next()) {
                    Card card = new Card();

                    Label id = new Label("Id: " + data.getString(1));
                    Label name = new Label("Name: " + data.getString(2));
                    Label department = new Label("Department: " + data.getString(3));
                    Label address = new Label("Address: " + data.getString(4));
                    Label salary = new Label("Salary: " + data.getString(5));
                    Label gender = new Label("Gender: " + data.getString(6));

                    HBox box = new HBox(12);
                    box.autosize();

                    MainBtn view = new MainBtn("view detail");
                    view.setBgColor("#17a2b8");
                    view.setTextColor("#FFF");
                    view.setRippleColor(Color.web("#AFD3E2"));
                    view.setOnAction(event -> {
                        System.out.println("EmployeeModule.EmployeeBox.employeeCards() " +
                                event.getSource());
                    });

                    box.getChildren().addAll(view);

                    card.setAlignment(Pos.TOP_LEFT);
                    card.setSpacing(12);
                    card.setMinWidth(185);
                    card.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                    card.setPadding(new Insets(16));
                    card.getChildren().addAll(id, name, department, address, salary, gender,
                            box);
                    card_list.add(card);
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

            for (int i = 0; i < card_list.size(); i++) {
                if (col == 3) {
                    row++;
                    col = 0;
                }

                this.add(card_list.get(i), col, row);
                col++;
            }

        }
    }

    private class CreateEmployee extends VBox {
        Stage stage;

        public CreateEmployee() {
            super(16);
            this.init();
            Scene scene = new Scene(this, 600, 420);
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setAlwaysOnTop(false);
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
