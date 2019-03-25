package com.example.roomtest;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.net.wifi.p2p.nsd.WifiP2pUpnpServiceInfo;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.roomtest.Database.AppDatabase;
import com.example.roomtest.Database.User;
import com.example.roomtest.Database.UserDao;

import java.util.List;

public class MainViewModel {
    private static final String TAG = "MainViewModel";

    private final UserDao mUserDao;
    private User userTest;

    public MainViewModel(@NonNull Application application) {
        mUserDao = AppDatabase.getsInstance(application).userDao();

        userTest = new User();
        userTest.setFirstName("ayane");
        userTest.setLastName("sakura");
        userTest.setUid(1);
    }

    //这个方法需要在子线程中执行才行
    public void insertDB_showInLog() {


        mUserDao.insertAll(userTest);
        User getUser = mUserDao.findByName("ayane", "sakura");
        Log.d(TAG, "Hello " + getUser.getFirstName() + " " + getUser.getLastName());
    }
}
