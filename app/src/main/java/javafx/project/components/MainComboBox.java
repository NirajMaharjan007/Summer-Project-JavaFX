package javafx.project.components;

import io.github.palexdev.materialfx.controls.*;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;

public class MainComboBox<T> extends MFXComboBox<T> {

    public MainComboBox() {
        super();
        this.init();
    }

    public MainComboBox(ObservableList<T> data) {
        super(data);
        this.init();
    }

    private void init() {
        MFXButton btn = new ImgIcon("src/main/resources/img/down-arrow.png").getBtnIcon();
        btn.setPadding(new Insets(2.25f));

        this.setTrailingIcon(btn);
        this.setAllowEdit(false);
        this.setScrollOnOpen(true);
        this.setSelectable(true);
    }

}
