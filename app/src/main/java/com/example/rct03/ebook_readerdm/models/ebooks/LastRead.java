
package com.example.rct03.ebook_readerdm.models.ebooks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LastRead {

    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("product_id")
    @Expose
    private int productId;
    @SerializedName("file_name")
    @Expose
    private String fileName;
    @SerializedName("user_id")
    @Expose
    private String userId;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
