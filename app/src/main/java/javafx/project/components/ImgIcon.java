package javafx.project.components;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

import io.github.palexdev.materialfx.controls.MFXCircleToggleNode;

public class ImgIcon {
    private final Label LABEL;
    private final MainBtn BUTTON;
    private final MFXCircleToggleNode TOGGLE_CIRCLE;

    public ImgIcon(String url) {
        String string = new File(url).toURI().toString();

        Image icon = new Image(string, false);

        LABEL = new Label("", new ImageView(icon));

        BUTTON = new MainBtn(new ImageView(icon));

        TOGGLE_CIRCLE = new MFXCircleToggleNode("", new ImageView(icon));

    }

    public Label getIcon() {
        return LABEL;
    }

    public MainBtn getBtnIcon() {
        return BUTTON;
    }

    public MFXCircleToggleNode getToggleButton() {
        return TOGGLE_CIRCLE;
    }
}
