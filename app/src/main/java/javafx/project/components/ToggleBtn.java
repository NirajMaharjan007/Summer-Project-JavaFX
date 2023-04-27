package javafx.project.components;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.geometry.Pos;
// import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class ToggleBtn extends MFXButton {
    public ToggleBtn(String text) {
        super(text);
        super.setAlignment(Pos.BASELINE_CENTER);

        this.init();
    }

    private void init() {
        this.setTextAlignment(TextAlignment.JUSTIFY);
        this.setFocusTraversable(true);
        this.setFocused(true);
    }
}
