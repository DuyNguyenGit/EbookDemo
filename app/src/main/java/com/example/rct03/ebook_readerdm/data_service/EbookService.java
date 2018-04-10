package com.example.rct03.ebook_readerdm.data_service;

import com.example.rct03.ebook_readerdm.models.ebooks.Ebooks;

import io.reactivex.Observable;

public interface EbookService {

    Observable<Ebooks> getEbooks(String token);
}
