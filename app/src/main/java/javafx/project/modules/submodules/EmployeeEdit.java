package javafx.project.modules.submodules;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.project.components.*;
import javafx.project.database.*;

public class EmployeeEdit extends BorderPane {
    private int id;

    private EmpDatabase data = new EmpDatabase(AdminDatabase.getInstance().getId());

    public EmployeeEdit() {
        super();

        this.init();
    }

    // public EmployeeEdit(int id){this.id = id}

    private void init() {
        VBox box = new VBox(16);
        MainTextField name = new MainTextField("above");
        name.setFloatingText("Enter a employee name");

        box.setAlignment(Pos.TOP_CENTER);
        box.getChildren().addAll(name);

        this.setLeft(new Label("Hello"));
        this.setCenter(box);
    }
}