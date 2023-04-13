package javafx.project.components;

import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.geometry.Insets;

public class ScrollPanel extends MFXScrollPane {
    public ScrollPanel() {
        super();
        super.autosize();
        super.setStyle("-fx-background-color: #454545;");

        this.init();
    }

    private void init() {
        this.setFitToWidth(true);
        this.setPadding(new Insets(12, 4, 8, 4));
    }

}
