package codes.codeoutprakhar.viewpager;

import java.io.Serializable;

public class newsModel implements Serializable {
    String title;
    String description;
    String imgUrl;
    String sourceUrl;

    public newsModel(String title, String description, String imgUrl, String sourceUrl) {
        this.title = title;
        this.description = description;
        this.imgUrl = imgUrl;
        this.sourceUrl = sourceUrl;
    }

    public newsModel(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }
}
