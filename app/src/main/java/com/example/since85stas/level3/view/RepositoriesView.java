package com.example.since85stas.level3.view;


import com.arellomobile.mvp.MvpView;

import java.util.List;


public interface RepositoriesView extends MvpView {
    void updateRepoList(List<String> data);
}
