package com.example.robertchen.rtsptesting;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.robertchen.rtsptesting.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mActivityMainBinding;
    ViewModelMainActivity mViewModelMainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //init model
        mViewModelMainActivity = new ViewModelMainActivity(this, mActivityMainBinding.viewVideo);
        mActivityMainBinding.setViewModelMain(mViewModelMainActivity);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mViewModelMainActivity.stopPlayStreaming();
    }
}
