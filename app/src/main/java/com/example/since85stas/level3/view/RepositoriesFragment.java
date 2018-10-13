package com.example.since85stas.level3.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.since85stas.level3.R;
import com.example.since85stas.level3.presenter.RepositoriesPresenter;

import java.util.List;

/**
 * Created by seeyo on 11.10.2018.
 */

public class RepositoriesFragment extends MvpAppCompatFragment implements RepositoriesView {

    RecyclerView recyclerViewm;
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

        //CharSequence s;
        View rootView = inflater.inflate(R.layout.repositories_list,container,false);

        recyclerViewm = rootView.findViewById(R.id.repositories_recycle_view);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerViewm.setLayoutManager(manager);

        search = rootView.findViewById(R.id.search_edit_text);

        // попытка сделать что-то с Rx, но пока не до конца разобрался
//        RxTextView.textChanges(search)
//                .subscribe(charSequence -> {
//
//                });

        return rootView;
    }


    @Override
    public void updateRepoList(List<String> data) {
        //this.data = data;

        adapter = new RepositoriesAdapter(getActivity(),data);
        recyclerViewm.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


}
