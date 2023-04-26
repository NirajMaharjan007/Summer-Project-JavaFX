package javafx.project.components;

import javafx.scene.control.ContentDisplay;
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

        // Create an ImageView for the Image
        ImageView imageView = new ImageView(icon);

        // Create a Label with text and the ImageView as the graphic
        LABEL = new Label("", imageView);

        BUTTON = new MainBtn("Icon");
        BUTTON.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        BUTTON.setGraphic(imageView);
    }

    public Label getIcon() {
        return LABEL;
    }

    public MainBtn getBtnIcon() {
        return BUTTON;
    }
}
