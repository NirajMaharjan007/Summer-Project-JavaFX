package javafx.project.modules.submodules;

import java.sql.ResultSet;
import java.io.*;

import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.geometry.*;

import javafx.project.components.*;
import javafx.project.database.*;
import javafx.project.enuma.*;

public class ViewDetail extends BorderPane {
    private final Insets PADDING = new Insets(2, 16, 4, 16);
    private int id;
    private EmpDatabase empDatabase = new EmpDatabase(AdminDatabase.getInstance().getId());
    private VBox box = new VBox(16);

    public ViewDetail(int id) {
        super();
        super.setManaged(true);

        this.id = id;
        this.init();
    }

    private void init() {

        Label header = new Label("Detail of Employee id: " + this.id);
        header.setStyle(Elements.HEADER1.getName() + "-fx-text-fill:#484b6a");

        VBox top = new VBox(8, new Card(header));
        top.setPadding(PADDING);

        this.setTop(top);
        this.atCenter();
    }

    private void atCenter() {
        try (ResultSet data = empDatabase.getData(this.id)) {
            box.setPadding(new Insets(8, 12, 8, 16));
            box.setAlignment(Pos.BASELINE_LEFT);

            VBox image_box = new VBox(16);
            image_box.setPadding(new Insets(4, 16, 8, 8));
            image_box.setAlignment(Pos.CENTER);

            BorderPane pane = new BorderPane();
            pane.setPadding(PADDING);
            pane.setCenter(box);

            Card card = new Card(pane);
            card.setPadding(PADDING);
            card.setMinHeight(180);

            VBox center = new VBox(16, card);
            center.setPadding(PADDING);

            InputStream stream = new FileInputStream("src/main/resources/img/uploads/default-pic.png");
            Image image = new Image(stream);
            // Creating the image view
            ImageView imageView = new ImageView();
            // Setting image to the image view
            imageView.setImage(image);
            // Setting the image view parameters
            imageView.setFitHeight(128);
            imageView.setPreserveRatio(true);

            while (data.next()) {
                /*
                 * Label name = new Label("Name: " + data.getString(2));
                 * Label department = new Label("Department: " + data.getString(3));
                 * Label address = new Label("Address: " + data.getString(4));
                 * Label salary = new Label("Salary: " + data.getString(5));
                 * Label gender = new Label("Gender: " + data.getString(6));
                 * box.getChildren().addAll(name, department, address, salary, gender);
                 */
                Label label[] = new Label[5];
                HBox hbox[] = new HBox[5];
                for (int i = 0; i < 5; i++) {
                    label[i] = new Label();
                    label[i].setStyle("-fx-font-weight: bold;");
                    hbox[i] = new HBox();
                }
                label[0].setText("Name: ");
                label[1].setText("Department: ");
                label[2].setText("Address: ");
                label[3].setText("Salary: ");
                label[4].setText("Gender: ");

                int j = 2;
                for (int i = 0; i < 5; i++) {
                    hbox[i].getChildren().add(label[i]);
                    if (j <= 6) {
                        hbox[i].getChildren().add(new Label(data.getString(j)));
                        j++;
                    }
                }

                box.getChildren().addAll(hbox);
                image_box.getChildren().add(imageView);
                pane.setLeft(image_box);
            }
            this.setCenter(center);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
