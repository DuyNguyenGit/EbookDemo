package com.example.rct03.ebook_readerdm.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.rct03.ebook_readerdm.App;
import com.example.rct03.ebook_readerdm.EbookApi;
import com.example.rct03.ebook_readerdm.R;
import com.example.rct03.ebook_readerdm.data_service.UserService;
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
    EbookApi ebookApi;

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


    /**
     * Unsubscribes the mCompositeDisposable and unbind views
     */
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
        Disposable disposable = userService.signIn(email, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError);

        mCompositeDisposable.add(disposable);


    }

    private void showLoading(Disposable disposable) {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void handleResponse(LoginResponse loginResponse) {
        Log.e(TAG, "signIn success: >>>" + new Gson().toJson(loginResponse));
    }
    private void handleError(Throwable error) {
        hideLoading();
        Log.e(TAG, "signIn error: >>>" + error.getLocalizedMessage());
    }



    protected void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }
}
