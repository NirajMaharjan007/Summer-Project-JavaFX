package javafx.project.modules;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.project.database.*;
import javafx.project.components.*;
import javafx.project.enuma.MainStyle;

public class MainModule extends VBox {
    private int adminId = AdminDatabase.getInstance().getId();

    public MainModule() {
        super();
        super.setSpacing(16);
        super.setPadding(new Insets(2, 4, 2, 4));
        VBox.setMargin(this, new Insets(8));

        this.init();
    }

    private void init() {
        this.getStylesheets().add(MainStyle.ALT_STYLESHEET.getLocation());

        Label header1 = new Label("Dashboard");
        Label header2 = new Label("Summary");
        Label refresh_icon = new ImgIcon("src/main/resources/img/refresh.png").getIcon();
        refresh_icon.setPadding(new Insets(1, 8, 1, 4));

        MainBtn refresh = new MainBtn("Refresh");

        header1.getStyleClass().add("header1");
        header2.getStyleClass().add("header2");

        VBox box = new VBox(16, new Pane());
        VBox.setVgrow(box, Priority.ALWAYS);
        VBox.setMargin(box, new Insets(16, 4, 8, 4));

        box.setAlignment(Pos.CENTER);

        refresh.setAlignment(Pos.BASELINE_CENTER);
        refresh.setGraphic(refresh_icon);
        refresh.setBgColor("#36cee6");
        refresh.setTextColor("#fff");
        refresh.setRippleColor(Color.web("#84DED2"));
        refresh.setOnAction(event -> {
            System.out.println("Refreshed");
            box.getChildren().clear();
            box.getChildren().add(new Pane());
        });

        this.getChildren().addAll(header1, header2, refresh, box);
    }

    private class ChartPane extends VBox {
        public ChartPane() {
            super();
        }
    }

    private class Pane extends FlowPane {
        private BorderPane[] pane = new BorderPane[2];

        private Card[] card = new Card[2];

        final Insets PADDING = new Insets(4, 8, 6, 8);

        public Pane() {
            super();
            super.setPadding(PADDING);
            super.setOrientation(Orientation.HORIZONTAL);
            super.getStylesheets().add(MainStyle.ALT_STYLESHEET.getLocation());

            for (int i = 0; i < 2; i++) {
                pane[i] = new BorderPane();
                pane[i].setPadding(PADDING);

                card[i] = new Card();
                card[i].setPadding(PADDING);
                card[i].setBgColor("#C7E0EA");
            }

            this.init();
            super.autosize();
        }

        private void init() {
            this.setHgap(32);
            this.setAlignment(Pos.BASELINE_CENTER);

            int count = new EmpDatabase(adminId).count();

            GridPane.setFillWidth(this, true);

            ImgIcon icon = new ImgIcon("src/main/resources/img/staffs.png");
            VBox box = new VBox(16);
            box.setPadding(new Insets(4, 10, 2, 32));
            box.setAlignment(Pos.CENTER);

            Label label = new Label("Total employees");
            label.getStyleClass().add("header3");

            Label label2 = new Label();

            if (count <= 0) {
                label2.setText("No Employees Found");
                label2.getStyleClass().add("header2");
                box.getChildren().add(label2);
            } else {
                label2.setText("" + count);
                label2.getStyleClass().add("header1");
                box.getChildren().add(label2);
                box.getChildren().add(label);
            }

            // card[0].setPrefWidth(1024);

            pane[0].setLeft(icon.getIcon());
            pane[0].setCenter(box);

            VBox activeBox = new VBox(16);
            activeBox.setAlignment(Pos.CENTER);
            activeBox.setPadding(new Insets(8, 12, 8, 32));

            Label label3 = new Label("30");
            label3.getStyleClass().add("header1");

            Label label4 = new Label("Active Days");
            label4.getStyleClass().add("header3");

            activeBox.getChildren().add(label3);
            activeBox.getChildren().add(label4);

            Label label5 = new ImgIcon("src/main/resources/img/calendar.png").getIcon();
            label5.setPadding(new Insets(12, 4, 2, 4));
            pane[1].setLeft(label5);
            pane[1].setCenter(activeBox);

            for (int i = 0; i < 2; i++) {
                card[i].getChildren().add(pane[i]);
                this.getChildren().add(card[i]);
            }

        }

    }
}
