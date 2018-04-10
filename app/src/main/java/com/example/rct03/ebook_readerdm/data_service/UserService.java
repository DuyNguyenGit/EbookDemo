package com.example.rct03.ebook_readerdm.data_service;

import com.example.rct03.ebook_readerdm.models.responses.LoginResponse;

import io.reactivex.Observable;


public interface UserService {

    Observable<LoginResponse> signIn(String user, String password);

//    Observable<SaveCreditCardResponse> saveCreditCard(String token);
}
