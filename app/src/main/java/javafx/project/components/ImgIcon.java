package javafx.project.components;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class ImgIcon {
    private final Label LABEL;
    private final MainBtn BUTTON;

    public ImgIcon(String url) {
        String string = new File(url).toURI().toString();

        System.out.println("ImgIcon.ImgIcon " + string);

        Image icon = new Image(string, false);

        LABEL = new Label("", new ImageView(icon));

        BUTTON = new MainBtn(new ImageView(icon));

    }

    public Label getIcon() {
        return LABEL;
    }

    public MainBtn getBtnIcon() {
        return BUTTON;
    }
}
