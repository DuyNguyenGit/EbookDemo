package com.example.rct03.ebook_readerdm.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.rct03.ebook_readerdm.App;
import com.example.rct03.ebook_readerdm.R;
import com.example.rct03.ebook_readerdm.data_service.UserService;
import com.example.rct03.ebook_readerdm.models.responses.LoginResponse;
import com.google.gson.Gson;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    @Inject
    UserService userService;


    @BindView(R.id.edtEmail)
    EditText edtUsername;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private CompositeSubscription subscriptions = new CompositeSubscription();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        App.getAppComponent().inject(this);
    }


    /**
     * Unsubscribes the subscriptions and unbind views
     */
    @Override
    protected void onDestroy() {
        if (null != subscriptions) {
            subscriptions.clear();
            subscriptions = null;
        }
        super.onDestroy();
    }

    public void signIn(View view) {
        final String email = edtUsername.getText().toString();
        final String password = edtPassword.getText().toString();
        subscriptions.add(userService.signIn(email, password)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(this::showLoading)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loginResponse -> {
                    Log.e(TAG, "signIn success: >>>" + new Gson().toJson(loginResponse));
                }, throwable -> {
                    hideLoading();
                    Log.e(TAG, "signIn error: >>>" + throwable.getMessage());
                }));
    }



    protected void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    protected void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }
}
