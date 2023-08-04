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
import javafx.project.modules.submodules.TodoOption;
import javafx.project.modules.submodules.TodoUpdate;

public class TodoModule extends VBox {
    private final AdminDatabase admin;

    private MainBtn addBtn;
    private ScrollPanel scrollPanel;
    private Diary diary;

    public TodoModule() {
        super(16);
        super.setPadding(new Insets(2, 4, 2, 4));
        VBox.setMargin(this, new Insets(8));

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
        private MainBtn updateBtn, deleteBtn, doneBtn;

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

        private void justDelete() {
        }

        private void initialize() {
            this.setPadding(new Insets(16));
            this.setMinSize(350, 350);
            this.fetch();
        }

        private void fetch() {
            try (ResultSet rs = admin.getTodos()) {
                while (rs.next()) {
                    int id = rs.getInt("id");

                    Label title = new Label(rs.getString("title"));
                    Label description = new Label(rs.getString("description"));

                    Label date = new Label(rs.getString("created_date"));
                    date.setStyle("-fx-font-weight: bold");

                    Label time = new Label(rs.getString("created_time"));
                    time.setStyle("-fx-font-weight: bold");

                    updateBtn = new MainBtn("Edit");
                    updateBtn.setBgColor("#17a2b8");
                    updateBtn.setTextColor("#FFF");
                    updateBtn.setRippleColor(Color.web("#AFD3E2"));
                    updateBtn.setOnAction(event -> new TodoUpdate(this, id).show());

                    deleteBtn = new MainBtn("Delete");
                    deleteBtn.setBgColor(Elements.DANGER_COLOR.getName());
                    deleteBtn.setRippleColor(Color.web(Elements.DANGER_ALT_COLOR.getName()));
                    deleteBtn.setTextColor("White");

                    doneBtn = new MainBtn("Done");
                    doneBtn.setBgColor(Elements.SUCCESS_COLOR.getName());
                    doneBtn.setTextColor("#fff");
                    doneBtn.setRippleColor(Color.web(Elements.SUCCESS_ALT_COLOR.getName()));

                    BorderPane main_layout = new BorderPane();

                    Card card = new Card(main_layout);
                    VBox.setVgrow(card, Priority.ALWAYS);
                    HBox.setHgrow(card, Priority.ALWAYS);
                    card.setSpacing(8);
                    card.setPadding(new Insets(8, 16, 8, 16));
                    // card.setMaxSize(512, 512);
                    // card.setMinSize(256, 256);
                    card.autosize();

                    Separator separator = new Separator(Orientation.HORIZONTAL);
                    separator.setHalignment(HPos.CENTER);
                    separator.setValignment(VPos.CENTER);
                    separator.setPadding(new Insets(16, 2, 4, 2));

                    VBox vbox = new VBox(8);
                    vbox.getChildren().addAll(separator, title, description);

                    BorderPane borderPane = new BorderPane();
                    borderPane.setLeft(date);
                    borderPane.setRight(time);

                    HBox hbox = new HBox(16);
                    hbox.setAlignment(Pos.BASELINE_CENTER);
                    hbox.setPadding(new Insets(16, 2, 8, 2));
                    hbox.getChildren().addAll(deleteBtn, doneBtn);

                    // main_layout.getChildren().addAll(borderPane, separator, vbox, hbox);
                    main_layout.setTop(borderPane);
                    main_layout.setCenter(vbox);
                    main_layout.setBottom(hbox);

                    card_list.add(card);
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            } finally {
                System.out.println("TodoModule.Diary.fetch() : Good");
                this.getChildren().addAll(card_list);
            }
        }
    }

}
