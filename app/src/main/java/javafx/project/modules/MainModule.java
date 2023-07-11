package javafx.project.modules;

import javafx.geometry.*;
import javafx.project.components.*;
import javafx.project.database.*;
import javafx.project.enuma.MainStyle;
import javafx.project.panels.Dashboard;
import javafx.scene.chart.*;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

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

    public GraphPane() {
      super();
      super.setPrefHeight(Dashboard.getStage().getHeight());
      VBox.setVgrow(this, Priority.ALWAYS);
      VBox.setMargin(this, new Insets(16, 2, 4, 2));

      this.init();
    }

    private void init() {
      NumberAxis xAxis = new NumberAxis();
      NumberAxis yAxis = new NumberAxis();

      xAxis.setLabel("Number of Month");

      LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
      lineChart.setTitle("Stock Monitoring, 2010");

      Series<Number, Number> series = new Series<>();
      series.setName("My portfolio");
      series.getData().add(new XYChart.Data<>(1, 10));
      series.getData().add(new XYChart.Data<>(2, 16));
      series.getData().add(new XYChart.Data<>(3, 16));

      lineChart.getData().add(series);
      lineChart.autosize();

      StackPane box = new StackPane(lineChart);
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
