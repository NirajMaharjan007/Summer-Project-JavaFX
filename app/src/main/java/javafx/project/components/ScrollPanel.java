package javafx.project.components;

import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.geometry.Insets;
import javafx.scene.Node;

public class ScrollPanel extends MFXScrollPane {
    public ScrollPanel() {
        super();
        super.setStyle("-fx-background-color: #fafafa;");
        this.init();

        super.autosize();
    }

    public ScrollPanel(Node node) {
        super(node);
        super.autosize();
        this.init();
    }

    private void init() {
        this.setFitToWidth(true);
        this.setPadding(new Insets(4, 4, 8, 6));
    }

}
