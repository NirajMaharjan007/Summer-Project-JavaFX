package javafx.project.components;

import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.geometry.Insets;

public class ScrollPanel extends MFXScrollPane {
    public ScrollPanel() {
        super();
        super.setStyle("-fx-background-color: #dedede;");
        super.autosize();

        this.init();
    }

    private void init() {
        this.setFitToHeight(true);
        this.setFitToWidth(true);
        this.setPadding(new Insets(12, 8, 10, 8));
    }

}
