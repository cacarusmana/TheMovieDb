package com.caca.themoviedb.view.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.caca.themoviedb.R;

/**
 * @author caca rusmana
 */
public class BaseActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.label_loading));
    }

    public void initToolbar(Toolbar toolbar, String title) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View v) {
                                                     onBackPressed();
                                                 }
                                             }
        );

        setTitle(title);
    }

    protected void showProgressDialog() {
        progressDialog.show();
    }

    protected void hideProgressDialog() {
        progressDialog.hide();
    }

    protected void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
