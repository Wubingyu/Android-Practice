package com.example.roomtest;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.roomtest.Database.AppDatabase;
import com.example.roomtest.Database.Article;
import com.example.roomtest.Database.ArticleDao;
import com.example.roomtest.Database.User;
import com.example.roomtest.Database.UserDao;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private UserDao mUserDao;
    private ArticleDao mArticleDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //一定要用Tid不要用Uid
        //因为Uid是进程的用户ID，你用户又没有变，怎么会变嘛，你是不是傻？
        //Pid也不能用，Pid是进程ID
        Log.d(TAG, "MainActivity onCreate ThreadID" + android.os.Process.myTid());

        //如果把这个放在构造函数中执行是不行的，因为那个时候还没有执行onCreate，那么似乎它还不是一个activity 于是执行（在getsInstance中的context.getApplicationContext()就会出错）
        mUserDao = AppDatabase.getsInstance(MainActivity.this).userDao();
        mArticleDao = AppDatabase.getsInstance(MainActivity.this).articleDao();
        
        initDB();
        getAllUsers();

    }


    private void initDB() {
        Log.d(TAG, "initDB: ");
        User user1 = new User("ayane", "sakura");
        User user2 = new User("Wu", "BingYu");

        Article article = new Article(true, "title", "context", "address");

        //Rx子线程插入数据
        Observable.just(user1, user2)
                .subscribeOn(Schedulers.io())
                //observeOn 和 subscribeOn???? 为啥之前学习的时候要这样。
                //.subscribeOn(Schedulers.io())
                //.observeOn(AndroidSchedulers.mainThread())
                //直接两个subscribeOn不行吗？
                .subscribe(user -> {
//                    Log.d(TAG, "RxIO ThreadID" + android.os.Process.myTid());
                        mUserDao.insertAll(user);
                    Log.d(TAG, "initDB: User");

//                        Log.d(TAG, "Hello the " + user_fromDB.getFirstName() + " " + user_fromDB.getLastName());
                });

        Observable.just(article)
                .subscribeOn(Schedulers.io())
                .subscribe(article1 -> {
                    mArticleDao.insertAll(article1);
                    Log.d(TAG, "initDB: Article");
                });


    }

    //在子线程中使用数据库
    private void getAllUsers() {
        Log.d(TAG, "getAllUsers: ");

        //？为什么使用fromArray，传入List<User，Observer中的还是List 不应该是User了嘛
        //一定要使用array数组吗？答案：是的 可以用list转换为array的方法，非常方便

        //用from((User[])mUserDao.getAll().toArray())
        //你是不是缺心眼，这个肯定是在主线程中创建好了，然后才来from的呀。所以啊，想要在子线程中创建Observable，一定要使用Observable.create()方法
        Observable.create(subscribe -> {
            List<User> users = mUserDao.getAll();
            for (User user : users) {
                subscribe.onNext(user);
            }
        }).subscribeOn(Schedulers.io())
                .subscribe(user -> Log.d(TAG, "Hello the " + ((User) user).getFirstName() + " " + ((User) user).getLastName()));

        Observable.create(subscribe -> {
            List<Article> articles = mArticleDao.getAllarticle();
            for (Article article : articles) {
                subscribe.onNext(article);
            }
        }).subscribeOn(Schedulers.io())
                .subscribe(article -> Log.d(TAG, "the Article title is: " + ((Article) article).getTitle() + " the context is: " + ((Article) article).getContext()));


        //另一种是Bean中本身就是Rx对象
    }
}
