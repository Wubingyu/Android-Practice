package com.example.material_design;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

/**
 *    coordinator-协调者
 * 它有两种使用场景：
 * 1、作为顶层ViewGroup
 * 2、As a container for a specific interaction with one or more child views，作为一个或多个有特定响应动作的容器。
 *
 * 它的子View的具体响应动作是通过behavior指定的
 */
public class AppBar extends AppCompatActivity {
//    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator);

//        textView = findViewById(R.id.appbar_textView);
//        textView.setText("Analysis by Tara McKelvey, BBC White House reporter\n" +
//                "\n" +
//                "From day one, clearances have been a problem for the Trump White House.\n" +
//                "\n" +
//                "During the campaign, the president promised to \"drain the swamp\" and brought in a slew of people who had never before worked in the West Wing.\n" +
//                "\n" +
//                "As a result, the process of obtaining clearances took longer than usual, and in some cases protocol may have been breached.\n" +
//                "\n" +
//                "As then-Chief of Staff John Kelly told me last spring, he discovered that \"a couple of spreadsheets worth\" of background investigations on individuals were still incomplete when he started his job in 2017. Among these was an investigation of then-staff secretary Rob Porter, who was later accused of domestic abuse and forced to resign.\n" +
//                "\n" +
//                "As Porter's case shows, the decision to allow people to work in the White House without being thoroughly vetted has caused myriad problems, ones that date back to the administration's earliest days.");
//        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        CollapsingToolbarLayout collapsingToolbar =
//                (CollapsingToolbarLayout) findViewById(R.id.main_content);
//        collapsingToolbar.setTitle("我的课程");
    }

    public void checkin(View view) {
        Snackbar.make(view, "checkin success!", Snackbar.LENGTH_SHORT).show();
    }
}
