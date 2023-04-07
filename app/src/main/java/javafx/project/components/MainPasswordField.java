package javafx.project.components;

import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.enums.FloatMode;

public class MainPasswordField extends MFXPasswordField {
    public MainPasswordField() {
        super();
        init();
    }

    private void init() {
        this.borderGapProperty().set(10.45);
        this.prefColumnCountProperty().set(17);
        this.prefHeightProperty().set(USE_PREF_SIZE);
        this.prefWidthProperty().set(USE_COMPUTED_SIZE);
        this.maxHeight(USE_PREF_SIZE);
        this.maxWidth(USE_COMPUTED_SIZE);
        this.floatModeProperty().set(FloatMode.BORDER);
    }

}
