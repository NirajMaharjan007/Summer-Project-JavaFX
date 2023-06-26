package javafx.project.modules;

import java.sql.ResultSet;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.*;

import javafx.project.components.*;
import javafx.project.database.*;
import javafx.project.enuma.*;
import javafx.project.log.Employee;

public class Attendence extends VBox {
    int adminId = AdminDatabase.getInstance().getId();
    MainBtn refresh;

    public Attendence() {
        super(16);

        this.init();
    }

    private void init() {
        HBox btn_box = new HBox(16);

        Label header = new Label("Attendence of Employees");
        header.setStyle(Elements.HEADER1.getName() + "-fx-text-fill:#484b6a");

        Label refresh_icon = new ImgIcon("src/main/resources/img/refresh.png").getIcon();
        refresh_icon.setPadding(new Insets(1, 8, 1, 4));

        refresh = new MainBtn("Refresh");
        refresh.setAlignment(Pos.BASELINE_CENTER);
        refresh.setGraphic(refresh_icon);
        refresh.setBgColor("#36cee6");
        refresh.setTextColor("#fff");
        refresh.setRippleColor(Color.web("#84DED2"));
        refresh.setOnAction(event -> {
            System.out.println("Attendence.Panel.init()\tRefreshed");
        });

        btn_box.getChildren().addAll(refresh);

        VBox inner_container = new VBox();
        inner_container.setAlignment(Pos.TOP_LEFT);

        inner_container.getChildren().addAll(new Panel());

        this.getChildren().addAll(header, btn_box, inner_container);
    }

    private class Panel extends VBox {
        public Panel() {
            super();
            this.setAlignment(Pos.TOP_CENTER);
            this.init();
        }

        private void init() {
            ObservableList<Employee> data = FXCollections.observableArrayList(
                    new Employee(5, "Niraj maharjan", "GG"));

            TableView<Employee> table = new TableView<>();
            TableColumn<Employee, Integer> idColumn = new TableColumn<>("ID");
            TableColumn<Employee, String> nameCol = new TableColumn<>("Name");
            TableColumn<Employee, String> departmentCol = new TableColumn<>("Department");

            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            departmentCol.setCellValueFactory(new PropertyValueFactory<>("department"));

            // Set the editable property of each column to false
            idColumn.setEditable(false);
            nameCol.setEditable(false);
            departmentCol.setEditable(false);
            departmentCol.setMaxWidth(1f * Integer.MAX_VALUE);

            table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
            table.getColumns().add(idColumn);
            table.getColumns().add(nameCol);
            table.getColumns().add(departmentCol);
            table.setItems(data);
            // table.getColumns().forEach(column -> column.setMinWidth(128));
            // table.autosize();

            this.getChildren().add(table);

            /*
             * int adminId = AdminDatabase.getInstance().getId();
             * try (ResultSet data = new EmpDatabase(adminId).getData()) {
             * while (data.next()) {
             * }
             * } catch (Exception e) {
             * System.err.println(e.getMessage());
             * } finally {
             * }
             */

        }
    }
}
