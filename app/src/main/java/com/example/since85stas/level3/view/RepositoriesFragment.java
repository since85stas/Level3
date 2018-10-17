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
 *    Здесь я конечно немного запутался, где определять adapter, определил пока в updateRepoList
 *    так как там мы передаем наши данные, но тогда возникает вопрос с фильтрацией результата
 *    из editText, где делать его?  пока не могу понять как правильно
 */

public class RepositoriesFragment extends MvpAppCompatFragment implements RepositoriesView {

    RecyclerView recyclerView;
    RepositoriesAdapter adapter;

//    List<String> data  = Collections.emptyList();

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

//        adapter = new RepositoriesAdapter(getActivity(),data);
//        recyclerView.setAdapter(adapter);

        // определяем поиск
        search = rootView.findViewById(R.id.search_edit_text);

        // попытка сделать что-то с Rx, но пока не до конца разобрался
        RxTextView.textChanges(search)
                .subscribe(charSequence -> {
                    if (search != null) {
                        mRepositoriesPresenter.getFilter().filter(charSequence.toString());
                    }
                    Log.d("dbg", "onCreateView: " + search);
                });

        //adapter.getFilter().filter("first");

        return rootView;
    }


    @Override
    public void updateRepoList(List<RepositoriesModel> data) {

        if (data != null) {
            adapter = new RepositoriesAdapter(getActivity(), data);
//        this.data = data;
            recyclerView.setAdapter(adapter);
        }

//        adapter.swap(data);

        //adapter.notifyDataSetChanged();
        //adapter.getFilter().filter("first");
    }

    // метод возвращает строку из поиска

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
