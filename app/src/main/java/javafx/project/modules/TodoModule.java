package javafx.project.modules;

import java.util.*;

import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

import javafx.project.components.*;
import javafx.project.enuma.Elements;
import javafx.project.log.Log;
import javafx.project.modules.submodules.TodoOption;

public class TodoModule extends VBox {
    private VBox box;

    private Log log;

    public TodoModule() {
        super(16);
        super.setPadding(new Insets(2, 4, 2, 4));
        VBox.setMargin(this, new Insets(8));

        log = Log.getInstance();

        this.init();
    }

    private void init() {
        Label header = new Label("To-Do List");
        header.setStyle(Elements.HEADER1.getName());

        VBox pane = new VBox();
        VBox.setVgrow(pane, Priority.ALWAYS);
        pane.setPadding(new Insets(8));

        ScrollPanel scrollPanel = new ScrollPanel(pane);
        VBox.setVgrow(scrollPanel, Priority.ALWAYS);
        HBox.setHgrow(scrollPanel, Priority.ALWAYS);
        scrollPanel.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPanel.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPanel.setMinViewportWidth(450);
        scrollPanel.setMinViewportHeight(420);
        scrollPanel.setFitToHeight(true);
        scrollPanel.setFitToWidth(true);
        scrollPanel.setContent(pane);

        box = new VBox(16);
        box.setPadding(new Insets(8, 4, 8, 4));
        box.getChildren().add(scrollPanel);

        this.setAlignment(Pos.TOP_CENTER);
        this.getChildren().addAll(header, box);
    }

    public class Diary extends FlowPane {
        private List<Card> card_list;

        public Diary() {
            super();
            this.initialize();
        }

        public void refresh() {
            this.getChildren().clear();
        }

        private void initialize() {
            this.setPadding(new Insets(8));
            this.setMinSize(350, 350);

            card_list = new ArrayList<>();
        }

        private void fetch() {

        }
    }

}
