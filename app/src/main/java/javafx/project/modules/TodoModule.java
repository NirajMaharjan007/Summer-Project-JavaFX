package javafx.project.modules;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

public class TodoModule extends VBox implements EventHandler<DragEvent> {

    public TodoModule() {
        super(16);
        super.setPadding(new Insets(2, 4, 2, 4));
        VBox.setMargin(this, new Insets(8));

        this.init();
    }

    private void init() {
        FlowPane pane = new FlowPane();

        pane.getChildren().add(new Label("Todo"));

        this.setAlignment(Pos.CENTER);
        this.getChildren().add(pane);
    }

    @Override
    public void handle(DragEvent event) {
        throw new UnsupportedOperationException("Unimplemented method 'handle'");
    }

}
