package com.example.rct03.ebook_readerdm.dataservices;

import com.example.rct03.ebook_readerdm.api.EbookApi;
import com.example.rct03.ebook_readerdm.models.user.User;
import com.example.rct03.ebook_readerdm.models.authentication.AuthToken;

import javax.inject.Inject;

import io.reactivex.Observable;


public class UserServiceImpl implements UserService {


    private final EbookApi ebookApi;

    @Inject
    public UserServiceImpl(EbookApi ebookApi) {
        this.ebookApi = ebookApi;
    }

    @Override
    public Observable<AuthToken> signIn(String user, String password) {
        return ebookApi.getToken("christian@2denker.de",
                "EZTwNrr93zqDKfpc",
                "0",
                "6b58222e-37d4-11e8-b467-0ed5f89f718b",
                "android",
                "6.0",
                "Sam Sung J7",
                "samsung");
    }

    @Override
    public Observable<User> getUserAccount(String token) {
        return ebookApi.getUser(token);
    }

}
