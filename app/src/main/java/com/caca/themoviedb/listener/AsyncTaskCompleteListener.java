package com.caca.themoviedb.listener;

/**
 * @author caca rusmana
 */
public interface AsyncTaskCompleteListener<T> {
    void onTaskComplete(T... params);
}
