package com.example.since85stas.level3.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.since85stas.level3.R;
import com.example.since85stas.level3.data.RepositoriesModel;
import com.example.since85stas.level3.presenter.RepositoriesPresenter;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 */

public class RepositoriesFragment extends MvpAppCompatFragment implements RepositoriesView {

    RecyclerView recyclerView;
    RepositoriesAdapter adapter;
    EditText search;

    public RepositoriesFragment() {

    }

    @InjectPresenter
    RepositoriesPresenter mRepositoriesPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //
        View rootView = inflater.inflate(R.layout.repositories_list,container,false);

        // определяем recycleview
        recyclerView = rootView.findViewById(R.id.repositories_recycle_view);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);

        // определяем поиск
        search = rootView.findViewById(R.id.search_edit_text);

        // попытка сделать что-то с Rx, не знаю так наверное не правильно,
        // когда обращаемся к презентеру?
        RxTextView.textChanges(search)
                .subscribe(charSequence -> {
                    if (search != null || search.length() != 0) {
                        mRepositoriesPresenter.getFilter().filter(charSequence.toString());
                    }
                    Log.d("dbg", "onCreateView: " + search);
                });
        return rootView;
    }


    @Override
    public void updateRepoList(List<RepositoriesModel> data) {
        if (data != null) {
            adapter = new RepositoriesAdapter(getActivity(), data);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void showError(Throwable e) {

    }

    @Override
    public void startLoad() {

    }

    @Override
    public void finishLoad() {

    }
}
