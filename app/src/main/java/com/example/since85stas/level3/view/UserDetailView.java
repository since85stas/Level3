package com.example.since85stas.level3.view;


import com.arellomobile.mvp.MvpView;


public interface UserDetailView extends MvpView {

    // login
    void setUserInfoText(String s);

    // email
    void setEmailText (String s)  ;

    // avatar
    void setImage(String imageUrl);

    // number of repos
    void setReposNumber(String s);

    void showError(Throwable e);

    void startLoad();

    void finishLoad();
}

