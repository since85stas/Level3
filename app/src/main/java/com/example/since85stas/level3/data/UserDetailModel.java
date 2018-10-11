package com.example.since85stas.level3.data;


import java.util.ArrayList;
import java.util.List;


public class UserDetailModel {

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
}

