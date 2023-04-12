package javafx.project.components;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.effects.DepthLevel;
import io.github.palexdev.materialfx.enums.ButtonType;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class MainBtn extends MFXButton {
    String color;

    public MainBtn(String text) {
        super(text);
        super.setPadding(new Insets(6, 12, 6, 12));
        this.init();
    }

    public void setBgColor(String colorHEX) {
        color = colorHEX;
        this.setStyle("-fx-background-color:" + colorHEX);
    }

    public void setTextColor(String colorHEX) {
        if (color != null)
            this.setStyle("-fx-text-fill:" + colorHEX + ";-fx-background-color:" + color);
        else
            this.setStyle("-fx-text-fill:" + colorHEX);
    }

    public void init() {
        this.setRippleAnimateBackground(true);
        this.buttonTypeProperty().set(ButtonType.RAISED);
        this.depthLevelProperty().set(DepthLevel.LEVEL2);
        this.setRippleAnimationSpeed(0.70);
        this.setRippleBackgroundOpacity(0.35);
        this.setRippleRadius(25);
        this.setTextAlignment(TextAlignment.CENTER);
        this.setRippleColor(Color.web("#cdcdcd"));
        this.setFocusTraversable(false);
        this.setFocused(false);
    }

}
