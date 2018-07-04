package com.example.rct03.ebook_readerdm.api;

import com.example.rct03.ebook_readerdm.models.ebooks.Ebooks;
import com.example.rct03.ebook_readerdm.models.user.User;
import com.example.rct03.ebook_readerdm.models.authentication.AuthToken;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface EbookApi {


    String BASE_URL = "http://eba.sap-press.com/v1/";

    @Headers({
            "Content-Type: application/x-www-form-urlencoded"
    })
    @FormUrlEncoded
    @POST("account/token")
    Observable<AuthToken> getToken(@Field("email") String first,
                                   @Field("password") String password,
                                   @Field("app_version") String appVersion,
                                   @Field("device_id") String deviceId,
                                   @Field("os_type") String osType,
                                   @Field("os_version") String osVersion,
                                   @Field("device_name") String deviceName,
                                   @Field("device_name_manufacturer") String deviceManufacturer);

    @GET("account")
    Observable<User> getUser(@Header("Authorization") String authorization);

    @GET("account/ebooks")
    Observable<Ebooks> getEbooks(@Header("Authorization") String authorization);


    @Headers({
            "file_path: /OEBPS/content.opf",
            "app_version : 0"
    })
    @Streaming
    @GET
    Observable<ResponseBody> download(@Header("Authorization") String authorization, @Url String url);


    @GET
    Observable<Response<ResponseBody>> downloadSingleFile(@Header("Authorization") String authorization,
                                            @Header("app_version") String appVersion,
                                            @Url String url);

    @GET
    Observable<Response<ResponseBody>> test1(@Header("Authorization") String authorization,
                                            @Header("app_version") String appVersion,
                                            @Url String url);
}
