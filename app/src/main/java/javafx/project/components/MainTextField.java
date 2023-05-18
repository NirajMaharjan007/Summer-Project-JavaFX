package javafx.project.components;

import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.enums.FloatMode;

public class MainTextField extends MFXTextField {
    public MainTextField() {
        super();
        super.setWidth(32);
        this.initialize();
        this.floatModeProperty().set(FloatMode.BORDER);
    }

    public MainTextField(String type) {
        super();
        super.setWidth(16);

        try {
            this.initialize();
            switch (type.toLowerCase()) {
                case "above":
                    this.floatModeProperty().set(FloatMode.ABOVE);
                    break;

                case "normal":
                    break;

                default:
                    throw new IllegalArgumentException(type + " not found");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void initialize() {
        this.borderGapProperty().set(12.45);
        this.prefColumnCountProperty().set(20);
        this.prefHeightProperty().set(USE_PREF_SIZE);
        this.prefWidthProperty().set(USE_COMPUTED_SIZE);
        this.maxHeight(USE_PREF_SIZE);
        this.maxWidth(USE_COMPUTED_SIZE);
    }

}
