package com.example.article_edit;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.chinalwb.are.AREditor;
import com.chinalwb.are.strategies.VideoStrategy;

import static com.example.article_edit.TextViewActivity.HTML_TEXT;

/**
 * copy
 */
public class ArticleEdit_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_edit);

        initView();
    }

    //对应layout中的富文本编辑部分
    private AREditor arEditor;

//    private VideoStrategy mVideoStrategy = new VideoStrategy() {
//        @Override
//        public String uploadVideo(Uri uri) {
//            try {
//                Thread.sleep(3000); // Do upload here
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return "http://www.xx.com/x.mp4";
//        }
//
//        @Override
//        public String uploadVideo(String videoPath) {
//            try {
//                Thread.sleep(3000);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return "http://www.xx.com/x.mp4";
//        }
//    };

    private void initView() {
        this.arEditor = this.findViewById(R.id.areditor);
//        this.arEditor.setVideoStrategy(mVideoStrategy);
    }


    /**
     * 这个emm onActivityResult不是在使用StartActivityForResult的时候的吗。但是似乎并没有用到啊？
     * 先注释掉
     */
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        this.arEditor.onActivityResult(requestCode, resultCode, data);
//    }

    /**
     * 动态插入menu，重写activity中的方法，所以说啊，每个activity都可以自己来重写的
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId = item.getItemId();
        if (menuId == com.chinalwb.are.R.id.action_save) {
            String html = this.arEditor.getHtml();
            DemoUtil.saveHtml(this, html);
            return true;
        }
        if (menuId == R.id.action_show_tv) {
            String html = "";
            html = this.arEditor.getHtml();
            Intent intent = new Intent(this, TextViewActivity.class);
            intent.putExtra(HTML_TEXT, html);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}