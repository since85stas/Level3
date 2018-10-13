package com.example.since85stas.level3.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by seeyo on 11.10.2018.
 */

public class RepositoriesModel {

    private List<String> mList;

    public RepositoriesModel() {
        mList = new ArrayList<>(3);
        mList.add("first program");
        mList.add("second program");
        mList.add("third program");
    }

    public List<String> getList () {

        return mList;
    }

}
