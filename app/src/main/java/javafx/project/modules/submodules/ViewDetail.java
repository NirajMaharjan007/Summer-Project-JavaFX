package javafx.project.modules.submodules;

import java.sql.ResultSet;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;

import javafx.project.components.*;
import javafx.project.database.*;
import javafx.project.enuma.*;

public class ViewDetail extends BorderPane {
    private final Insets PADDING = new Insets(2, 16, 4, 16);
    private int id;
    private EmpDatabase empDatabase = new EmpDatabase(AdminDatabase.getInstance().getId());

    public ViewDetail(int id) {
        super();
        super.setManaged(true);

        this.id = id;
        this.init();
    }

    private void init() {

        Label header = new Label("Detail of Employee id: " + this.id);
        header.setStyle(Elements.HEADER1.getName() + "-fx-text-fill:#484b6a");

        VBox top = new VBox(8, new Card(header));
        top.setPadding(PADDING);

        this.setTop(top);
        this.atCenter();
    }

    private void atCenter() {
        try (ResultSet data = empDatabase.getData(this.id)) {
            Card card = new Card();
            VBox center = new VBox(16, card);
            center.setPadding(PADDING);
            while (data.next()) {
                card.getChildren().addAll(new Label(data.getString(1)));
            }
            this.setCenter(center);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

}
