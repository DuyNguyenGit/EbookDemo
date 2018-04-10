package com.example.rct03.ebook_readerdm.dataservices;

import com.example.rct03.ebook_readerdm.api.EbookApi;
import com.example.rct03.ebook_readerdm.models.ebooks.Ebooks;

import javax.inject.Inject;

import io.reactivex.Observable;

public class EbookServiceImpl implements EbookService {


    private final EbookApi ebookApi;

    @Inject
    public EbookServiceImpl(EbookApi ebookApi) {
        this.ebookApi = ebookApi;
    }

    @Override
    public Observable<Ebooks> getEbooks(String token) {
        return ebookApi.getEbooks(token);
    }
}
