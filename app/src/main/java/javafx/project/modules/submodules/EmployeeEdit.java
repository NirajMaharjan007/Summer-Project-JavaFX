package javafx.project.modules.submodules;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.project.components.*;
import javafx.project.database.*;

public class EmployeeEdit extends BorderPane {
    // private int id;

    private EmpDatabase data = new EmpDatabase(AdminDatabase.getInstance().getId());

    public EmployeeEdit() {
        super();

        this.init();
    }

    // public EmployeeEdit(int id){this.id = id}

    private void init() {
        VBox box = new VBox(16);
        VBox left_Vbox = new VBox(8);

        MainTextField name = new MainTextField("above");
        name.setFloatingText("Enter a employee name");

        box.setPadding(new Insets(8, 12, 4, 16));
        box.setAlignment(Pos.TOP_CENTER);
        box.getChildren().addAll(name);

        left_Vbox.setPadding(new Insets(16));
        left_Vbox.setAlignment(Pos.CENTER);
        left_Vbox.getChildren().addAll(new Label("Hello World"));

        this.setLeft(left_Vbox);
        this.setCenter(box);
    }
}