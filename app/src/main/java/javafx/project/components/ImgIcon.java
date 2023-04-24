package javafx.project.components;


import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImgIcon {
    private Label label;

    public ImgIcon(String url) {
        Image icon = new Image(url);

        // Create an ImageView for the Image
        ImageView imageView = new ImageView(icon);

        // Create a Label with text and the ImageView as the graphic
        label = new Label("", imageView);
    }

    public Label getIcon() {
        return label;
    }
}
