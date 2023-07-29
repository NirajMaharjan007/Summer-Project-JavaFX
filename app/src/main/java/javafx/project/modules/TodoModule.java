package javafx.project.modules;

import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.*;

import javafx.project.components.*;
import javafx.project.enuma.Elements;
import javafx.project.log.Log;
import javafx.project.modules.submodules.TodoOption;

public class TodoModule extends VBox {
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

        FlowPane pane = new FlowPane(Orientation.HORIZONTAL);
        pane.setPadding(new Insets(8));
        pane.setHgap(32);
        pane.setVgap(16);
        pane.getChildren().addAll(new Note(), new Todos(), new Done());

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

        VBox box = new VBox(16);
        box.setPadding(new Insets(8, 4, 8, 4));
        box.setAlignment(Pos.TOP_CENTER);
        box.getChildren().add(scrollPanel);

        this.getChildren().addAll(header, box);
    }

    private class Arrangement extends Card {
        protected FlowPane flow;
        protected Label label;
        private MainBtn add_btn;

        public Arrangement() {
            super();
            super.setMaxHeight(325);
            super.setPrefHeight(315);

            super.setMinWidth(256);
            super.setPrefWidth(300);

            flow = new FlowPane(Orientation.VERTICAL);

            this.initialization();
            this.getChildren().add(flow);
        }

        private void initialization() {
            this.setSpacing(16);
            this.setPadding(new Insets(16));
            this.setAlignment(Pos.TOP_CENTER);

            VBox box = new VBox(16);

            label = new Label();
            label.setStyle(Elements.HEADER2.getName());

            Separator separator = new Separator();
            separator.setPrefWidth(300);
            separator.setHalignment(HPos.CENTER);
            separator.setValignment(VPos.CENTER);

            Label add_icon = new ImgIcon("src/main/resources/img/add.png").getIcon();
            add_icon.setPadding(new Insets(0.25, 8, 0.25, 0));

            add_btn = new MainBtn("Add");
            add_btn.setAlignment(Pos.CENTER);
            add_btn.setGraphic(add_icon);
            add_btn.setBgColor(Elements.SUCCESS_COLOR.getName());
            add_btn.setTextColor("#fff");
            add_btn.setRippleColor(Color.web(Elements.SUCCESS_ALT_COLOR.getName()));
            add_btn.setOnAction(event -> new TodoOption().show());

            BorderPane layout = new BorderPane();
            BorderPane.setMargin(add_btn, new Insets(0, 8, 0, 0));
            BorderPane.setMargin(label, new Insets(0, 0, 0, 8));

            layout.setPadding(new Insets(8, 2, 4, 2));
            layout.setLeft(label);
            layout.setRight(add_btn);

            box.getChildren().addAll(layout, separator);
            flow.getChildren().add(box);
        }

    }

    private class Note extends Arrangement {
        public Note() {
            super();

            label.setText("Note");
        }

    }

    private class Todos extends Arrangement {
        public Todos() {
            super();

            label.setText("Todos");
        }
    }

    private class Done extends Arrangement {
        public Done() {
            super();

            label.setText("Done");
        }
    }

}
