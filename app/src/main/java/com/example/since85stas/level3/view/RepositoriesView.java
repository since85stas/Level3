package com.example.since85stas.level3.view;


import com.arellomobile.mvp.MvpView;
import com.example.since85stas.level3.data.RepositoriesModel;

import java.util.List;


public interface RepositoriesView extends MvpView {

    void updateRepoList(List<RepositoriesModel> data, String time, int dataBase);

    void updateRepoListFiltrable(List<RepositoriesModel> data);

    void showError(Throwable e);

    void startLoad();

    void finishLoad();

    void saveToDb();

    void loadFromDb();
}
