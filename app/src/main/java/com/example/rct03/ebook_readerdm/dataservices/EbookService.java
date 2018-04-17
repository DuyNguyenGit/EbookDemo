package com.example.rct03.ebook_readerdm.dataservices;

import com.example.rct03.ebook_readerdm.models.ebooks.Ebooks;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;

public interface EbookService {

    Observable<Ebooks> getEbooks(String token);

    Observable<ResponseBody> downloadEbook(String token, String url);

    Observable<retrofit2.Response<ResponseBody>> downloadSingleFile(String token, String appVersion, String url);
}
