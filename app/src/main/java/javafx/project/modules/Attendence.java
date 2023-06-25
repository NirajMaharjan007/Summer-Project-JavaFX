package javafx.project.modules;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.project.components.*;
import javafx.project.database.*;
import javafx.project.enuma.*;

public class Attendence extends VBox {
    int adminId = AdminDatabase.getInstance().getId();
    MainBtn refresh;

    public Attendence() {
        super(16);

        this.init();
    }

    private void init() {
        HBox btn_box = new HBox(16);

        Label header = new Label("Attendence of Employees");
        header.setStyle(Elements.HEADER1.getName() + "-fx-text-fill:#484b6a");

        Label refresh_icon = new ImgIcon("src/main/resources/img/refresh.png").getIcon();
        refresh_icon.setPadding(new Insets(1, 8, 1, 4));

        refresh = new MainBtn("Refresh");
        refresh.setAlignment(Pos.BASELINE_CENTER);
        refresh.setGraphic(refresh_icon);
        refresh.setBgColor("#36cee6");
        refresh.setTextColor("#fff");
        refresh.setRippleColor(Color.web("#84DED2"));
        refresh.setOnAction(event -> {
            System.out.println("Attendence.Panel.init()\tRefreshed");
        });

        btn_box.getChildren().addAll(refresh);

        FlowPane inner_container = new FlowPane(Orientation.VERTICAL);
        inner_container.setAlignment(Pos.TOP_CENTER);

        inner_container.getChildren().addAll(new Panel());

        this.getChildren().addAll(header, btn_box, inner_container);
    }

    private class Panel extends VBox {
        public Panel() {
            super(16);
            this.init();
        }

        private void init() {

        }
    }
}
