package id.sch.smktelkom_mlg.privateassignment.xirpl614.mymoviesapp;

public class MainListItem {
    private String imageUrl;
    private String head;
    private String desc;

    public MainListItem(String imageUrl, String head, String desc) {
        this.imageUrl = imageUrl;
        this.head = head;
        this.desc = desc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getHead() {
        return head;
    }

    public String getDesc() {
        return desc;
    }
}