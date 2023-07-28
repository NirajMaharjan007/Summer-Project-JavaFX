package javafx.project.modules;

import javafx.project.components.*;
import javafx.project.database.*;
import javafx.project.enuma.Elements;
import javafx.project.enuma.MainStyle;
import javafx.project.panels.Dashboard;

import javafx.geometry.*;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class MainModule extends VBox {
    private int adminId = AdminDatabase.getInstance().getId();

    private static MainModule module;

    public static MainModule getModule() {
        if (module == null)
            module = new MainModule();

        return module;
    }

    private MainModule() {
        super(16);
        super.setPadding(new Insets(2, 4, 2, 4));
        VBox.setMargin(this, new Insets(8));

        this.init();
    }

    private void init() {
        this.getStylesheets().add(MainStyle.ALT_STYLESHEET.getLocation());

        Label header1 = new Label("Dashboard");
        header1.getStyleClass().add("header1");

        Label refresh_icon = new ImgIcon("src/main/resources/img/refresh.png")
                .getIcon();
        refresh_icon.setPadding(new Insets(1, 8, 1, 4));

        MainBtn refresh = new MainBtn("Refresh");

        VBox box = new VBox(16, new Pane(), new GraphPane());
        VBox.setVgrow(box, Priority.ALWAYS);
        VBox.setMargin(box, new Insets(8, 4, 6, 4));

        box.setAlignment(Pos.TOP_CENTER);

        refresh.setAlignment(Pos.BASELINE_CENTER);
        refresh.setGraphic(refresh_icon);
        refresh.setBgColor("#36cee6");
        refresh.setTextColor("#fff");
        refresh.setRippleColor(Color.web("#84DED2"));
        refresh.setOnAction(event -> {
            System.out.println("Refreshed");
            box.getChildren().clear();
            box.getChildren().addAll(new Pane(), new GraphPane());
        });

        this.setMinHeight(512);
        this.getChildren().addAll(header1, refresh, box);
    }

    private class GraphPane extends VBox {

        private EmpDatabase empData = new EmpDatabase(adminId);

        public GraphPane() {
            super();
            super.setPrefHeight(Dashboard.getStage().getHeight());
            VBox.setVgrow(this, Priority.ALWAYS);
            VBox.setMargin(this, new Insets(32, 8, 6, 8));
            if (empData.getPresent() != null && empData.getAbsent() != null) {
                this.init();
            } else {
                this.isNull();
            }
        }

        private void isNull() {
            StackPane box = new StackPane();
            StackPane.setMargin(box, new Insets(12, 6, 4, 6));
            StackPane.setAlignment(box, Pos.CENTER);

            Label label = new Label("No Attendance Data");
            label.setStyle(Elements.HEADER2.getName());

            box.getChildren().add(label);

            box.autosize();
            this.getChildren().add(box);
        }

        private void init() {
            NumberAxis yAxis = new NumberAxis();
            CategoryAxis xAxis = new CategoryAxis();

            // Set the labels for the axes
            xAxis.setLabel("Date");
            yAxis.setLabel("Attendance");

            XYChart.Series<String, Number> absent = empData.getAbsent();
            absent.setName("Absent");

            XYChart.Series<String, Number> present = empData.getPresent();
            present.setName("Present");

            BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
            barChart.setTitle("Attendance Chart");
            barChart.getData().add(absent);
            barChart.getData().add(present);
            barChart.setBarGap(4);
            barChart.setCategoryGap(32);
            barChart.setPrefWidth(10);
            barChart.setMinWidth(8);

            StackPane box = new StackPane(barChart);

            StackPane.setMargin(box, new Insets(8, 4, 6, 4));
            StackPane.setAlignment(box, Pos.TOP_CENTER);
            box.autosize();

            this.getChildren().add(box);
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

            Label label5 = new ImgIcon("src/main/resources/img/calendar.png")
                    .getIcon();
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
