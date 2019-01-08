package com.example.teamas.loginfunctionality.Login.mainContracts.presenters;

import android.content.Context;
import android.view.ContextMenu;


import com.example.teamas.loginfunctionality.Login.mainContracts.SignInContract;
import com.example.teamas.loginfunctionality.Login.pojo.SignIn.SignInResponse;
import com.example.teamas.loginfunctionality.Login.rest.RetrofitApiClient;
import com.example.teamas.loginfunctionality.Login.rest.RetrofitApiInterface;
import com.example.teamas.loginfunctionality.Login.utils.NetworkReachability;
import com.example.teamas.loginfunctionality.Login.utils.constants.AppNumerics;
import com.google.gson.JsonObject;

import org.reactivestreams.Subscription;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;


public class SignInPresenter implements SignInContract.Presenter {

    private static final String TAG = "MTAG";
    private RetrofitApiInterface retrofit;
    private Context context;
    private SignInContract.View view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    public SignInPresenter(Context context, SignInContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void validateCredentials(final JsonObject signInBody) {
        if (NetworkReachability.isNetworkAvailable(context)) {
            retrofit = RetrofitApiClient.getApiService();
            io.reactivex.Observable<SignInResponse> observable = retrofit.signInUser(signInBody);
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<SignInResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            compositeDisposable.add(d);
                        }

                        @Override
                        public void onNext(SignInResponse signInResponse) {
                            view.signInSuccessful(signInResponse.getSignInData());
                        }

                        @Override
                        public void onError(Throwable e) {
                            switch (((HttpException) e).code()) {
                                case AppNumerics.badRequest:
                                    break;

                                case AppNumerics.dontExist:
                                    break;

                                case AppNumerics.serverDown:
                                    break;

                                case AppNumerics.unauthorized:
                                    break;

                                case AppNumerics.serverUnavailable:
                                    break;
                            }
                        }

                        @Override
                        public void onComplete() {
                            compositeDisposable.dispose();
                        }


                    });

        } else {

        }

    }
}
