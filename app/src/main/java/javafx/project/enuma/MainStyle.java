package javafx.project.enuma;

public enum MainStyle {
    STYLESHEET("./css/style.css"),
    ALT_STYLESHEET("./css/secondary.css");

    public final String label;

    private MainStyle(String location) {
        String path = getClass().getClassLoader().getResource(location).toExternalForm();
        this.label = path;
    }

    public String getLocation() {
        return label;
    }
}
