package turbogram.Models;

public class SideMenuItem {
    int delButton;
    int id;
    int show;
    String title;

    public SideMenuItem(int id, String title, int show, int delButton) {
        this.id = id;
        this.title = title;
        this.show = show;
        this.delButton = delButton;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public int getShow() {
        return this.show;
    }

    public int getDelButton() {
        return this.delButton;
    }
}
