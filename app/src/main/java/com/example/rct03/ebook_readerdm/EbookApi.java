package com.example.rct03.ebook_readerdm;

import com.example.rct03.ebook_readerdm.models.responses.LoginResponse;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

public interface EbookApi {


    String BASE_URL = "http://eba.sap-press.com";

    @Headers({
            "x-project: sap-press",
            "Content-Type: application/x-www-form-urlencoded"
    })
    @FormUrlEncoded
    @POST("account/token")
    Observable<LoginResponse> getToken(@Field("email") String first,
                                       @Field("password") String password,
                                       @Field("app_version") String appVersion,
                                       @Field("device_id") String deviceId,
                                       @Field("os_type") String osType,
                                       @Field("os_version") String osVersion,
                                       @Field("device_name") String deviceName,
                                       @Field("device_name_manufacturer") String deviceManufacturer);
}
