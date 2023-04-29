package javafx.project.enuma;

public enum MainStyle {
    STYLESHEET();

    public final String label;

    private MainStyle() {
        // String string = new
        // File("/src/main/resources/css/style.css").toURI().toString();
        String path = getClass().getClassLoader().getResource("./css/style.css").toExternalForm();
        System.out.println("MainStyle.MainStyle(); " + path);
        this.label = path;
    }

    public String getLocation() {
        return label;
    }
}
