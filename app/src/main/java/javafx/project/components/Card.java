package javafx.project.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class Card extends VBox {
    public Card() {
        super();
        super.setSpacing(10);
        VBox.setMargin(this, new Insets(12, 4, 8, 4));
        this.init();
    }

    void init() {
        // -fx-background-radius: <top-left> <top-right> <bottom-right> <bottom-left>;
        this.setStyle("-fx-background-color: #fafafa;" +
                "-fx-pref-height: 100%;" +
                "-fx-pref-width: 100%;" +
                "-fx-background-insets: -1 -5 -1 -1;" +
                "-fx-background-radius: 8 8 4 4;" +
                "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.30), 16, 0.05, 4, 8);");
        this.setAlignment(Pos.TOP_CENTER);
    }

}
