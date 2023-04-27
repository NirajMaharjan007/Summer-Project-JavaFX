package javafx.project.components;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class ToggleBtn extends MFXButton {
    public ToggleBtn(String text) {
        super(text);
        super.setAlignment(Pos.BASELINE_CENTER);

        this.init();
    }

    private void init() {
        this.setRippleAnimateBackground(true);
        this.setRippleAnimationSpeed(0.70);
        this.setRippleBackgroundOpacity(0.35);
        this.setRippleRadius(25);
        this.setTextAlignment(TextAlignment.CENTER);
        this.setRippleColor(Color.web("#cdcdcd"));
        this.setFocusTraversable(false);
        this.setFocused(false);
        this.setTextAlignment(TextAlignment.JUSTIFY);
        this.setFocusTraversable(true);
        this.setFocused(true);
    }
}
