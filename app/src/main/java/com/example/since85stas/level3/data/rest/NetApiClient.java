package com.example.since85stas.level3.data.rest;


import com.example.since85stas.level3.data.Endpoints;
import com.example.since85stas.level3.data.RepositoriesModel;
import com.example.since85stas.level3.data.UserDetailModel;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class NetApiClient {

    private static final NetApiClient ourInstance = new NetApiClient();

    public static NetApiClient getInstance() {
        return ourInstance;
    }

    private Endpoints netApi = new ServiceGenerator().createService(Endpoints.class);

    private NetApiClient() {
    }

    public Observable<UserDetailModel> getUser(String user){
        return netApi.getUser(user)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public Flowable<List<RepositoriesModel>> getReps(){
        return netApi.getRepos()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
