package javafx.project.modules;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.project.components.Card;
import javafx.project.enuma.Elements;

public class Attendence extends VBox {
    public Attendence() {
        super(16);

        this.init();
    }

    private void init() {
        Label header = new Label("Attendence of Employees");
        header.setStyle(Elements.HEADER1.getName() + "-fx-text-fill:#484b6a");

        Card card = new Card();
        card.setSpacing(12);

        VBox box = new VBox(16, card);

        this.getChildren().addAll(header, box);
    }

}
