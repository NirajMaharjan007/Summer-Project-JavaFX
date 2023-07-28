package javafx.project.panels;

import io.github.palexdev.materialfx.css.themes.*;

import javafx.project.components.*;
import javafx.project.enuma.*;
import javafx.project.modules.*;
import javafx.project.modules.submodules.AdminOption;

import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.*;

public class Dashboard extends BorderPane {

  public static VBox container = MainDash.container;

  private static Stage stage, newStage;

  public Dashboard(Stage stage) {
    super();
    Dashboard.stage = stage;

    Dashboard.newStage = new Stage();

    super.autosize();
    // super.setPadding(new Insets(6, 12, 6, 12));

    Scene scene = new Scene(this, 1200, 620, true, SceneAntialiasing.BALANCED);
    MFXThemeManager.addOn(scene, Themes.DEFAULT, Themes.LEGACY);

    init();

    newStage.centerOnScreen();
    newStage.setResizable(true);
    newStage.setTitle("Dashboard");
    newStage.setScene(scene);
    newStage.show();
  }

  public static Stage getStage() {
    return Dashboard.newStage;
  }

  private void init() {
    this.setTop(new TopBar());
    this.setCenter(new MainDash());
    this.setLeft(new SideBar());
  }

  private class TopBar extends BorderPane {

    public TopBar() {
      super(); // "-fx-border-insets: 4;" +
      super.setPadding(new Insets(16));
      super.getStylesheets().add(MainStyle.STYLESHEET.getLocation());
      super.getStyleClass().add("topbar");

      this.init();
    }

    private void init() {
      HBox box = new HBox();
      box.setPadding(new Insets(8));
      box.setSpacing(16);

      MainBtn logout = new MainBtn("Logout");
      Label img_logout = new ImgIcon("src/main/resources/img/logout.png")
          .getIcon();
      img_logout.setPadding(new Insets(0, 6, 0, 0));

      box.setAlignment(Pos.CENTER);

      logout.setGraphic(img_logout);
      logout.setBgColor(Elements.DANGER_COLOR.getName());
      logout.setTextColor("White");
      logout.setRippleColor(Color.web(Elements.DANGER_ALT_COLOR.getName()));

      logout.setOnAction(event -> {
        stage.setTitle("Login Panel");
        stage.show();
        newStage.close();
      });

      Button btn = new ImgIcon("src/main/resources/img/settings.png").getBtnIcon();
      btn.setOnAction(event -> {
        new AdminOption().show();
      });

      box.getChildren().addAll(btn, logout);

      HBox logoSide = new HBox();
      logoSide.setPadding(new Insets(8));
      logoSide.setSpacing(16);
      // logoSide.getChildren().add(new Label("Logo"));
      logoSide.getChildren().add(new ImgIcon("src/main/resources/img/logolaxprinter.png").getIcon());

      this.setRight(box);
      this.setLeft(logoSide);
    }
  }

  private class MainDash extends VBox {

    /*
     * public MainModule module = new MainModule();
     * public EmployeeModule employeeModule = new EmployeeModule();
     */
    public static VBox container = new VBox();

    public MainDash() {
      super();
      super.setSpacing(12);
      super.setPadding(new Insets(8, 16, 4, 32));
      VBox.setMargin(this, new Insets(8));

      this.init();
    }

    private void init() {
      Card card = new Card();

      ScrollPanel panel = new ScrollPanel();
      VBox.setVgrow(panel, Priority.ALWAYS);
      VBox.setMargin(panel, new Insets(8, 4, 8, 4));

      panel.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
      panel.setMaxHeight(card.getMaxHeight());

      container.getStylesheets().add(MainStyle.STYLESHEET.getLocation());
      container.getStyleClass().add("container");
      container.setSpacing(12);
      container.setMaxWidth(Double.MAX_VALUE);
      container.setMaxHeight(Double.MAX_VALUE);
      container.setPadding(new Insets(4, 8, 2, 8));
      VBox.setMargin(container, new Insets(8));

      container.getChildren().clear();
      container.getChildren().add(MainModule.getModule());

      card.getChildren().add(container);
      card.setAlignment(Pos.TOP_CENTER);
      card.setPrefWidth(newStage.getMaxWidth());
      card.setPrefHeight(newStage.getMaxHeight());

      this.getChildren().add(card);
    }
  }

