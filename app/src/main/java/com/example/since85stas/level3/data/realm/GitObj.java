package com.example.since85stas.level3.data.realm;

import com.example.since85stas.level3.data.RepositoriesModel;

import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * Created by seeyo on 22.10.2018.
 */

public class GitObj extends RealmObject {

    @Required
    private String name;

    private String descr;

    private String update_at;

    public void setName(String name) {
        this.name = name;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public void setUpdate_at(String update_at) {
        this.update_at = update_at;
    }

    public RepositoriesModel toRepModel () {
        RepositoriesModel model = new RepositoriesModel();
        model.setName(name);
        model.setDescription(descr);
        model.setUpdated_at(update_at);
        return model;
    }

    public void saveList(List<RepositoriesModel> list) {

    }

    public void saveRepItem (RepositoriesModel model) {


    }
}