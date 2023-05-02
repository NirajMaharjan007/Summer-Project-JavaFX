package javafx.project.enuma;

public enum MainStyle {
    STYLESHEET("./css/style.css"),
    ALT_STYLESHEET("./css/secondary.css");

    public final String label;

    private MainStyle(String location) {
        // String string = new
        // File("/src/main/resources/css/style.css").toURI().toString();
        String path = getClass().getClassLoader().getResource(location).toExternalForm();
        System.out.println("MainStyle.MainStyle(); " + path);
        this.label = path;
    }

    public String getLocation() {
        return label;
    }
}
