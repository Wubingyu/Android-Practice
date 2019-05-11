package com.example.md_anim;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Button coordinator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*        ImageView imageView = findViewById(R.id.imageView);

        //使用search这种简单的就可以好好的绘制， 为什么同样是只有一个M的attachment就不行呢？
        AnimatedVectorDrawableCompat animatedVectorDrawableCompat = AnimatedVectorDrawableCompat.create(
                MainActivity.this, R.drawable.animated_vector_search
        );

        imageView.setImageDrawable(animatedVectorDrawableCompat);
        ((Animatable) imageView.getDrawable()).start();*/


/*        ImageView imageView = findViewById(R.id.image);
//        imageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.b1));
//        imageView.setBackground(getResources().getDrawable(R.drawable.b1));
        imageView.setBackground(this.getDrawable(R.drawable.b1));*/

        coordinator = findViewById(R.id.jump_coordinator);
        coordinator.setOnClickListener(v -> jump(coordinatorActivity.class));
    }

    private void jump(Class<?> activity) {
        Intent intent = new Intent(MainActivity.this, activity);
        startActivity(intent);
    }
}
