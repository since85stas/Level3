package com.example.since85stas.level3.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.since85stas.level3.data.RepositoriesModel;
import com.example.since85stas.level3.view.RepositoriesView;

/**
 * Created by seeyo on 11.10.2018.
 */

@InjectViewState
public class RepositoriesPresenter extends MvpPresenter<RepositoriesView> {

    private RepositoriesModel mRepositoriesModel;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        mRepositoriesModel = new RepositoriesModel();
        getViewState().updateRepoList(mRepositoriesModel.getList());
        Log.d("UserDetailPresenter", "first attach");
    }

    @Override
    public void attachView(RepositoriesView view) {
        super.attachView(view);
        Log.d("UserDetailPresenter", "attach view");
    }



}
