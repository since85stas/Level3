package com.example.since85stas.level3.presenter;

import android.util.Log;
import android.widget.Filter;
import android.widget.Filterable;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.since85stas.level3.data.RepositoriesModel;
import com.example.since85stas.level3.data.rest.NetApiClient;
import com.example.since85stas.level3.view.RepositoriesView;

import java.util.ArrayList;
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

    public void setSearch(String search) {
        this.search = search;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        mRepositoriesModel = new RepositoriesModel();
        //getViewState().updateRepoList(mRepositoriesModel.getList());
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
        data = list;
        Log.d("RepPr", "onNext: " + "rrr");
        getViewState().updateRepoList(data);
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

    @Override
    public Filter getFilter() {
        return new Filter() {
            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                data = (List<RepositoriesModel>) results.values;
                getViewState().updateRepoList(data);
                //notifyDataSetChanged();
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
