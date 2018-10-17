package com.example.since85stas.level3.presenter;


import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.since85stas.level3.data.rest.NetApiClient;
import com.example.since85stas.level3.view.UserDetailView;
import com.example.since85stas.level3.data.UserDetailModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


@InjectViewState
public class UserDetailPresenter extends MvpPresenter<UserDetailView>
        implements Observer<UserDetailModel> {

    private UserDetailModel mUserDetailModel;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        mUserDetailModel = new UserDetailModel();
        Log.d("UserDetailPresenter", "first attach");
        //loadDate();
    }

    @Override
    public void attachView(UserDetailView view) {
        super.attachView(view);
        Log.d("UserDetailPresenter", "attach view");
        //loadDate();
    }

    public void buttonClick() {
        loadDate();
    }

    private void loadDate() {
        getViewState().startLoad();
        NetApiClient.getInstance().getUser("since85stas")
                .subscribe(this);
    }

    @Override
    public void onSubscribe(Disposable d) {
        //nope
    }

    @Override
    public void onNext(UserDetailModel githubUser) {
        getViewState().setUserInfoText(githubUser.getLogin());
        getViewState().setImage(githubUser.getAvatar());
        getViewState().setReposNumber(githubUser.getPublic_repos());
    }

    @Override
    public void onError(Throwable e) {
        getViewState().showError(e);
    }

    @Override
    public void onComplete() {
        getViewState().finishLoad();
    }
}
