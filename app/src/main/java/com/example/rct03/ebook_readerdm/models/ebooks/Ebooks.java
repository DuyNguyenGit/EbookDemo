
package com.example.rct03.ebook_readerdm.models.ebooks;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ebooks {

    @SerializedName("results")
    @Expose
    private List<Ebook> ebooks = null;
    @SerializedName("count")
    @Expose
    private int count;

    public List<Ebook> getEbooks() {
        return ebooks;
    }

    public void setEbooks(List<Ebook> ebooks) {
        this.ebooks = ebooks;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
