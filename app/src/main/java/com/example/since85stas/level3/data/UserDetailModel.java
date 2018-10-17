package com.example.since85stas.level3.data;


import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class UserDetailModel {

    private String login;

    private String public_repos;

    private String updated_at;

    private String email;

    @SerializedName("avatar_url")
    private String avatar;




    private List<String> mList;

    public UserDetailModel() {
        mList = new ArrayList<>(2);
        mList.add("Slava Kpss");
        mList.add("antyhipe@slovospb.ru");
        //mList.add(0);
    }

    public String getElementValueAtIndex(int _index) {
        return mList.get(_index);
    }

    public void setElementValueAtIndex(int _index, String value) {
        mList.set(_index, value);
    }

    @Nullable
    public String getAvatar() {
        return avatar;
    }

    @Nullable
    public String getLogin() {
        return login;
    }

    @Nullable
    public String getPublic_repos() {
        return public_repos;
    }

    @Nullable
    public String getUpdated_at() {
        return updated_at;
    }
}

