package com.example.rct03.ebook_readerdm.models.authentication;

import com.google.gson.annotations.SerializedName;

public class AuthToken {

    @SerializedName("token")
    private String token;

    @SerializedName("user_key")
    private String apiKey;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
