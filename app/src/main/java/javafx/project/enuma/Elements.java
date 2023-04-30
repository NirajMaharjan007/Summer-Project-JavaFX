package javafx.project.enuma;

public enum Elements {
    HEADER1("-fx-font-size: 24;" +
            "-fx-text-fill: Black;-fx-font-weight:bold;"),

    HEADER2("-fx-font-size: 16;" +
            "-fx-text-fill: Black;-fx-font-weight:bold;"),

    DANGER_COLOR("#f53b3b"),

    DANGER_ALT_COLOR("#d21d1d"),

    SUCCESS_COLOR("#54B435"),

    SUCCESS_ALT_COLOR("#82CD47"),

    INFO_COLOR("#00A896"),

    INFO_ALT_COLOR("#8fcee2");

    public final String label;

    private Elements(String label) {
        this.label = label;
    }

    public String getName() {
        return label;
    }
}
