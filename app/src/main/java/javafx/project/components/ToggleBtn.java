package javafx.project.components;

import io.github.palexdev.materialfx.effects.DepthLevel;
import io.github.palexdev.materialfx.enums.ButtonType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class ToggleBtn extends MainBtn {
    public ToggleBtn(String text) {
        super(text);
        super.setAlignment(Pos.BASELINE_CENTER);
        super.buttonTypeProperty().set(ButtonType.FLAT);
        super.depthLevelProperty().set(DepthLevel.LEVEL0);

        this.init();
        this.initialize();
    }

    private void initialize() {
        this.setPadding(new Insets(12, 10, 12, 10));
        this.setStyle("");
        this.getStyleClass().setAll("toggle-button");
    }

    public void setSize(int width, int height) {
        this.setPrefSize(width, height);
    }
}
