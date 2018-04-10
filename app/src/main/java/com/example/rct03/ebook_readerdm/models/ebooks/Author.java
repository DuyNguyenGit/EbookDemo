
package com.example.rct03.ebook_readerdm.models.ebooks;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Author {

    @SerializedName("images")
    @Expose
    private List<Image> images = null;
    @SerializedName("info")
    @Expose
    private String info;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("first_name")
    @Expose
    private String firstName;

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

}
