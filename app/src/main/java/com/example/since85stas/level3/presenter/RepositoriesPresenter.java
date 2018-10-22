package com.example.since85stas.level3.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Filter;
import android.widget.Filterable;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.since85stas.level3.data.RepositoriesModel;
import com.example.since85stas.level3.data.rest.NetApiClient;
import com.example.since85stas.level3.data.sql.GitContract;
import com.example.since85stas.level3.data.sql.GitDataSourse;
import com.example.since85stas.level3.data.sql.GitHelper;
import com.example.since85stas.level3.view.RepositoriesView;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by seeyou on 11.10.2018.
 */

@InjectViewState
public class RepositoriesPresenter extends MvpPresenter<RepositoriesView>
        implements Observer<List<RepositoriesModel>> , Filterable {

    private RepositoriesModel mRepositoriesModel;

    private List<RepositoriesModel>  data;

    private String search;

    private Context contex;

    public RepositoriesPresenter (Context contex) {
        this.contex = contex;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        mRepositoriesModel = new RepositoriesModel();
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

        data = list;
        Log.d("RepPr", "onNext: " + "rrr");
        getViewState().finishLoad();

        // сохраняем в БД
        GitDataSourse sourse = new GitDataSourse(contex);
        sourse.open();
        sourse.deleteAll();
        int count = sourse.saveListToDb(list);
        Log.d("RepPr", "onNext: " + "lines saved " + count);
        sourse.close();

        }

    public void loadDate() {
        getViewState().startLoad();
        NetApiClient.getInstance().getReps("since85stas")
                .subscribe(this);
    }

    public String loadFromSql() {
        GitDataSourse sourse = new GitDataSourse(contex);
        // загружаем из БД
        long timeInit = Calendar.getInstance().getTimeInMillis();
        sourse.open();
        List<RepositoriesModel> listFromDb = sourse.getAllNotes();
        sourse.close();
        long timeFinal = Calendar.getInstance().getTimeInMillis();
        long timeSqlLoad = timeFinal - timeInit;
        getViewState().updateRepoList(listFromDb,String.valueOf(timeSqlLoad));
        return String.valueOf(timeSqlLoad);
    }

    private void saveRepoToDb() {

    }

    private void loadFromDb() {

    }



    @Override
    public void onError(Throwable e) {
        getViewState().showError(e);
    }

    @Override
    public void onComplete() {
        getViewState().finishLoad();
    }

    // пока поиск сделан только по имени репозитория
    @Override
    public Filter getFilter() {
        return new Filter() {
            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                List<RepositoriesModel> newData = (List<RepositoriesModel>) results.values;
                //data = (List<RepositoriesModel>) results.values;
                getViewState().updateRepoList(newData,null);
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<RepositoriesModel> filteredResults = null;
                if (constraint.length() == 0) {
                    filteredResults = data;
                } else {
                    filteredResults = getFilteredResults(constraint.toString().toLowerCase());
                }

                FilterResults results = new FilterResults();
                results.values = filteredResults;

                return results;
            }

            protected List<RepositoriesModel> getFilteredResults(String constraint) {
                List<RepositoriesModel> results = new ArrayList<>();

                for (RepositoriesModel item : data) {
                    if (item.getName().toLowerCase().contains(constraint)) {
                        results.add(item);
                    }
                }
                return results;
            }
        };
    }

}
