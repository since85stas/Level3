package com.example.since85stas.level3.presenter;


import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.since85stas.level3.view.UserDetailView;
import com.example.since85stas.level3.data.UserDetailModel;

@InjectViewState
public class UserDetailPresenter extends MvpPresenter<UserDetailView> {

    private UserDetailModel mUserDetailModel;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        mUserDetailModel = new UserDetailModel();
        Log.d("UserDetailPresenter", "first attach");
    }

    @Override
    public void attachView(UserDetailView view) {
        super.attachView(view);
        Log.d("UserDetailPresenter", "attach view");
    }

    private String getNewUserInfo(int modelElementIndex) {
        String currentValue = mUserDetailModel.getElementValueAtIndex(modelElementIndex);
        return currentValue + " !";
    }

    public void buttonClick() {
        mUserDetailModel.setElementValueAtIndex(0, getNewUserInfo(0));
        mUserDetailModel.setElementValueAtIndex(1, getNewUserInfo(1));
        getViewState().setUserInfoText(getNewUserInfo(0));
        getViewState().setEmailText(getNewUserInfo(1));

    }
}
