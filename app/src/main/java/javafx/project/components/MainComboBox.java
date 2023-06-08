package javafx.project.components;

import io.github.palexdev.materialfx.controls.*;
import javafx.geometry.Insets;

public class MainComboBox extends MFXComboBox<String> {

    public MainComboBox() {
        super();
        this.init();
    }

    private void init() {
        MFXButton btn = new ImgIcon("src/main/resources/img/down-arrow.png").getBtnIcon();
        btn.setPadding(new Insets(4));

        this.setTrailingIcon(btn);
        this.setAllowEdit(false);
        this.setScrollOnOpen(true);
        this.setSelectable(true);
    }

}
