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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.since85stas.level3.R;
import com.example.since85stas.level3.data.RepositoriesModel;
import com.example.since85stas.level3.presenter.RepositoriesPresenter;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.List;

/**
 */

public class RepositoriesFragment extends MvpAppCompatFragment implements RepositoriesView {

    RecyclerView recyclerView;

    @Override
    public void saveToDb() {

    }

    @Override
    public void loadFromDb() {

    }

    RepositoriesAdapter adapter;
    EditText search;
    TextView timeSqlText;
    TextView timeNetText;
    TextView timeRealmText;

    public RepositoriesFragment() {

    }

    @InjectPresenter
    RepositoriesPresenter mRepositoriesPresenter;

    @ProvidePresenter
    RepositoriesPresenter mRepositoriesPresenter() {
        return new RepositoriesPresenter(getContext().getApplicationContext());
    }

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

        Button getFromNet      = rootView.findViewById(R.id.from_net_button);
        timeNetText            = rootView.findViewById(R.id.net_time);
        getFromNet.setOnClickListener(view -> mRepositoriesPresenter.loadDate());

        Button getFromSqlButton = rootView.findViewById(R.id.from_sql_button);
        timeSqlText             = rootView.findViewById(R.id.sql_time);
        getFromSqlButton.setOnClickListener( view -> mRepositoriesPresenter.loadFromSql());

        Button getFromRealmButton = rootView.findViewById(R.id.from_realm_button);
        timeRealmText = rootView.findViewById(R.id.realm_time);
        getFromRealmButton.setOnClickListener(view -> mRepositoriesPresenter.);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void updateRepoList(List<RepositoriesModel> data, String time, int dbType) {
        if (data != null) {
            adapter = new RepositoriesAdapter(getActivity(), data);
            recyclerView.setAdapter(adapter);
        }

        if (time != null) {
            switch (dbType) {
                // на сколько правильно использовать такие константы ?
                case RepositoriesPresenter.SQL_CASE:
                    timeSqlText.setText(time);
                    break;
                case RepositoriesPresenter.REALM_CASE:
                    timeRealmText.setText(time);
                    break;
            }
        }
    }

    @Override
    public void updateRepoListFiltrable(List<RepositoriesModel> data) {
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
        Toast toast = new Toast ( getContext() );
        toast.setText("Finish load");
        toast.show();
    }
}
