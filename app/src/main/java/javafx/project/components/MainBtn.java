package javafx.project.components;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.effects.DepthLevel;
import io.github.palexdev.materialfx.enums.ButtonType;
import javafx.geometry.Insets;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class MainBtn extends MFXButton {
    String color, colorHex;

    public MainBtn(String text) {
        super(text);
        super.setPadding(new Insets(8, 12, 8, 12));
        super.buttonTypeProperty().set(ButtonType.RAISED);
        super.depthLevelProperty().set(DepthLevel.LEVEL2);

        this.init();
    }

    public MainBtn(ImageView imageView) {
        super("", imageView);
        super.setStyle("-fx-background-color:transparent;");
        super.setPadding(new Insets(16));
        super.buttonTypeProperty().set(ButtonType.FLAT);
        super.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        super.setMaxSize(28, 28);
        super.setMinSize(12, 12);

        this.init();
    }

    public void setBgColor(String colorHEX) {
        color = colorHEX;
        this.setStyle("-fx-background-color:" + colorHEX);
    }

    public void setTextColor(String colorHEX) {
        this.colorHex = colorHEX;
        if (color != null)
            this.setStyle("-fx-text-fill:" + colorHEX + ";-fx-background-color:" + color);
        else
            this.setStyle("-fx-text-fill:" + colorHEX);
    }

    public void init() {
        this.setRippleAnimateBackground(true);
        this.setRippleAnimationSpeed(0.70);
        this.setRippleBackgroundOpacity(0.35);
        this.setRippleRadius(25);
        this.setTextAlignment(TextAlignment.CENTER);
        this.setRippleColor(Color.web("#cdcdcd"));
        this.setFocusTraversable(false);
        this.setFocused(false);
    }

    public void setSize(int width, int height) {
        this.setPrefSize(width, height);
    }

}
