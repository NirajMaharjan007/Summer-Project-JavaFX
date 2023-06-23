package javafx.project.modules.submodules;

import java.io.*;
import java.sql.ResultSet;

import javafx.scene.paint.Color;
import javafx.geometry.*;

import javafx.project.components.*;
import javafx.project.database.*;
import javafx.project.enuma.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.project.modules.EmployeeModule;

public class ViewDetail extends BorderPane {
    private MainBtn exit = EmployeeModule.exit;
    private final Insets PADDING = new Insets(4, 16, 8, 16);
    private int id;
    private EmpDatabase empDatabase = new EmpDatabase(AdminDatabase.getInstance().getId());

    public ViewDetail(int id) {
        super();
        super.setManaged(true);

        this.id = id;
        this.init();
    }

    private void init() {
        this.atTop();
        this.atCenter();
    }

    private void atTop() {
        Label header = new Label("Detail of Employee id: " + this.id);
        header.setStyle(Elements.HEADER1.getName() + "-fx-text-fill:#484b6a");

        VBox top = new VBox(8, new Card(header));
        top.setPadding(PADDING);

        this.setTop(top);
    }

    private void atCenter() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(8, 12, 4, 16));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(32);
        gridPane.setVgap(32);

        VBox image_box = new VBox(16);
        image_box.setPadding(new Insets(4, 16, 6, 4));
        image_box.setAlignment(Pos.CENTER);

        FlowPane pane = new FlowPane(Orientation.HORIZONTAL);
        pane.setPadding(PADDING);
        pane.setAlignment(Pos.CENTER);

        Card card = new Card(pane);
        card.setPadding(new Insets(16, 8, 16, 8));
        card.setMinHeight(265);

        VBox center = new VBox(6, card);
        center.setAlignment(Pos.CENTER);

        MainBtn update_btn = new MainBtn("Update details");
        update_btn.setBgColor("#17a2b8");
        update_btn.setTextColor("#FFF");
        update_btn.setRippleColor(Color.web("#AFD3E2"));

        exit.setBgColor(Elements.DANGER_COLOR.getName());
        exit.setTextColor("#FFF");
        exit.setRippleColor(Color.web(Elements.DANGER_ALT_COLOR.getName()));
        exit.setOnAction(event -> {
            EmployeeModule.stage.close();
        });

        HBox btn_box = new HBox(16, update_btn, exit);
        btn_box.setPadding(PADDING);
        btn_box.setAlignment(Pos.BASELINE_CENTER);
        int row = 0, col = 0;
        try (ResultSet data = empDatabase.getData(this.id)) {
            while (data.next()) {
                int employeeId = data.getInt(1);

                InputStream stream = new FileInputStream(data.getString(9));
                Image image = new Image(stream);
                ImageView imageView = new ImageView();
                imageView.setImage(image);
                imageView.setFitHeight(128);
                imageView.setPreserveRatio(true);

                update_btn.setOnAction(event -> {
                    new EmployeeUpdate(employeeId).show();
                });

                Label label[] = new Label[7];
                HBox hbox[] = new HBox[7];
                for (int i = 0; i < 7; i++) {
                    label[i] = new Label();
                    label[i].setStyle("-fx-font-weight: bold;");
                    hbox[i] = new HBox();
                }
                label[0].setText("Name: ");
                label[1].setText("Department: ");
                label[2].setText("Address: ");
                label[3].setText("Salary: ");
                label[4].setText("Gender: ");
                label[5].setText("Email: ");
                label[6].setText("Contact no.:");

                int j = 2;
                for (int i = 0; i < 7; i++) {
                    hbox[i].getChildren().add(label[i]);
                    if (j <= 8) {
                        hbox[i].getChildren().add(new Label(data.getString(j)));
                        j++;
                    }
                }

                for (int i = 0; i < 7; i++) {
                    if (col == 2) {
                        row++;
                        col = 0;
                    }

                    gridPane.add(hbox[i], col, row);
                    col++;
                }

                image_box.getChildren().add(imageView);
                pane.getChildren().addAll(image_box, gridPane);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            this.setCenter(center);
            this.setBottom(btn_box);
        }
    }

}
