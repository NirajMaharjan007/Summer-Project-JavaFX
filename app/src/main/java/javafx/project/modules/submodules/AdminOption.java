package javafx.project.modules.submodules;

import io.github.palexdev.materialfx.css.themes.*;
import java.io.*;
import java.sql.ResultSet;

import javafx.project.components.*;
import javafx.project.database.AdminDatabase;
import javafx.project.enuma.*;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;

public class AdminOption extends Pane {

    private Stage stage;
    private Scene scene;

    static VBox container;

    public AdminOption() {
        super();
        scene = new Scene(this, 800, 600);
        container = new VBox();

        MFXThemeManager.addOn(scene, Themes.DEFAULT, Themes.LEGACY);

        stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.setTitle("Admin's Setting");

        this.init();
    }

    public void show() {
        stage.showAndWait();
    }

    private void init() {
        this.setPadding(new Insets(8));
        this.getChildren().add(new MainPanel());
    }

    private class MainPanel extends BorderPane {

        public MainPanel() {
            super();

            this.atLeft();
            this.atCenter();
        }

        private void atCenter() {
            container.setPadding(new Insets(6, 8, 4, 18));
            container.getChildren().clear();
            container.getChildren().add(new AdminDetail());
            container.autosize();
            this.setCenter(container);
        }

        private void atLeft() {
            this.setLeft(new SideBar());
        }
    }

    private class AdminDetail extends Card {

        public AdminDetail() {
            super();
            super.setPrefWidth(500);
            this.setAlignment(Pos.TOP_CENTER);
            this.init();
        }

