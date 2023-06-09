package javafx.project.modules;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

import java.sql.ResultSet;
import java.util.*;

import javafx.project.components.*;
import javafx.project.database.*;
import javafx.project.enuma.*;

public class Attendence extends VBox {
    int adminId = AdminDatabase.getInstance().getId();

    public Attendence() {
        super(16);

        this.init();
    }

    private void init() {
        Label header = new Label("Attendence of Employees");
        header.setStyle(Elements.HEADER1.getName() + "-fx-text-fill:#484b6a");

        Card card = new Card();
        card.setSpacing(12);

        VBox box = new VBox(16, card);
        MainComboBox comboBox = new MainComboBox();
        comboBox.setFloatingText("Attendence");
        comboBox.selectFirst();
        comboBox.getItems().addAll("Absent", "Present");
        box.getChildren().addAll(comboBox);
        this.getChildren().addAll(header, box);

        this.content();
    }

    private void content() {
        List<Card> card_list = new ArrayList<Card>();
        try (ResultSet data = new EmpDatabase(adminId).getData()) {
            Card card = new Card();
            while (data.next()) {

            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            VBox vbox = new VBox(8);

            this.getChildren().addAll(vbox);
        }

    }

}
