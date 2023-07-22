package javafx.project.modules;

import java.sql.ResultSet;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.project.components.*;
import javafx.project.database.*;
import javafx.project.enuma.*;
import javafx.project.log.Employee;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Attendence extends VBox {
    int adminId = AdminDatabase.getInstance().getId();

    LocalTime startTime, endTime, now;

    MainBtn refresh, save;

    public Attendence() {
        super(16);
        startTime = LocalTime.of(10, 45);
        endTime = LocalTime.of(17, 30);
        now = LocalTime.now();

        this.init();
    }

    private void init() {
        HBox btn_box = new HBox(16);

        Label header = new Label("Attendence of Employees");
        header.setStyle(Elements.HEADER1.getName());

        Label refresh_icon = new ImgIcon("src/main/resources/img/refresh.png")
                .getIcon();
        refresh_icon.setPadding(new Insets(1, 8, 1, 4));

        Label save_icon = new ImgIcon("src/main/resources/img/check-mark.png")
                .getIcon();
        save_icon.setPadding(new Insets(1, 8, 1, 4));

        refresh = new MainBtn("Refresh");
        refresh_icon.setAlignment(Pos.BASELINE_CENTER);
        refresh.setGraphic(refresh_icon);
        refresh.setBgColor("#36cee6");
        refresh.setTextColor("#fff");
        refresh.setRippleColor(Color.web("#84DED2"));

        save = new MainBtn("Save");
        save.setAlignment(Pos.BASELINE_CENTER);
        save.setGraphic(save_icon);
        save.setBgColor(Elements.SUCCESS_COLOR.getName());
        save.setTextColor("#fff");
        save.setRippleColor(Color.web(Elements.SUCCESS_ALT_COLOR.getName()));

        btn_box.getChildren().addAll(save, refresh);

        VBox inner_container = new VBox();
        VBox.setVgrow(inner_container, Priority.ALWAYS);
        inner_container.setAlignment(Pos.TOP_CENTER);
        inner_container.setPadding(new Insets(8));
        inner_container.setPrefHeight(360);
        inner_container.getChildren().addAll(new Panel());

        this.getChildren().addAll(header, btn_box, inner_container);
    }

    private class Panel extends VBox {

        TableView<Employee> table;
        ObservableList<Employee> data;

        public Panel() {
            super();
            this.setAlignment(Pos.TOP_CENTER);
            if (now.compareTo(startTime) < 0 || now.compareTo(endTime) > 0) {
                this.init();
                table.setDisable(true);
                System.out.println("Attendence.Panel.init() => false");
            } else {
                this.init();
            }
        }

        private void fetchData() {
            try (ResultSet rs = new EmpDatabase(adminId).getData()) {
                while (rs.next()) {
                    data = FXCollections.observableArrayList(
                            new Employee(
                                    rs.getInt("emp_id"),
                                    rs.getString("name"),
                                    rs.getString("department")));
                    table.getItems().addAll(data);
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

        private void init() {
            ScrollPanel scrollPane = new ScrollPanel();
            VBox.setVgrow(scrollPane, Priority.ALWAYS);
            VBox.setMargin(scrollPane, new Insets(4));
            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            scrollPane.setFitToHeight(true);
            scrollPane.setFitToWidth(true);
            scrollPane.setMinViewportHeight(360);

            table = new TableView<>();
            TableColumn<Employee, Integer> idColumn = new TableColumn<>("ID");
            TableColumn<Employee, String> nameCol = new TableColumn<>("Name");
            TableColumn<Employee, String> departmentCol = new TableColumn<>(
                    "Department");
            TableColumn<Employee, String> attent = new TableColumn<>("Attendance");

            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            departmentCol.setCellValueFactory(
                    new PropertyValueFactory<>("department"));
            attent.setCellValueFactory(new PropertyValueFactory<>("box"));

            table.setTableMenuButtonVisible(false);
            table.setColumnResizePolicy(
                    TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
            table.getColumns().add(idColumn);
            table.getColumns().add(nameCol);
            table.getColumns().add(departmentCol);
            table.getColumns().add(attent);
            table
                    .getColumns()
                    .forEach(column -> {
                        column.setMinWidth(32);
                        column.setEditable(false);
                        column.setStyle("-fx-alignment: CENTER");
                    });
            table.setSelectionModel(null);
            table.autosize();

            scrollPane.setContent(table);

            this.fetchData();
            this.getChildren().add(scrollPane);

            refresh.setOnAction(event -> {
                List<Employee> employees = table.getItems();
                EmpDatabase empData = new EmpDatabase(adminId);

                table.getItems().clear();
                this.fetchData();

                for (Employee employee : employees) {
                    String result = empData.getStatus(employee.getId());
                    System.out.println(result);
                    if (result.equalsIgnoreCase("present")) {
                        employee.present.setSelected(true);
                    } else if (result.equalsIgnoreCase("absent")) {
                        employee.absent.setSelected(true);
                    } else {
                        employee.present.setSelected(false);
                        employee.absent.setSelected(false);
                    }
                }
                System.out.println("Attendence.Panel.init()=> Updated");
            });

            save.setOnAction(event -> {
                List<Employee> selectedEmployees = table
                        .getItems()
                        .stream()
                        .filter(Employee::isSelected)
                        .collect(Collectors.toList());

                Alert alert = new Alert(null);
                alert.setTitle("Notifications");
                int i, j;
                i = j = -1;
                for (Employee employee : selectedEmployees) {
                    EmpDatabase empData = new EmpDatabase(adminId);
                    boolean empty = empData.isStatusEmpty(employee.getId());

                    if (empty == false) {
                        i = empData.setStatus(
                                employee.getAttendance(),
                                employee.getId());
                    } else {
                        j = empData.updateStatus(employee.getAttendance(), employee.getId());
                    }

                    System.out.println(
                            "Employee with ID: "
                                    + employee.getId()
                                    + " for "
                                    + employee.getAttendance());
                }

                if (i > -1 || j > -1) {
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Success Notification");
                    alert.setContentText("Success to set in database");
                } else if (now.compareTo(startTime) < 0 || now.compareTo(endTime) > 0) {
                    alert.setAlertType(Alert.AlertType.WARNING);
                    alert.setHeaderText("WARNING Notification");
                    alert.setContentText("WARNING: not in working time");
                } else {
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error Notification");
                    alert.setContentText("Failed to set in database");
                }
                alert.showAndWait();
            });
        }
    }
}