  private class SideBar extends VBox {

    public SideBar() {
      super();
      super.setSpacing(12);
      super.setPrefHeight(newStage.getMaxHeight());
      super.setPrefWidth(200);
      // super.autosize();

      this.init();
    }

    private void init() {
      this.setPadding(new Insets(18, 8, 12, 8));
      this.getStylesheets().add(MainStyle.STYLESHEET.getLocation());
      this.getStyleClass().addAll("sidebar");

      Label headerLabel = new Label("Side Bar");
      headerLabel.setStyle(Elements.HEADER1.getName());
      headerLabel.setGraphicTextGap(4);
      this.getChildren().add(headerLabel);

      Label menuLabel = new Label("Components");
      menuLabel.setStyle(Elements.HEADER2.getName());
      menuLabel.setPadding(new Insets(16, 8, 4, 8));
      this.getChildren().add(menuLabel);

      ScrollPanel scrollPane = new ScrollPanel();
      scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

      VBox.setVgrow(scrollPane, Priority.ALWAYS);
      Insets scrollPaneMargin = new Insets(0, 5, 10, 5);
      VBox.setMargin(scrollPane, scrollPaneMargin);

      VBox navBar = new VBox();
      navBar.getStylesheets().add(MainStyle.STYLESHEET.getLocation());
      navBar.getStyleClass().add("navbar");
      navBar.setSpacing(10);
      navBar.setMaxWidth(Double.MAX_VALUE);
      navBar.setMaxHeight(Double.MAX_VALUE);

      MainBtn[] btn = new MainBtn[5];

      for (int i = 0; i < 5; i++) {
        btn[i] = new MainBtn("");
        btn[i].setSize((int) navBar.getMaxWidth(), 120);
        btn[i].setBgColor("#C7E0EA");
        btn[i].setRippleColor(Color.web("#ddecf2"));
        btn[i].setTextColor(
            "#484b6a; -fx-font-weight: bold; -fx-font-size:14px");
        btn[i].buttonTypeProperty()
            .set(io.github.palexdev.materialfx.enums.ButtonType.RAISED);
        btn[i].depthLevelProperty()
            .set(io.github.palexdev.materialfx.effects.DepthLevel.LEVEL1);
      }

      Label emp_icon = new ImgIcon("src/main/resources/img/user.png").getIcon();
      emp_icon.setPadding(new Insets(4, 10, 4, 2));

      Label dash_icon = new ImgIcon("src/main/resources/img/monitor.png").getIcon();
      dash_icon.setPadding(new Insets(4, 8, 4, 2));

      Label attend_icon = new ImgIcon("src/main/resources/img/attendence.png").getIcon();
      attend_icon.setPadding(new Insets(4, 8, 4, 2));

      Label todo_icon = new ImgIcon("src/main/resources/img/to-do-list.png").getIcon();
      todo_icon.setPadding(new Insets(4, 8, 4, 2));

      btn[0].setGraphic(dash_icon);
      btn[0].setText("Dashboard");

      btn[1].setGraphic(emp_icon);
      btn[1].setText("Employee");

      btn[2].setGraphic(attend_icon);
      btn[2].setText("Attenance");

      btn[3].setGraphic(todo_icon);
      btn[3].setText("To-Do List");

      for (int index = 0; index < 4; index++)
        navBar.getChildren().add(btn[index]);

      new SwitchNode(MainDash.container, btn[0]).switchNode(MainModule.getModule());
      new SwitchNode(MainDash.container, btn[1]).switchNode(EmployeeModule.getModule());
      new SwitchNode(MainDash.container, btn[2]).switchNode(Attendence.getModule());
      new SwitchNode(MainDash.container, btn[3]).switchNode(TodoModule.getModule());

      scrollPane.setContent(navBar);

      this.getChildren().add(scrollPane);
    }
  }
}
