package id.sch.smktelkom_mlg.privateassignment.xirpl614.mymoviesapp;

public class MainListItem {
    private String head;
    private String desc;
    private String imageUrl;

    public MainListItem(String head, String desc, String imageUrl) {
        this.head = head;
        this.desc = desc;
        this.imageUrl = imageUrl;
    }

    public String getHead() {
        return head;
    }

    public String getDesc() {
        return desc;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}