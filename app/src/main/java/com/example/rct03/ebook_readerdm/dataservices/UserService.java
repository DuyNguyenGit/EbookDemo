package com.example.rct03.ebook_readerdm.dataservices;

import com.example.rct03.ebook_readerdm.models.user.User;
import com.example.rct03.ebook_readerdm.models.authentication.AuthToken;

import io.reactivex.Observable;


public interface UserService {

    Observable<AuthToken> signIn(String user, String password);

    Observable<User> getUserAccount(String token);

}
