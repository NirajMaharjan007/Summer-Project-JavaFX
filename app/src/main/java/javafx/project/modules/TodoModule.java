package javafx.project.modules;

import java.sql.ResultSet;
import java.util.*;

import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.*;

import javafx.project.components.*;
import javafx.project.database.AdminDatabase;
import javafx.project.enuma.Elements;
import javafx.project.log.Log;
import javafx.project.modules.submodules.TodoOption;

public class TodoModule extends VBox {
    private final Log log;
    private final AdminDatabase admin;

    private MainBtn addBtn;
    private ScrollPanel scrollPanel;
    private Diary diary;

    public TodoModule() {
        super(16);
        super.setPadding(new Insets(2, 4, 2, 4));
        VBox.setMargin(this, new Insets(8));

        log = Log.getInstance();
        admin = AdminDatabase.getInstance();

        this.init();
    }

    private void init() {
        Label header = new Label("To-Do List");
        header.setStyle(Elements.HEADER1.getName());

        Label add_icon = new ImgIcon("src/main/resources/img/add.png").getIcon();
        add_icon.setPadding(new Insets(1, 8, 1, 2));

        addBtn = new MainBtn("Add Todos List");
        addBtn.setGraphic(add_icon);
        addBtn.setAlignment(Pos.CENTER);
        addBtn.setBgColor(Elements.SUCCESS_COLOR.getName());
        addBtn.setTextColor("#fff");
        addBtn.setRippleColor(Color.web(Elements.SUCCESS_ALT_COLOR.getName()));

        diary = new Diary();
        scrollPanel = new ScrollPanel(diary);
        VBox.setVgrow(scrollPanel, Priority.ALWAYS);
        HBox.setHgrow(add_icon, Priority.ALWAYS);
        scrollPanel.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPanel.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPanel.setMinViewportWidth(420);
        scrollPanel.setMinViewportHeight(450);
        // scrollPanel.setFitToHeight(true);
        scrollPanel.setFitToWidth(true);

        this.setAlignment(Pos.TOP_LEFT);
        this.getChildren().addAll(header, addBtn, scrollPanel);
    }

    public class Diary extends FlowPane {
        private List<Card> card_list;

        public Diary() {
            super();
            super.setHgap(64);
            super.setVgap(32);

            card_list = new ArrayList<>();
            addBtn.setOnAction(event -> new TodoOption(this).show());

            this.initialize();
        }

        public void refresh() {
            System.out.println("Refreshing");
            diary.getChildren().clear();
            scrollPanel.setContent(new Diary());
        }

        private void initialize() {
            this.setPadding(new Insets(16));
            this.setMinSize(350, 350);
            this.fetch();
        }

        private void fetch() {
            try (ResultSet rs = admin.getTodos()) {
                while (rs.next()) {
                    BorderPane panel = new BorderPane();
                    Card card = new Card(panel);
                    card.setSpacing(16);
                    card.setPadding(new Insets(8, 6, 4, 6));
                    card.setMaxSize(256, 256);
                    card.setMinSize(128, 128);

                    VBox vbox = new VBox(16);
                    VBox.setVgrow(vbox, Priority.ALWAYS);
                    vbox.setPadding(new Insets(8));

                    HBox hbox = new HBox(16);
                    HBox.setHgrow(hbox, Priority.ALWAYS);
                    hbox.setPadding(new Insets(8));

                    Label title = new Label(rs.getString("title"));
                    Label description = new Label(rs.getString("description"));
                    Label date = new Label(rs.getString("created_date"));
                    Label time = new Label(rs.getString("created_time"));

                    hbox.getChildren().addAll(date, time);
                    vbox.getChildren().addAll(title, description);

                    panel.setTop(hbox);
                    panel.setCenter(vbox);

                    card_list.add(card);
                }
                this.getChildren().addAll(card_list);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

}
