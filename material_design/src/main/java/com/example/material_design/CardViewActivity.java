package com.example.material_design;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CardViewActivity extends AppCompatActivity {
    private static final String TAG = "CardViewActivity";
    CardView cardView1, cardView2;
    TextView share, explore, text_card;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);

        cardView1 = findViewById(R.id.card_view_1);
        cardView2 = findViewById(R.id.card_view_2);
        share = findViewById(R.id.text_card1_share);
        explore = findViewById(R.id.text_card1_explore);
        text_card = findViewById(R.id.text_card2);

        cardView1.setOnClickListener(v -> makeSnackBar(cardView1, "cardView1 clicked"));
        cardView2.setOnClickListener(v -> makeSnackBar(cardView2, "cardView2 clicked"));
        share.setOnTouchListener((v, event) -> set_childViewClick(v, event, "share"));
        explore.setOnTouchListener((v, event) -> set_childViewClick(v, event,"explore"));
        text_card.setOnTouchListener((v, event) -> set_childViewClick(v, event, "text_card"));

        cardView1.setRadius(30);

    }

    boolean set_childViewClick(View v, MotionEvent event, String text) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onCreate: ACTION_DOWN");
                makeSnackBar(v, text);
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onCreate: ACTION_UP");
                break;
        }
        return false;
    }

    void makeSnackBar(View view, String text) {
        Snackbar.make(view, text, Snackbar.LENGTH_LONG)
                .setAction("Action", v -> Toast.makeText(
                        CardViewActivity.this,
                        text+" and its Action is clicked",
                        Toast.LENGTH_SHORT).show()).show();
    }
}
