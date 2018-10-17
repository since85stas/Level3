package com.example.since85stas.level3.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.since85stas.level3.data.RepositoriesModel;
import com.example.since85stas.level3.data.rest.NetApiClient;
import com.example.since85stas.level3.view.RepositoriesView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by seeyo on 11.10.2018.
 */

@InjectViewState
public class RepositoriesPresenter extends MvpPresenter<RepositoriesView>
        implements Observer<List<RepositoriesModel>> {

    private RepositoriesModel mRepositoriesModel;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        mRepositoriesModel = new RepositoriesModel();
        getViewState().updateRepoList(mRepositoriesModel.getList());
        //loadDate();
        Log.d("UserDetailPresenter", "first attach");
    }

    @Override
    public void attachView(RepositoriesView view) {
        super.attachView(view);
        loadDate();
        Log.d("UserDetailPresenter", "attach view");
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(List<RepositoriesModel> list) {
        Log.d("RepPr", "onNext: " + "rrr");
    }

    private void loadDate() {
        getViewState().startLoad();
        NetApiClient.getInstance().getReps("since85stas")
                .subscribe(this);
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
