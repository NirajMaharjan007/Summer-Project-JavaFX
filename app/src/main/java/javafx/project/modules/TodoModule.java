package javafx.project.modules;

import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

import javafx.project.components.*;
import javafx.project.enuma.Elements;
import javafx.project.log.Log;

public class TodoModule extends VBox {
    private Log log;
    private MainComboBox<String> combo_box;

    public TodoModule() {
        super(16);
        super.setPadding(new Insets(2, 4, 2, 4));
        VBox.setMargin(this, new Insets(8));

        log = Log.getInstance();
        combo_box = new MainComboBox<>();

        this.init();
    }

    private void init() {
        Label header = new Label("To-Do List");
        header.setStyle(Elements.HEADER1.getName());

        FlowPane pane = new FlowPane(Orientation.HORIZONTAL);
        pane.setPadding(new Insets(8));
        pane.getChildren().addAll(new Note());

        ScrollPanel scrollPanel = new ScrollPanel(pane);
        VBox.setVgrow(scrollPanel, Priority.ALWAYS);
        HBox.setHgrow(scrollPanel, Priority.ALWAYS);
        scrollPanel.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPanel.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPanel.setMinViewportWidth(420);
        scrollPanel.setMinViewportHeight(400);
        scrollPanel.setFitToHeight(true);
        scrollPanel.setFitToWidth(true);
        scrollPanel.setContent(pane);

        HBox layout = new HBox(8);
        layout.getChildren().add(combo_box);
        layout.setAlignment(Pos.BASELINE_LEFT);

        VBox box = new VBox(16);
        box.setPadding(new Insets(8, 4, 8, 4));
        box.setAlignment(Pos.TOP_CENTER);
        box.getChildren().addAll(layout, scrollPanel);

        this.getChildren().addAll(header, box);
    }

    private class Note extends Card {
        private FlowPane flow;

        public Note() {
            super();
            super.setMaxHeight(512);
            super.setPrefHeight(325);

            super.setMinWidth(256);
            super.setPrefWidth(325);

            flow = new FlowPane(Orientation.VERTICAL);

            this.initialization();
            this.getChildren().add(flow);
        }

        private void initialization() {
            this.setSpacing(8);
            this.setPadding(new Insets(16));

            VBox box = new VBox(16);

            Label label = new Label("Notes");
            label.setStyle(Elements.HEADER2.getName());

            Separator separator = new Separator();
            separator.setPrefWidth(300);
            separator.setHalignment(HPos.CENTER);
            separator.setValignment(VPos.CENTER);

            box.getChildren().addAll(label, separator);
            flow.getChildren().add(box);
        }

    }

}
