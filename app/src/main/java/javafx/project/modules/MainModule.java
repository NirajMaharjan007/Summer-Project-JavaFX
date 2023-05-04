package javafx.project.modules;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.project.components.Card;
import javafx.project.components.ImgIcon;
import javafx.project.database.EmpDatabase;
import javafx.project.enuma.MainStyle;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class MainModule extends VBox {
    public MainModule() {
        super();
        super.setSpacing(16);
        super.setPadding(new Insets(2, 6, 4, 8));
        VBox.setMargin(this, new Insets(8));

        this.init();
    }

    private void init() {
        this.getStylesheets().add(MainStyle.ALT_STYLESHEET.getLocation());

        Label header1 = new Label("Dashboard");
        Label header2 = new Label("Summary");

        header1.getStyleClass().add("header1");
        header2.getStyleClass().add("header2");

        this.getChildren().addAll(header1, header2, new Pane());
    }

    private class Pane extends GridPane {
        private BorderPane[] pane = new BorderPane[3];

        private Card[] card = new Card[3];

        public Pane() {
            super();
            super.setPadding(new Insets(10, 12, 10, 12));
            super.getStylesheets().add(MainStyle.ALT_STYLESHEET.getLocation());

            for (int i = 0; i < 3; i++) {
                pane[i] = new BorderPane();
                pane[i].setPadding(new Insets(10, 8, 10, 8));

                card[i] = new Card();
                card[i].setPadding(new Insets(10, 8, 10, 8));
                card[i].setBgColor("#AFD3E2");
            }

            this.init();
        }

        private void init() {
            int count = EmpDatabase.getInstance().count();
            this.setHgap(32);

            ImgIcon icon = new ImgIcon("src/main/resources/img/staffs.png");
            VBox box = new VBox(16);
            box.setPadding(new Insets(8, 12, 8, 32));
            box.setAlignment(Pos.CENTER);

            Label label = new Label("Total employees");
            label.getStyleClass().add("header3");

            Label label2 = new Label("" + count);
            label2.getStyleClass().add("header1");

            box.getChildren().add(label2);
            box.getChildren().add(label);

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

            pane[1].setLeft(new ImgIcon("src/main/resources/img/calendar.png").getIcon());
            pane[1].setCenter(activeBox);

            for (int i = 0; i < 3; i++) {
                card[i].getChildren().add(pane[i]);
                this.add(card[i], i, 0);
            }

        }

    }
}
