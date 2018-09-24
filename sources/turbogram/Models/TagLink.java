package turbogram.Models;

public class TagLink {
    String tag;
    String title;

    public TagLink(String title, String tag) {
        this.title = title;
        this.tag = tag;
    }

    public String getTitle() {
        return this.title;
    }

    public String getTag() {
        return this.tag;
    }
}
