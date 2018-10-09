package com.caca.themoviedb.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.bumptech.glide.Glide;
import com.caca.themoviedb.listener.AsyncTaskCompleteListener;
import com.caca.themoviedb.util.Constant;

/**
 * @author caca rusmana
 */
public class GetBackdropBitmapTask extends AsyncTask<Object, Bitmap, Bitmap> {

    private Context context;
    private String backdropPath;
    private AsyncTaskCompleteListener<Object> callBack;

    public GetBackdropBitmapTask(Context context, String backdropPath, AsyncTaskCompleteListener<Object> callBack) {
        this.context = context;
        this.backdropPath = backdropPath;
        this.callBack = callBack;
    }


    @Override
    protected Bitmap doInBackground(Object... objects) {

        try {
            return Glide.with(context).asBitmap().load(Constant.BASE_IMAGE_URL + Constant.IMAGE_WIDTH_200_PARAM + backdropPath).submit().get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        callBack.onTaskComplete(bitmap);
    }
}
