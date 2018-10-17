package com.example.since85stas.level3.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.since85stas.level3.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RepositoriesAdapter extends RecyclerView.Adapter<RepositoriesAdapter.RepositoriesViewHolder>
        implements Filterable {

    class RepositoriesViewHolder extends RecyclerView.ViewHolder {

        TextView repoName;
        TextView numCommits;
        TextView numBranches;
        TextView lastUpdate;


        public RepositoriesViewHolder(@NonNull View itemView) {
            super(itemView);

            repoName = itemView.findViewById(R.id.repository_name);
        }
    }

    List<String> data = Collections.emptyList();

    private LayoutInflater inflater;
    private Context context;

    public RepositoriesAdapter(Context context, List<String> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @NonNull
    @Override
    public RepositoriesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.repositories_list_item, viewGroup, false);
        RepositoriesViewHolder holder = new RepositoriesViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RepositoriesViewHolder holder, int i) {
        String repoName = data.get(i);

        holder.repoName.setText(repoName);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void swap(List<String> datas)
    {
        if(datas == null || datas.size()==0)
            return;
        if (data != null && data.size()>0)
            data.clear();
        data.addAll(datas);
        notifyDataSetChanged();

    }

    // добавляем метод ля фильтра RecycleView пока сделано для случая когда передаем String
    @Override
    public Filter getFilter() {
        return new Filter() {
            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                data = (List<String>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<String> filteredResults = null;
                if (constraint.length() == 0) {
                    filteredResults = data;
                } else {
                    filteredResults = getFilteredResults(constraint.toString().toLowerCase());
                }

                FilterResults results = new FilterResults();
                results.values = filteredResults;

                return results;
            }

            protected List<String> getFilteredResults(String constraint) {
                List<String> results = new ArrayList<>();

                for (String item : data) {
                    if (item.toLowerCase().contains(constraint)) {
                        results.add(item);
                    }
                }
                return results;
            }
        };
    }
}