        private VBox getImageViewer() {
            VBox box = new VBox(8);

            try (ResultSet rs = AdminDatabase.getInstance().getDetail()) {
                while (rs.next()) {
                    InputStream stream = new FileInputStream(rs.getString("picture"));
                    Image image = new Image(stream);
                    ImageView imageView = new ImageView();
                    imageView.setImage(image);
                    imageView.setFitHeight(128);
                    imageView.setPreserveRatio(true);
                    box.getChildren().add(imageView);
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            } finally {
                box.setPadding(new Insets(16, 8, 4, 8));
                box.setAlignment(Pos.TOP_CENTER);
            }
            return box;
        }

        private void init() {
            HBox hbox[] = new HBox[3];
            Label label[] = new Label[3];
            Label detail_label[] = new Label[3];

            String detail[] = new String[3];
            String string[] = { "Real Name: ", "Email: ", "Phone Number: " };

            VBox box = new VBox(16);
            box.setAlignment(Pos.TOP_CENTER);
            box.setPadding(new Insets(4, 2, 8, 2));

            Label title = new Label("Admin's Details");
            title.setStyle(Elements.HEADER1.getName());
            box.getChildren().add(title);

            for (int i = 0; i < hbox.length; i++) {
                hbox[i] = new HBox();
                hbox[i].setAlignment(Pos.BASELINE_CENTER);

                label[i] = new Label();
                label[i].setStyle("-fx-text-fill:#484b6a;-fx-font-weight:bold;");
                label[i].setText(string[i]);

                detail_label[i] = new Label();
            }

            try (ResultSet resultSet = AdminDatabase.getInstance().getDetail()) {
                while (resultSet.next()) {
                    detail = new String[] { resultSet.getString("name"), resultSet.getString("email"),
                            resultSet.getString("phone") };
                }

                for (int i = 0; i < detail_label.length; i++) {
                    detail_label[i].setText(detail[i]);
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            } finally {
                box.getChildren().add(this.getImageViewer());
                for (int i = 0; i < hbox.length; i++) {
                    if (detail_label[i].getText() == null) {
                        detail = new String[] { "N/A", "N/A", "N/A" };
                        detail_label[i].setText(detail[i]);
                    }
                    hbox[i].getChildren().addAll(label[i], detail_label[i]);
                    box.getChildren().add(hbox[i]);
                }
            }
            this.getChildren().add(box);
        }
    }

    private class AdminUpdate extends Card {

        VBox field_box, admin_box, admin_detailbox, current;
        MainBtn next;

        MainTextField textField;
        MainPasswordField passwordField;

        public AdminUpdate() {
            super();
            super.setPrefWidth(500);
            this.setAlignment(Pos.TOP_CENTER);

            this.init();
        }

        private void init() {
            next = new MainBtn("Next");
            next.setAlignment(Pos.CENTER);
            next.setBgColor(Elements.INFO_COLOR.getName());
            next.setRippleColor(Color.web(Elements.INFO_ALT_COLOR.getName()));
            next.setTextColor("#fff");
            next.setOnAction(event -> {
                if (current == admin_box) {
                    field_box.getChildren().clear();
                    field_box.setPrefHeight(280);
                    current = admin_detailbox;
                } else {
                    field_box.getChildren().clear();
                    field_box.setPrefHeight(225);
                    current = admin_box;
                }

                field_box.getChildren().add(current);
            });

            field_box = new VBox(8);
            field_box.setAlignment(Pos.TOP_CENTER);

            HBox toggle_box = new HBox(8);
            toggle_box.setAlignment(Pos.CENTER_RIGHT);
            toggle_box.getChildren().add(next);

            this.initAdminChange();
            this.initAdminDetailChange();

            current = admin_box;
            field_box.getChildren().clear();
            field_box.setPrefHeight(225);
            field_box.getChildren().add(current);

            this.setPadding(new Insets(16, 8, 4, 8));
            this.getChildren().addAll(toggle_box, field_box);
        }

        private void initAdminChange() {
            admin_box = new VBox(16);
            admin_box.setAlignment(Pos.TOP_CENTER);

            HBox box = new HBox(10);
            box.setAlignment(Pos.BASELINE_CENTER);

            textField = new MainTextField("inline");
            textField.setFloatingText("Enter the admin name:");

            passwordField = new MainPasswordField("inline");
            passwordField.setFloatingText("Enter the admin password:");

            Label label = new Label("Change admin name and password");
            label.setStyle("-fx-font-weight:bold");
            label.setAlignment(Pos.CENTER);

            Label icon = new ImgIcon("src/main/resources/img/check-mark.png").getIcon();
            icon.setPadding(new Insets(1, 8, 1, 2));

            MainBtn save = new MainBtn("Save");
            save.setBgColor(Elements.SUCCESS_COLOR.getName());
            save.setRippleColor(Color.web(Elements.SUCCESS_ALT_COLOR.getName()));
            save.setTextColor("#fff");
            save.setGraphic(icon);
            save.setOnAction(event -> {
                Alert alert = new Alert(null);
                try {
                    String admin = textField.getText(), password = passwordField.getText();
                    boolean flag = admin != null && password != null && !admin.isBlank() && !password.isBlank();
                    if (flag) {
                        int i = AdminDatabase.getInstance().updateAdmin(admin, password);
                        if (i > -1) {
                            alert.setAlertType(Alert.AlertType.INFORMATION);
                            alert.setTitle("Success");
                            alert.setHeaderText("Success to Update");
                            alert.setContentText("Required to logout.");
                            System.out.println("Done");
                            stage.close();
                        } else {
                            alert.setAlertType(Alert.AlertType.ERROR);
                            alert.setTitle("Failed");
                            alert.setHeaderText("Failed to Update");
                            alert.setContentText("Try another time.");
                            System.out.println("Failed");
                        }
                    } else {
                        alert.setAlertType(Alert.AlertType.WARNING);
                        alert.setTitle("Failed");
                        alert.setHeaderText("Failed to Update");
                        alert.setContentText("Either admin field is blank or password field is blank");
                        System.out.println("Blank");
                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                } finally {
                    alert.show();
                }
            });

            box.getChildren().addAll(save);

            admin_box.getChildren().addAll(label, textField, passwordField, box);
        }

        private void initAdminDetailChange() {
            admin_detailbox = new VBox(16);
            admin_detailbox.setAlignment(Pos.TOP_CENTER);

            HBox box = new HBox(10);
            box.setAlignment(Pos.BASELINE_CENTER);

            MainTextField name = new MainTextField("inline");
            MainTextField email = new MainTextField("inline");
            MainTextField phone = new MainTextField("inline");

            name.setFloatingText("Enter your real name:");
            email.setFloatingText("Enter your email:");
            phone.setFloatingText("Enter your phone:");

            Label label = new Label("Change your detail");
            label.setStyle("-fx-font-weight:bold");
            label.setAlignment(Pos.CENTER);

            Label icon = new ImgIcon("src/main/resources/img/check-mark.png").getIcon();
            icon.setPadding(new Insets(1, 8, 1, 2));

            MainBtn save = new MainBtn("Save");
            save.setBgColor(Elements.SUCCESS_COLOR.getName());
            save.setRippleColor(Color.web(Elements.SUCCESS_ALT_COLOR.getName()));
            save.setTextColor("#fff");
            save.setGraphic(icon);
            save.setOnAction(event -> {
                Alert alert = new Alert(null);
                String name_text = name.getText(), email_text = email.getText(),
                        phone_text = phone.getText();
                try {
                    int i = -1;
                    if (!name_text.isBlank() && !email_text.isBlank() && !phone_text.isBlank()) {
                        if (AdminDatabase.getInstance().isNullAdminDetail() == false) {
                            i = AdminDatabase.getInstance().insertAdminDetail(name_text,
                                    email_text, phone_text);
                            if (i > -1) {
                                alert.setAlertType(Alert.AlertType.INFORMATION);
                                alert.setTitle("Success");
                                alert.setHeaderText("Success to Insert your detail");
                                alert.setContentText("Required to logout.");
                                System.out.println("Inserted");
                                stage.close();
                            } else {
                                alert.setAlertType(Alert.AlertType.ERROR);
                                alert.setTitle("Failed");
                                alert.setHeaderText("Failed to Insert");
                                alert.setContentText("Try another time");
                                System.out.println("Failed");
                            }
                        } else {
                            i = AdminDatabase.getInstance().updateAdminDetail(name_text,
                                    email_text, phone_text);
                            if (i > -1) {
                                alert.setAlertType(Alert.AlertType.INFORMATION);
                                alert.setTitle("Success");
                                alert.setHeaderText("Success to Updated your detail");
                                alert.setContentText("Required to logout.");
                                System.out.println("updated");
                                stage.close();
                            } else {
                                alert.setAlertType(Alert.AlertType.ERROR);
                                alert.setTitle("Failed");
                                alert.setHeaderText("Failed to Updated");
                                alert.setContentText("Try another time");
                                System.out.println("Failed");
                            }
                        }
                    } else {
                        alert.setAlertType(Alert.AlertType.WARNING);
                        alert.setTitle("Failed");
                        alert.setHeaderText("Failed to Do");
                        alert.setContentText("The Field is blank\nPlease Check it");
                        System.out.println("Blank");
                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                } finally {
                    alert.show();
                }
            });

            box.getChildren().addAll(save);

            admin_detailbox.getChildren().addAll(label, name, email, phone, box);
        }
    }

    private class Activity extends Card {
        public Activity() {
            super();
            this.init();
        }

        private void init() {
            VBox header_box = new VBox(8);

            Label label = new Label("Activity Log");
            label.setStyle(Elements.HEADER1.getName());

            MainBtn clear = new MainBtn("Clear");
            clear.setBgColor("#17a2b8");
            clear.setTextColor("#FFF");
            clear.setRippleColor(Color.web("#AFD3E2"));

            header_box.getChildren().addAll(label, clear);

            FlowPane pane = new FlowPane(Orientation.VERTICAL);
            pane.setPadding(new Insets(8));
            pane.getChildren().addAll(header_box);

            this.setSpacing(16);
            this.getChildren().add(pane);
            this.autosize();
        }
    }

    private class SideBar extends VBox {
        public SideBar() {
            super();
            super.setSpacing(12);
            super.setPrefWidth(250);
            super.setPrefHeight(600);
            // super.autosize();

            this.init();
        }

        private void init() {
            this.setPadding(new Insets(18, 8, 12, 8));
            this.getStylesheets().add(MainStyle.STYLESHEET.getLocation());
            this.getStyleClass().addAll("sidebar");

            Label menuLabel = new Label("Admin's Sidebar menu");
            menuLabel.setStyle(Elements.HEADER2.getName());
            menuLabel.setPadding(new Insets(8, 2, 4, 2));
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
                btn[i].setTextColor("#484b6a; -fx-font-weight: bold; -fx-font-size:14px");
                btn[i].buttonTypeProperty().set(io.github.palexdev.materialfx.enums.ButtonType.RAISED);
                btn[i].depthLevelProperty().set(io.github.palexdev.materialfx.effects.DepthLevel.LEVEL1);
            }

            Label detail_icon = new ImgIcon("src/main/resources/img/administration.png").getIcon();
            detail_icon.setPadding(new Insets(4, 10, 4, 2));

            Label update_icon = new ImgIcon("src/main/resources/img/briefcase.png").getIcon();
            update_icon.setPadding(new Insets(4, 8, 4, 2));

            Label log_icon = new ImgIcon("src/main/resources/img/history.png").getIcon();
            log_icon.setPadding(new Insets(4, 8, 4, 2));

            btn[0].setGraphic(detail_icon);
            btn[0].setText("Admin Detail");

            btn[1].setGraphic(update_icon);
            btn[1].setText("Admin Update");

            btn[2].setGraphic(log_icon);
            btn[2].setText("Activity");

            new SwitchNode(AdminOption.container, btn[0]).switchNode(new AdminDetail());
            new SwitchNode(AdminOption.container, btn[1]).switchNode(new AdminUpdate());
            new SwitchNode(AdminOption.container, btn[2]).switchNode(new Activity());

            navBar.getChildren().addAll(btn[0], btn[1], btn[2]);

            scrollPane.setContent(navBar);

            this.getChildren().add(scrollPane);
        }

    }
}
