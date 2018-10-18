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

    public UserDetailModel() {

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

