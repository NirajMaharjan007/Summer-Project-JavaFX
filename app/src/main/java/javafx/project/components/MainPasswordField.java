package javafx.project.components;

import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.enums.FloatMode;

public class MainPasswordField extends MFXPasswordField {

    public MainPasswordField() {
        super();
        init();
    }

    public MainPasswordField(String type) {
        super();
        super.setWidth(16);

        try {
            this.init();
            switch (type.toLowerCase()) {
                case "above":
                    this.floatModeProperty().set(FloatMode.ABOVE);
                    break;

                case "normal":
                    break;

                case "inline":
                    this.floatModeProperty().set(FloatMode.INLINE);
                    break;

                default:
                    throw new IllegalArgumentException(type + " not found");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void init() {
        this.borderGapProperty().set(12.45);
        this.prefColumnCountProperty().set(17);
        this.prefHeightProperty().set(USE_PREF_SIZE);
        this.prefWidthProperty().set(USE_COMPUTED_SIZE);
        this.maxHeight(USE_PREF_SIZE);
        this.maxWidth(USE_COMPUTED_SIZE);
        this.floatModeProperty().set(FloatMode.BORDER);
    }

}
