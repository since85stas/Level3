package com.example.since85stas.level3.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;
import com.example.since85stas.level3.R;
import com.example.since85stas.level3.presenter.UserDetailPresenter;

/**
 * Created by seeyo on 10.10.2018.
 */


public class UserDetailFragment extends MvpAppCompatFragment implements UserDetailView {
    private Button getInfoButton;
    TextView userName ;
    TextView email;
    ImageView avtar;
    TextView numRepos;

    @InjectPresenter
    UserDetailPresenter mUserDetailPresenter;

    public UserDetailFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.users_details,container,false);

        getInfoButton = rootView.findViewById(R.id.get_information);
        userName      = rootView.findViewById(R.id.user_name_text);
        email         = rootView.findViewById(R.id.user_email_text);
        avtar         = rootView.findViewById(R.id.avatar);
        numRepos      = rootView.findViewById(R.id.num_repos);

        getInfoButton.setOnClickListener( view -> { mUserDetailPresenter.buttonClick(); });
        return rootView;
    }

    @Override
    public void setUserInfoText(String s) {
        userName.setText(s);
    }

    @Override
    public void setEmailText(String s) {
            email.setText(s);
    }

    @Override
    public void setImage(String imageUrl) {
        Glide.with(this)
                .load(imageUrl)
                .into(avtar);
    }

    @Override
    public void setReposNumber(String s) {
        numRepos.setText(s);
    }

    @Override
    public void showError(Throwable e) {

    }

    @Override
    public void startLoad() {

    }

    @Override
    public void finishLoad() {

    }
}
