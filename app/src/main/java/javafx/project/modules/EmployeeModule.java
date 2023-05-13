package javafx.project.modules;

import io.github.palexdev.materialfx.controls.*;

import javafx.geometry.Insets;
import javafx.project.database.EmpDatabase;
import javafx.project.enuma.MainStyle;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EmployeeModule extends VBox {
    private EmpDatabase empData = EmpDatabase.getInstance();

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

        VBox box = new VBox(12);

        EmployeeTable table = new EmployeeTable();

        box.getChildren().add(table);

        header1.getStyleClass().add("header1");

        this.getChildren().addAll(header1, box);
    }

    private class CreateEmployee extends VBox {
        Stage stage;

        public CreateEmployee() {
            super(16);
            Scene scene = new Scene(this, 450, 450);
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setTitle("Create Employee");
        }

        public void show() {
            stage.show();
        }
    }

    private class EmployeeTable extends MFXTableView<String> {
        public EmployeeTable() {
            super();

            this.init();
        }

        private void init() {
            this.setMinWidth(Double.MIN_NORMAL);
            this.setMaxWidth(Double.MAX_VALUE);
            this.setPrefWidth(800);

            MFXTableColumn<String> nameColumn = new MFXTableColumn<>("Name", false);
            MFXTableColumn<String> surnameColumn = new MFXTableColumn<>("Surname", false);
            MFXTableColumn<String> ageColumn = new MFXTableColumn<>("Age", false);

            getTableColumns().add(nameColumn);
            getTableColumns().add(surnameColumn);
            getTableColumns().add(ageColumn);

            this.autosizeColumn(8);
        }
    }

}
