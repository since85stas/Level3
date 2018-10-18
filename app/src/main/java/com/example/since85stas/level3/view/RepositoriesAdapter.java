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
import com.example.since85stas.level3.data.RepositoriesModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RepositoriesAdapter extends RecyclerView.Adapter<RepositoriesAdapter.RepositoriesViewHolder>
        {

    class RepositoriesViewHolder extends RecyclerView.ViewHolder {

        TextView repoName;
        TextView numCommits;
        TextView numBranches;
        TextView lastUpdate;
        TextView descr;


        public RepositoriesViewHolder(@NonNull View itemView) {
            super(itemView);

            repoName = itemView.findViewById(R.id.repository_name);
            numCommits = itemView.findViewById(R.id.repository_commits);
            lastUpdate = itemView.findViewById(R.id.repository_upd);
            descr = itemView.findViewById(R.id.repository_descr);
        }
    }

    List<RepositoriesModel> data = Collections.emptyList();

    private LayoutInflater inflater;
    private Context context;

    public RepositoriesAdapter(Context context, List<RepositoriesModel> data) {
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
        String repoName = data.get(i).getName();
        String commits = data.get(i).getSize();
        String descr = data.get(i).getDescription();
        String upd = data.get(i).getUpdated_at();

        holder.repoName.setText(repoName);
        holder.descr.setText(descr);
        holder.numCommits.setText(commits);
        holder.lastUpdate.setText(upd);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}



