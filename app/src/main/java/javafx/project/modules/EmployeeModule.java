package javafx.project.modules;

import io.github.palexdev.materialfx.css.themes.*;
import java.sql.ResultSet;
import java.util.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.project.components.*;
import javafx.project.database.*;
import javafx.project.enuma.*;
import javafx.project.modules.submodules.*;
import javafx.project.panels.Dashboard;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;

public class EmployeeModule extends VBox {

    public static Stage stage;

    private int adminId = AdminDatabase.getInstance().getId();
    private EmpDatabase empData = new EmpDatabase(adminId);

    private ScrollPanel scrollPanel;
    private EmployeeBox emp_box;

    public static MainBtn exit = new MainBtn("Exit");

    public EmployeeModule() {
        super(16);
        super.setPadding(new Insets(2, 4, 2, 4));
        VBox.setMargin(this, new Insets(8));

        scrollPanel = new ScrollPanel();
        emp_box = new EmployeeBox();

        this.init();
    }

    public void justRefresh() {
        System.out.println("Refreshed");
        emp_box.getChildren().clear();
        scrollPanel.setContent(new EmployeeBox());
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

        scrollPanel.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPanel.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPanel.setMinViewportWidth(400);
        scrollPanel.setMinViewportHeight(480);
        scrollPanel.setFitToHeight(true);
        scrollPanel.setFitToWidth(true);
        scrollPanel.setContent(emp_box);
        VBox.setVgrow(scrollPanel, Priority.ALWAYS);

        Label refresh_icon = new ImgIcon("src/main/resources/img/refresh.png")
                .getIcon();
        refresh_icon.setPadding(new Insets(1, 8, 1, 2));

        MainBtn refresh = new MainBtn("Refresh");
        refresh.setAlignment(Pos.BASELINE_CENTER);
        refresh.setGraphic(refresh_icon);
        refresh.setBgColor("#36cee6");
        refresh.setTextColor("#fff");
        refresh.setRippleColor(Color.web("#84DED2"));
        refresh.setOnAction(event -> justRefresh());

        HBox box = new HBox(16);
        HBox.setHgrow(box, Priority.ALWAYS);
        HBox.setMargin(box, new Insets(16, 4, 8, 4));

        box.getChildren().addAll(create, refresh);

        this.getChildren().addAll(header1, box, scrollPanel);
    }

    private class EmployeeBox extends FlowPane {
        public EmployeeBox() {
            super();
            super.setOrientation(Orientation.HORIZONTAL);
            super.setPadding(new Insets(8, 16, 12, 16));
            super.setMaxSize(
                    Dashboard.getStage().getMaxWidth(),
                    Dashboard.getStage().getMaxHeight());
            super.setMinWidth(Dashboard.getStage().getMinWidth());
            super.setPrefHeight(Dashboard.getStage().getHeight());

            this.init();
        }

        private void init() {
            this.setAlignment(Pos.TOP_CENTER);
            this.setHgap(64);
            this.setVgap(32);

            if (empData.count() >= 1) {
                this.employeeCards();
            } else {
                Label label = new Label("No Employee");
                label.setStyle(Elements.HEADER2.getName());
                this.getChildren().add(label);
            }
        }

        private class Delete implements EventHandler<ActionEvent> {

            private int id;

            public Delete(int id) {
                this.id = id;
            }

            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Are you sure about that? ");
                alert.setContentText("You are deleting the Id " + this.id);
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        if (empData.deleteEmployee(String.valueOf(this.id)) > -1) {
                            Alert info = new Alert(Alert.AlertType.INFORMATION);
                            info.setTitle("Information");
                            info.setHeaderText(
                                    "Employee Id: " + this.id + " has been deleted");
                            info.show();
                            System.out.println(this.id + ":Id is deleted");
                            justRefresh();
                        }
                    } else {
                        alert.close();
                    }
                });
            }
        }

        private void employeeCards() {
            List<Card> card_list = new ArrayList<>();

            try (ResultSet data = new EmpDatabase(adminId).getData();) {
                while (data.next()) {
                    Card card = new Card();

                    Label id = new Label("Id: " + data.getString(1));
                    Label name = new Label("Name: " + data.getString(2));
                    Label department = new Label("Department: " + data.getString(3));
                    Label address = new Label("Address: " + data.getString(4));
                    Label salary = new Label("Salary: " + data.getString(5));
                    Label gender = new Label("Gender: " + data.getString(6));

                    MainBtn view = new MainBtn("View detail");
                    view.setBgColor("#17a2b8");
                    view.setTextColor("#FFF");
                    view.setRippleColor(Color.web("#AFD3E2"));
                    view.setOnAction(new View(data.getInt(1)));

                    MainBtn delete = new MainBtn("Delete");
                    delete.setBgColor(Elements.DANGER_COLOR.getName());
                    delete.setTextColor("#FFF");
                    delete.setRippleColor(Color.web(Elements.DANGER_ALT_COLOR.getName()));
                    delete.setOnAction(new Delete(data.getInt(1)));

                    HBox box = new HBox(12);
                    box.getChildren().addAll(view, delete);
                    box.autosize();

                    card.setAlignment(Pos.TOP_LEFT);
                    card.setSpacing(16);
                    card.setMinWidth(200);
                    card.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                    card.setPadding(new Insets(16));
                    card.getChildren()
                            .addAll(id, name, department, address, salary, gender, box);
                    card_list.add(card);
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            this.getChildren().addAll(card_list);
        }
    }

    private class View implements EventHandler<ActionEvent> {

        private int id;

        public View(int id) {
            this.id = id;
        }

        @Override
        public void handle(ActionEvent event) {
            new ShowDetail(id).show();
        }
    }

    private class ShowDetail extends VBox {

        private int id;

        public ShowDetail(int id) {
            super(8);
            this.id = id;
            this.init();

            Scene scene = new Scene(this, 650, 420);
            MFXThemeManager.addOn(scene, Themes.DEFAULT, Themes.LEGACY);

            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setAlwaysOnTop(false);
            stage.setScene(scene);
            stage.setTitle("View Detail");
        }

        private void init() {
            this.setAlignment(Pos.CENTER);
            this.getChildren().add(new ViewDetail(this.id));
        }

        public void show() {
            stage.show();
        }
    }

    public class CreateEmployee extends VBox {

        EmployeeEdit edit = new EmployeeEdit(this);

        public CreateEmployee() {
            super(16);
            this.init();

            Scene scene = new Scene(this, 600, 420);
            MFXThemeManager.addOn(scene, Themes.DEFAULT, Themes.LEGACY);

            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setAlwaysOnTop(false);
            stage.setScene(scene);
            stage.setTitle("Create Employee");
        }

        private void init() {
            this.setAlignment(Pos.CENTER);
            this.getChildren().add(edit);
        }

        public void show() {
            stage.show();
        }

        public void refresh() {
            justRefresh();
        }
    }
}
