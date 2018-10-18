package com.example.since85stas.level3.data;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by seeyo on 11.10.2018.
 */

public class RepositoriesModel {

    String name ;
    String description;
    String updated_at;
    String size;

    private List<String> mList;

    public RepositoriesModel() {
    }

    public List<String> getList () {

        return mList;
    }

    @Nullable
    public String getName() {
        return name;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    @Nullable
    public String getUpdated_at() {
        return updated_at;
    }

    @Nullable
    public String getSize() {
        return size;
    }
}
