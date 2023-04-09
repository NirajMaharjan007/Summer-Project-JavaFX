package javafx.project.enuma;

public enum Elements {
    HEADER1("-fx-font-size: 24;" +
            "-fx-text-fill: Black;-fx-font-weight:bold;"),

    HEADER2("-fx-font-size: 16;" +
            "-fx-text-fill: Black;-fx-font-weight:bold;");

    public final String label;

    private Elements(String label) {
        this.label = label;
    }

    public String getName() {
        return label;
    }
}
