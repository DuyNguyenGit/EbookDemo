package com.example.rct03.ebook_readerdm.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.rct03.ebook_readerdm.App;
import com.example.rct03.ebook_readerdm.R;
import com.example.rct03.ebook_readerdm.data_service.EbookService;
import com.example.rct03.ebook_readerdm.data_service.UserService;
import com.example.rct03.ebook_readerdm.models.ebooks.Ebooks;
import com.example.rct03.ebook_readerdm.models.user.User;
import com.example.rct03.ebook_readerdm.models.responses.LoginResponse;
import com.google.gson.Gson;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Inject
    UserService userService;
    @Inject
    EbookService ebookService;

    @BindView(R.id.edtEmail)
    EditText edtUsername;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        App.getAppComponent().inject(this);
    }

    @Override
    protected void onDestroy() {
        if (null != mCompositeDisposable) {
            mCompositeDisposable.clear();
            mCompositeDisposable = null;
        }
        super.onDestroy();
    }

    public void signIn(View view) {
        final String email = edtUsername.getText().toString();
        final String password = edtPassword.getText().toString();
        showLoading();
        final Disposable disposable = userService.signIn(email, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError);

        mCompositeDisposable.add(disposable);

    }

    private void handleResponse(LoginResponse loginResponse) {
        Log.e(TAG, "signIn success: >>>" + new Gson().toJson(loginResponse));
        getUserInfo(loginResponse);
        getEbooks(loginResponse);
    }

    private void getEbooks(LoginResponse loginResponse) {
        final String token = "Token ".concat(loginResponse.getToken());
        final Disposable disposable = ebookService.getEbooks(token)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleEbooks, this::handleError);
        mCompositeDisposable.add(disposable);
    }

    private void handleEbooks(Ebooks ebooks) {
        hideLoading();
        Log.e(TAG, "handleEbooks: >>>Number of Ebook = " + new Gson().toJson(ebooks));
        if (ebooks.getEbooks().size() > 0)
            Log.e(TAG, "handleEbooks: >>>Title of Ebook  = " + ebooks.getEbooks().get(0).getTitle());
    }

    private void getUserInfo(LoginResponse loginResponse) {
        final String token = "Token ".concat(loginResponse.getToken());
        final Disposable disposable = userService.getUserAccount(token)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleUserAccount, this::handleError);
        mCompositeDisposable.add(disposable);
    }

    private void handleUserAccount(User user) {
        hideLoading();
        Log.e(TAG, "handleUserAccount: >>>FirstName = " + user.getFirstName());
    }

    private void handleError(Throwable error) {
        hideLoading();
        Log.e(TAG, "signIn error: >>>" + error.getLocalizedMessage());
    }

    private void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    protected void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }
}
