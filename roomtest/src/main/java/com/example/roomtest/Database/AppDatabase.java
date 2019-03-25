package com.example.roomtest.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {User.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase sInstance;

    // 构造函数必须是public，否则报错
    public AppDatabase() {
    }

    // 单实例模式，节省资源
    public static AppDatabase getsInstance(Context context) {
        if (sInstance == null) {
            synchronized (AppDatabase.class) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "sampleDb")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return sInstance;
    }

    //这个地方是一个abstract？使用的使用，需要创建吗？
    //好像是不需要？因为database里面有entities = {User.class}
    public abstract UserDao userDao();
}