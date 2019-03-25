package com.example.roomtest.Database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

@Database(entities = {User.class, Article.class}, version = 3)
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
//                            .addMigrations(MIGRATION_3_4)
                            .fallbackToDestructiveMigration() //使用这个方法，数据被清空，不需要使用Migration
                            .build();
                }
            }
        }
        return sInstance;
    }

    //这个地方是一个abstract？使用的使用，需要创建吗？
    //好像是不需要？因为database里面有entities = {User.class}
    public abstract UserDao userDao();

    public abstract ArticleDao articleDao();


    /**
     * 每次的版本更新的不同情况，都需要使用定义不同的Migration
     * 参考：http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2017/0728/8278.html
     */
    static final Migration MIGRATION_3_4 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // if we didn't alter(修改) the table, there's nothing else to do here.

            //3_4增加表Article
            //没有创建成功吗，这个语句？
            database.execSQL(
                    "CREATE TABLE article (Article_id INTEGER, isInCreate INTEGER, context TEXT, title TEXT, pic_address TEXT, PRIMARY KEY(Article_id))"
            );
        }
    };
}