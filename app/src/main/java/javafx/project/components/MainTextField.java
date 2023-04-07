package javafx.project.components;

import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.enums.FloatMode;

public class MainTextField extends MFXTextField {
    public MainTextField() {
        super();
        super.setWidth(32);
        this.initialize();
    }

    private void initialize() {
        this.borderGapProperty().set(10.45);
        this.prefColumnCountProperty().set(20);
        this.prefHeightProperty().set(USE_PREF_SIZE);
        this.prefWidthProperty().set(USE_COMPUTED_SIZE);
        this.maxHeight(USE_PREF_SIZE);
        this.maxWidth(USE_COMPUTED_SIZE);
        this.floatModeProperty().set(FloatMode.BORDER);
    }
}
