package com.example.since85stas.level3.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.since85stas.level3.R;
import com.example.since85stas.level3.presenter.UserDetailPresenter;

/**
 * Created by seeyo on 10.10.2018.
 */


public class UserDetailFragment extends MvpAppCompatFragment implements UserDetailView {
    private Button getInfoButton;
    TextView userName ;
    TextView email;

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

        getInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUserDetailPresenter.buttonClick();
            }
        });
        return rootView;
    }

    // пока особой логики нет,по нажатию кнопки меняем текст, на значение из модели и добавляем '!'
    @Override
    public void setUserInfoText(String s) {
        userName.setText(s);
    }

    @Override
    public void setEmailText(String s) {
        email.setText(s);
    }
}
