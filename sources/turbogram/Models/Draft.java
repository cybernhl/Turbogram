package turbogram.Models;

public class Draft {
    String text;
    String title;

    public Draft(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public String getTitle() {
        return this.title;
    }

    public String getText() {
        return this.text;
    }
}
