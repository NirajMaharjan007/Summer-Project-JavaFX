package javafx.project.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class Card extends VBox {
    private final String STYLE = "-fx-background-color: #fafafa;" +
            "-fx-background-insets: -1 -5 -1 -1;" +
            "-fx-background-radius: 8 8 4 4;" +
            "-fx-effect: dropshadow(gaussian, rgba(170, 170, 170, 0.5), 16, 0.05, 6, 8);";

    public Card() {
        super();
        super.setSpacing(16);
        VBox.setMargin(this, new Insets(10, 8, 12, 8));
        this.init();
    }

    private void init() {
        // -fx-background-radius: <top-left> <top-right> <bottom-right> <bottom-left>;

        this.setStyle(STYLE);
        this.setAlignment(Pos.TOP_CENTER);
    }

    public void setBgColor(String hexColor) {
        this.setStyle("-fx-background-color: " + hexColor + " !important;" + STYLE);
    }
}
