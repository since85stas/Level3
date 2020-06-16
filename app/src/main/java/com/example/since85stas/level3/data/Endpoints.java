package com.example.since85stas.level3.data;


import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface Endpoints {

    @GET("/users/{user}")
    Observable<UserDetailModel> getUser(
            @Path("user") String user);

    @GET("/users/{user}/repos")
    Observable<List<RepositoriesModel>> getRepos(
            @Path("user") String user);

//    @GET("/repositories")
//    Observable<List<RepositoriesModel>> getRepos(
//            );
}
