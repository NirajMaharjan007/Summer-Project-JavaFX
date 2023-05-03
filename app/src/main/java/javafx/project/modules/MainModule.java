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
        Label header2 = new Label("Details");

        header1.getStyleClass().add("header1");
        header2.getStyleClass().add("header2");

        this.getChildren().addAll(header1, header2, new Pane());
    }

    private class Pane extends BorderPane {
        private BorderPane[] pane = new BorderPane[3];

        private Card[] card = new Card[3];

        public Pane() {
            super();
            super.setPadding(new Insets(10, 12, 10, 12));

            for (int i = 0; i < 3; i++) {
                pane[i] = new BorderPane();
                pane[i].setPadding(new Insets(10, 8, 10, 8));

                card[i] = new Card();
                card[i].setBgColor("#AFD3E2");
            }

            card[0].setPadding(new Insets(10, 32, 10, 8));
            card[1].setPadding(new Insets(10, 8, 10, 8));
            card[2].setPadding(new Insets(10, 8, 10, 32));

            this.init();
        }

        private void init() {
            int count = EmpDatabase.getInstance().count();

            ImgIcon icon = new ImgIcon("src/main/resources/img/staffs.png");
            VBox box = new VBox(16);
            box.setPadding(new Insets(8, 8, 4, 32));

            box.setAlignment(Pos.CENTER);

            box.getChildren().add(new Label("" + count));
            box.getChildren().add(new Label("Total employees"));

            pane[0].setLeft(icon.getIcon());
            pane[0].setCenter(box);

            for (int i = 0; i < 3; i++) {
                card[i].getChildren().add(pane[i]);
            }

            this.setLeft(card[0]);
            this.setCenter(card[1]);
            this.setRight(card[2]);
        }

    }
}
