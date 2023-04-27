package javafx.project.components;

import io.github.palexdev.materialfx.controls.MFXRectangleToggleNode;
import io.github.palexdev.materialfx.effects.ripple.RippleClipType;
import io.github.palexdev.materialfx.factories.RippleClipTypeFactory;
import javafx.geometry.Pos;
// import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class ToggleBtn extends MFXRectangleToggleNode {
    public ToggleBtn(String text) {
        super(text);
        super.setAlignment(Pos.BASELINE_CENTER);
        super.setStyle("-fx-background-color: #fafafa;");
        super.setMaxSize(USE_COMPUTED_SIZE, BASELINE_OFFSET_SAME_AS_HEIGHT);

        this.init();
    }

    private void init() {
        this.setRippleClipTypeFactory(new RippleClipTypeFactory(RippleClipType.ROUNDED_RECTANGLE).setArcs(16));
        this.setTextAlignment(TextAlignment.JUSTIFY);
        this.setFocusTraversable(false);
        this.setFocused(false);
    }
}
