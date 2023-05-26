package javafx.project.modules.submodules;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;

import javafx.project.components.*;
import javafx.project.enuma.*;

public class ViewDetail extends BorderPane {
    private int id;

    public ViewDetail(int id) {
        super();
        super.setManaged(true);

        this.id = id;
        this.init();
    }

    private void init() {
        Card card = new Card();
        card.setAlignment(Pos.TOP_CENTER);

        Label header = new Label("Detail of Employee id: " + this.id);
        header.setStyle(Elements.HEADER1.getName() + "-fx-text-fill:#484b6a");

        card.getChildren().addAll(header);

        this.setCenter(card);
    }

}
