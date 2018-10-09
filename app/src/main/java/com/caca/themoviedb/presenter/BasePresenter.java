package com.caca.themoviedb.presenter;

import com.caca.themoviedb.view.base.BaseView;

public interface BasePresenter<T extends BaseView> {

    void onAttach(T view);

    void onDetach();
}
